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
 * Created by hsenid on 4/29/16.
 */
public class Search extends HttpServlet {

    Connection connection = null;
    PreparedStatement statement;
    ResultSet resultSet;
    JsonArray jsonArray = null;
    String query = null;
    String queryCityName=null;
    private static final Logger log = LogManager.getLogger(Search.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String process = req.getParameter("process");
        JsonArray jsonArray = search(req.getParameter("searchName"));
        out.println(jsonArray);
    }

    public JsonArray search(String firstName) {
        try {
            connection = DBCon.getComboDataSource().getConnection();
            if (firstName != null && !firstName.isEmpty()) {
                query = "SELECT * FROM user_cred u,city c WHERE u.cityId=c.ID AND FirstName LIKE\'" + firstName + "%\';";
            } else {
                query = "SELECT * FROM user_cred u,city c WHERE u.cityId=c.ID;";
            }
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            jsonArray = new JsonArray();
            while (resultSet.next()) {
                JsonObject jsonObj = new JsonObject();
                jsonObj.addProperty("id", resultSet.getString("ID"));
                jsonObj.addProperty("firstname", resultSet.getString("FirstName"));
                jsonObj.addProperty("lastname", resultSet.getString("LastName"));
                jsonObj.addProperty("country", resultSet.getString("Country"));
                /*queryCityName="SELECT Name FROM city WHERE ID="+Integer.parseInt(resultSet.getString("CityId"))+";";
                statement=connection.prepareStatement(queryCityName);
                ResultSet cityNames=statement.executeQuery();
                if(cityNames.next()){
                    jsonObj.addProperty("city", cityNames.getString("Name"));
                }else{
                    jsonObj.addProperty("city", "-");
                }*/
                jsonObj.addProperty("city", resultSet.getString("Name"));
                jsonObj.addProperty("dob", resultSet.getString("DOB"));
                jsonObj.addProperty("username", resultSet.getString("UserName"));
                jsonObj.addProperty("email", resultSet.getString("Email"));
                jsonObj.addProperty("tel", resultSet.getString("ContactNo"));
                jsonArray.add(jsonObj);
            }

        } catch (SQLException e) {
            log.error("Error while searching users. " + e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("Error while closing connection created search users. " + e);
            }
        }
        return jsonArray;
    }
}
