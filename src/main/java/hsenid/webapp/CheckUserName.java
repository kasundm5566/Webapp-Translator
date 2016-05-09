package hsenid.webapp;

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
public class CheckUserName extends HttpServlet{
    private static final Logger log = LogManager.getLogger(CheckUserName.class);
    private Connection connection=null;
    private PreparedStatement statement = null;
    private ResultSet result = null;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String username=req.getParameter("username");
            connection=DBCon.getComboDataSource().getConnection();
            String query = "SELECT UserName FROM user_cred WHERE UserName=\'"+username+"\';";
            PreparedStatement statement = connection.prepareStatement(query);
            result = statement.executeQuery();
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            if(result.first()){
                out.println("NO");
            }else{
                out.println("OK");
            }
        } catch (SQLException e) {
            log.error("Error while checking user name availability. "+e);
        }finally {
            try {
                if(connection!=null){
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (result != null) {
                    result.close();
                }
            } catch (SQLException e) {
                log.error("Error while closing the connection create to check the user name availability. "+e);
            }
        }
    }
}
