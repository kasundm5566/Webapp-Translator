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
        User user = new User(request.getParameter("ufname"), request.getParameter("ulname"), request.getParameter("ucountry"), request.getParameter("udate"), request.getParameter("uemail"), Long.parseLong(request.getParameter("utel")));
        int userId=Integer.parseInt(request.getParameter("id"));
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        int result=updateUser(user,userId);
        out.println(result);
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

    public void updateUserGroups(int userId){
        Connection con=null;
        PreparedStatement preStatement=null;
        String groupUpdateQuery=null;
        String groupDeleteQuery=null;
        try {
            con=DBCon.getComboDataSource().getConnection();
            groupDeleteQuery="DELETE FROM user_group WHERE user_id="+userId+";";

            preStatement=con.prepareStatement(groupUpdateQuery);
        } catch (SQLException e) {
            log.error("Error while updating user_groups. "+e);
        }
    }
}
