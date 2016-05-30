package hsenid.webapp.usermanagement.searchuser;

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
import java.sql.SQLException;

/**
 * Created by hsenid on 5/2/16.
 */
public class Delete extends HttpServlet {

    private static final Logger log= LogManager.getLogger(Delete.class);
    private Connection connection=null;
    private PreparedStatement statement=null;

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
            connection= DBCon.getComboDataSource().getConnection();
            String userDeleteQuery="DELETE FROM user_cred WHERE ID="+id+";";
            String groupDeletequery="DELETE FROM user_group WHERE user_id="+id+";";
            statement=connection.prepareStatement(userDeleteQuery);
            exec=statement.executeUpdate();
            statement=connection.prepareStatement(groupDeletequery);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Error while deleting the user. "+e);
        }finally {
            try {
                if(connection!=null){
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                log.error("Error closing the common while deleting the user. "+e);
            }
        }
        return exec;
    }
}
