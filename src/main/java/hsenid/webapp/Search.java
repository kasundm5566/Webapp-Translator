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
    private static final Logger log = LogManager.getLogger(Search.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String process = req.getParameter("process");
        JsonArray jsonArray = search(req.getParameter("searchName"), Integer.parseInt(req.getParameter("offset")));
        out.println(jsonArray);
    }

    public JsonArray search(String firstName, int offset) {
        try {
            connection = DBCon.getComboDataSource().getConnection();
            query = "SELECT * FROM user_cred u,city c WHERE u.cityId=c.cityId AND FirstName LIKE\'" + firstName + "%\' LIMIT 10 OFFSET " + 10 * (offset - 1) + ";";

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
                jsonObj.addProperty("city", resultSet.getString("CityName"));
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
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                log.error("Error while closing connection created search users. " + e);
            }
        }
        return jsonArray;
    }
}
