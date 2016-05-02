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

    private static final Logger log = LogManager.getLogger(Search.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String process = req.getParameter("process");
        JsonArray jsonArray=null;
        if (process.equals("typeahead")) {
            jsonArray = searchTypeahead(req.getParameter("searchName"));
            out.println(jsonArray);
        } else if (process.equals("search")) {
            jsonArray=search(req.getParameter("searchName"));
            out.println(jsonArray);
        }
    }

    public JsonArray searchTypeahead(String match) {
        Connection connection = null;
        PreparedStatement statement;
        ResultSet resultSet;
        JsonObject jsonObject;
        JsonArray jsonArray = new JsonArray();
        try {
            connection = DBCon.getComboDataSource().getConnection();
            String query = "SELECT FirstName FROM user_cred HAVING UserName LIKE\'" + match + "%\';";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                jsonObject = new JsonObject();
                jsonObject.addProperty("firstname", resultSet.getString("FirstName"));
                jsonArray.add(jsonObject);
            }
        } catch (SQLException e) {
            log.error("Error while loading typeahead. " + e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("Error while closing connection created to load typeahead. " + e);
            }
        }
        return jsonArray;
    }

    public JsonArray search(String firstName) {
        Connection connection = null;
        PreparedStatement statement;
        ResultSet resultSet;
        JsonArray jsonArray = null;
        String query=null;
        try {
            connection = DBCon.getComboDataSource().getConnection();
            if(firstName!=null && !firstName.isEmpty()){
                query = "SELECT * FROM user_cred WHERE FirstName=\'" + firstName + "\';";
            }else{
                query="SELECT * FROM user_cred";
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
                jsonObj.addProperty("dob", resultSet.getString("DOB"));
                jsonObj.addProperty("username", resultSet.getString("UserName"));
                jsonObj.addProperty("email", resultSet.getString("Email"));
                jsonObj.addProperty("tel", resultSet.getString("ContactNo"));
                jsonArray.add(jsonObj);
            }

        } catch (SQLException e) {
            log.error("Error while searching users. " + e);
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("Error while closing connection created search users. " + e);
            }
        }
        return jsonArray;
    }
}
