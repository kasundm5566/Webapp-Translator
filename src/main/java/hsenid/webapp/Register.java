package hsenid.webapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Reporter;

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
    private Connection connection=null;
    private PreparedStatement statement=null;
    private static final Logger log = LogManager.getLogger(Register.class);
    private String city=null;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        city=req.getParameter("city");
        User user = new User(req.getParameter("fname"), req.getParameter("lname"), req.getParameter("country"), req.getParameter("city"), req.getParameter("date"), req.getParameter("username"), req.getParameter("pass"), req.getParameter("email"), Long.parseLong(req.getParameter("tel")));
        int result=addUser(user, req.getParameter("group"));
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println(result);
    }

    public int addUser(User user, String group) {
        int exec=0;
        try {
            connection = DBCon.getComboDataSource().getConnection();
            String queryCityId="SELECT CityId FROM city WHERE CityName=\'" +city+"\';";
            statement=connection.prepareStatement(queryCityId);
            ResultSet resultSet=statement.executeQuery();
            if(resultSet.next()){
                String query="INSERT INTO user_cred (UserName,Pass,FirstName,LastName,DOB,Country,CityId,Email,ContactNo) VALUES (\'"+user.getUserName()+"\', md5(\'"+user.getPassword()+"\'),\'"+user.getFirstName()+"\',\'"+user.getLastName()+"\',"+user.getDob().replaceAll("-","")+",\'"+user.getCountry()+"\',\'"+Integer.parseInt(resultSet.getString("CityId"))+"\',\'"+user.getEmail()+"\',\'"+user.getContactNo()+"\');";
                statement=connection.prepareStatement(query);
                exec=statement.executeUpdate();

                String groupQuery="INSERT INTO user_group (UserId,GrpId) VALUES ((SELECT ID FROM user_cred WHERE UserName=\'"+user.getUserName()+"\'),(SELECT GroupId FROM userdata.group WHERE GroupName=\'"+group+"\'));";
                statement=connection.prepareStatement(groupQuery);
                exec=statement.executeUpdate();

            }else{
                log.error("Error with city data while adding a new user.");
            }
/*            if(exec==1){
                if(exec!=1){
                    String rollbackQuery="DELETE FROM user_cred WHERE UserName=\'"+user.getUserName()+"\';";
                    statement=connection.prepareStatement(rollbackQuery);
                    statement.executeUpdate();
                }
            }*/
        } catch (SQLException e) {
            log.error("Error while adding a new user. "+e);
        }finally {
            try {
                if(connection!=null){
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                log.error("Error closing the connection while adding a new user. "+e);
            }
        }
        return exec;
    }
}
