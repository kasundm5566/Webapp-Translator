package hsenid.webapp;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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
import java.util.Iterator;

/**
 * Created by hsenid on 5/2/16.
 */
public class Update extends HttpServlet{
    private static final Logger log= LogManager.getLogger(Update.class);
    private Connection connection=null;
    private PreparedStatement statement=null;
    String city=null;

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        city=request.getParameter("ucity");
        String[] groups=request.getParameterValues("ugroup[]");

        User user = new User(request.getParameter("ufname"), request.getParameter("ulname"), request.getParameter("ucountry"), request.getParameter("udate"), request.getParameter("uemail"), Long.parseLong(request.getParameter("utel")));
        int userId=Integer.parseInt(request.getParameter("id"));
        int userUpdateResult=updateUser(user, userId);
        updateUserGroups(userId,groups);
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        out.println(userUpdateResult);
    }

    public int updateUser(User user,int id){
        int exec=0;
        try {
            connection=DBCon.getComboDataSource().getConnection();
            String queryCityId="SELECT ID FROM city WHERE Name=\'"+city+"\';";
            statement=connection.prepareStatement(queryCityId);
            ResultSet cityNames=statement.executeQuery();
            if (cityNames.next()){
                String query="UPDATE user_cred SET FirstName=\'"+user.getFirstName()+"\',LastName=\'"+user.getLastName()+"\',Country=\'"+user.getCountry()+"\',DOB=\'"+user.getDob()+"\',Email=\'"+user.getEmail()+"\',ContactNo=\'"+user.getContactNo()+"\',CityId="+Integer.parseInt(cityNames.getString("ID"))+" WHERE ID="+id+";";
                statement=connection.prepareStatement(query);
                exec=statement.executeUpdate();
            }else{
                log.error("Error getting the city id while updating the user.");
            }
        } catch (SQLException e) {
            log.error("Error while updating the user. "+e);
        }finally {
            try {
                if(connection!=null){
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                log.error("Error closing the connection related objects created while updating the user. "+e);
            }
        }
        return exec;
    }

    public void updateUserGroups(int userId, String[] groups){
        Connection con=null;
        PreparedStatement preStatement=null;
        String groupUpdateQuery=null;
        String groupDeleteQuery=null;
        try {
            con=DBCon.getComboDataSource().getConnection();
            groupDeleteQuery="DELETE FROM user_group WHERE user_id="+userId+";";
            preStatement=con.prepareStatement(groupDeleteQuery);
            preStatement.executeUpdate();
            for (int i = 0; i < groups.length; i++) {
                groupUpdateQuery="INSERT INTO user_group (user_id,group_id) VALUES ("+userId+",(SELECT ID FROM userdata.group WHERE Name=\'" + groups[i] + "\'));";
                preStatement=con.prepareStatement(groupUpdateQuery);
                preStatement.executeUpdate();
            }
        } catch (SQLException e) {
            log.error("Error while updating user_groups. "+e);
        }finally {
            try {
                if(con!=null){
                    con.close();
                }
                if (preStatement != null) {
                    preStatement.close();
                }
            } catch (SQLException e) {
                log.error("Error closing the connection related objects created while updating the user_groups. "+e);
            }
        }
    }
}
