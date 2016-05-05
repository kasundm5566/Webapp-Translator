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

/**
 * Created by hsenid on 4/27/16.
 */
public class Load extends HttpServlet {
    Connection connection;
    private static final Logger log = LogManager.getLogger(Load.class);
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        try {
            int pageNo=Integer.parseInt(request.getParameter("page"));
            connection = DBCon.getComboDataSource().getConnection();
            String query = "SELECT * FROM user_cred LIMIT 10 OFFSET "+10*(pageNo-1)+";";
            String queryCityName=null;
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            JsonArray jsonArray = new JsonArray();

            while (resultSet.next()) {
                JsonObject jsonObj = new JsonObject();
                jsonObj.addProperty("id", resultSet.getString("ID"));
                jsonObj.addProperty("firstname", resultSet.getString("FirstName"));
                jsonObj.addProperty("lastname", resultSet.getString("LastName"));
                jsonObj.addProperty("country", resultSet.getString("Country"));
                queryCityName="SELECT Name FROM city WHERE ID="+Integer.parseInt(resultSet.getString("CityId"))+";";
                statement=connection.prepareStatement(queryCityName);
                ResultSet cityNames=statement.executeQuery();
                if(cityNames.next()){
                    jsonObj.addProperty("city", cityNames.getString("Name"));
                }else{
                    jsonObj.addProperty("city", "-");
                }
                jsonObj.addProperty("dob", resultSet.getString("DOB"));
                jsonObj.addProperty("username", resultSet.getString("UserName"));
                jsonObj.addProperty("email", resultSet.getString("Email"));
                jsonObj.addProperty("tel", resultSet.getString("ContactNo"));
                jsonArray.add(jsonObj);
            }
            out.println(jsonArray);
        } catch (SQLException e) {
            log.error("Error while loading all user details from the database. "+e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("Error while closing the connection created to load all users. "+e);
            }
        }
    }
}
