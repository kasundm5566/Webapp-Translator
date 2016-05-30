package hsenid.webapp.usermanagement.searchuser;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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
 * Created by hsenid on 5/20/16.
 */
public class GetUserCity extends HttpServlet {
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet userCityResult = null;
    private String query = null;
    private JsonArray jsonArray = null;
    private static final Logger log = LogManager.getLogger(GetUserCity.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        User user=new User(req.getParameter("username"));
        JsonArray userCity=findUserCity(user);
        out.println(userCity);
    }

    public JsonArray findUserCity(User user){
        try {
            connection= DBCon.getComboDataSource().getConnection();
            query="SELECT Name FROM user_cred uc,city c WHERE uc.cityId=c.ID AND uc.UserName=\'"+user.getUserName()+"\';";
            statement=connection.prepareStatement(query);
            userCityResult=statement.executeQuery();
            jsonArray=new JsonArray();
            while (userCityResult.next()){
                JsonObject jsonObject=new JsonObject();
                jsonObject.addProperty("cityname",userCityResult.getString("Name"));
                jsonArray.add(jsonObject);
            }
        } catch (SQLException e) {
            log.error("Error while loading the city of the selected user. "+e);
        }finally {
            try {
                if(connection!=null){
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (userCityResult != null) {
                    userCityResult.close();
                }
            } catch (SQLException e) {
                log.error("Error while closing the common related objects created to load the city of the selected user. " + e);
            }
        }
        return jsonArray;
    }
}
