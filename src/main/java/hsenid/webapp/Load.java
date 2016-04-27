package hsenid.webapp;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.commons.collections.map.MultiValueMap;
import org.testng.collections.ListMultiMap;

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
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by hsenid on 4/27/16.
 */
public class Load extends HttpServlet{
    Connection connection;
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        try {
            connection=DBCon.getComboDataSource().getConnection();
            String query="SELECT * FROM user_cred;";
            PreparedStatement statement=connection.prepareStatement(query);
            ResultSet resultSet=statement.executeQuery();
            MultiValueMap map=new MultiValueMap();

            JsonObject json      = new JsonObject();
            JsonArray firstname = new JsonArray();
            while (resultSet.next()){
                /*map.put("firstname", resultSet.getString("FirstName"));
                map.put("lastname", resultSet.getString("LastName"));
                map.put("country", resultSet.getString("Country"));
                map.put("dob", resultSet.getString("DOB"));
                map.put("username", resultSet.getString("UserName"));
                map.put("email", resultSet.getString("Email"));
                map.put("tel",resultSet.getString("ContactNo"));*/
                firstname.add(resultSet.getString("FirstName"));
            }
            json.add("firstname",firstname);
            out.println(json.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
