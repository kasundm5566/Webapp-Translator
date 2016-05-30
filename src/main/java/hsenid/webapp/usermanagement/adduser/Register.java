package hsenid.webapp.usermanagement.adduser;

import hsenid.webapp.common.DBCon;
import hsenid.webapp.usermanagement.common.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by hsenid on 4/26/16.
 */
public class Register extends HttpServlet {
    private Connection connection = null;
    private PreparedStatement statement = null;
    ResultSet resultSet = null;
    private static final Logger log = LogManager.getLogger(Register.class);
    private String city = null;
    String[] userGroups = null;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        city = req.getParameter("city");
        User user = new User(req.getParameter("fname"), req.getParameter("lname"), req.getParameter("country"), req.getParameter("city"), req.getParameter("date"), req.getParameter("username"), req.getParameter("pass"), req.getParameter("email"), Long.parseLong(req.getParameter("tel")));
        userGroups = req.getParameterValues("group");
        int result = addUser(user, userGroups);
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println(result);
    }

    public int addUser(User user, String[] groupList) {
        int exec = 0;
        try {
            connection = DBCon.getComboDataSource().getConnection();
            String queryCityId = "SELECT ID FROM city WHERE Name=\'" + city + "\';";
            statement = connection.prepareStatement(queryCityId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String query = "INSERT INTO user_cred (UserName,Pass,FirstName,LastName,DOB,Country,CityId,Email,ContactNo) VALUES (\'" + user.getUserName() + "\', md5(\'" + user.getPassword() + "\'),\'" + user.getFirstName() + "\',\'" + user.getLastName() + "\'," + user.getDob().replaceAll("-", "") + ",\'" + user.getCountry() + "\',\'" + Integer.parseInt(resultSet.getString("ID")) + "\',\'" + user.getEmail() + "\',\'" + user.getContactNo() + "\');";
                statement = connection.prepareStatement(query);
                exec = statement.executeUpdate();

                for (int i = 0; i < groupList.length; i++) {
                    String groupQuery = "INSERT INTO user_group (user_id,group_id) VALUES ((SELECT ID FROM user_cred WHERE UserName=\'" + user.getUserName() + "\'),(SELECT ID FROM userdata.group WHERE Name=\'" + groupList[i] + "\'));";
                    statement = connection.prepareStatement(groupQuery);
                    exec = statement.executeUpdate();
                }

            } else {
                log.error("Error with city data while adding a new user.");
            }
        } catch (SQLException e) {
            log.error("Error while adding a new user. " + e);
            exec = 0;
            rollBack(user);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                log.error("Error closing the common related objects created while adding a new user. " + e);
            }
        }
        return exec;
    }

    // This method is to remove partially added user record in an error
    public void rollBack(User user) {
        Connection con = null;
        PreparedStatement preState = null;
        try {
            con = DBCon.getComboDataSource().getConnection();
            String rollBackQuery = "DELETE FROM user_cred WHERE UserName=\'" + user.getUserName() + "\';";
            preState = con.prepareStatement(rollBackQuery);
            preState.executeUpdate();
        } catch (SQLException e) {
            log.error("Error while roll back adding user. " + e);
        } finally {
            try {
                preState.close();
                con.close();
            } catch (SQLException e) {
                log.error("Error closing the ResultSet while roll back adding user. " + e);
            }
        }
    }
}
