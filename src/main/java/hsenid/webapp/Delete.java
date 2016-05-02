package hsenid.webapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.AlgorithmConstraints;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by hsenid on 5/2/16.
 */
public class Delete extends HttpServlet {

    private static final Logger log= LogManager.getLogger(Delete.class);
    Connection connection;
    PreparedStatement statement;

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        int userId=Integer.parseInt(request.getParameter("id"));
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        int result=deleteUser(userId);
        out.println(result);
    }

    public int deleteUser(int id){
        int exec=0;
        try {
            connection=DBCon.getComboDataSource().getConnection();
            String query="DELETE FROM user_cred WHERE ID="+id+";";
            statement=connection.prepareStatement(query);
            exec=statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Error while deleting the user. "+e);
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("Error closing the connection while deleting the user. "+e);
            }
        }
        return exec;
    }
}
