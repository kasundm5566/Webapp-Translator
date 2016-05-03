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
    Connection connection;
    PreparedStatement statement;
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
        //out.println(user.getFirstName()+" "+user.getLastName()+" "+user.getCountry()+" "+user.getDob()+" "+user.getUserName()+" "+user.getEmail()+" "+user.getContactNo());
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
            log.error("Error while deleting the user. "+e);
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("Error closing the connection while updating the user. "+e);
            }
        }
        return exec;
    }
}
