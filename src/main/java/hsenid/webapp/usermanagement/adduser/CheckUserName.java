package hsenid.webapp.usermanagement.adduser;

import hsenid.webapp.common.DBCon;
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
public class CheckUserName extends HttpServlet {
    private static final Logger log = LogManager.getLogger(CheckUserName.class);
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet result = null;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        if (validateUserName(username)) {
            out.println("OK");
        } else {
            out.println("NO");
        }
    }

    public boolean validateUserName(String username) {
        boolean availability = false;
        try {
            connection = DBCon.getComboDataSource().getConnection();
            String query = "SELECT UserName FROM user_cred WHERE UserName=\'" + username + "\';";
            PreparedStatement statement = connection.prepareStatement(query);
            result = statement.executeQuery();
            if (!result.first()) {
                availability = true;
            }
        } catch (SQLException e) {
            log.error("Error while checking user name availability. " + e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (result != null) {
                    result.close();
                }
            } catch (SQLException e) {
                log.error("Error while closing the common related objects created to check the user name availability. " + e);
            }
        }
        return availability;
    }
}
