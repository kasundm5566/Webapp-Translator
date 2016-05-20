package hsenid.webapp;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.omg.CORBA.portable.ValueOutputStream;

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
 * Created by hsenid on 5/3/16.
 */
public class CityLoader extends HttpServlet{

    private Connection connection = null;
    private PreparedStatement statement=null;
    private ResultSet resultSet=null;
    private JsonArray jsonArray = null;
    private String query = null;
    private static final Logger log = LogManager.getLogger(CityLoader.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String country=req.getParameter("country");
        JsonArray jsonArray=loadCities(country);
        resp.setContentType("text/html");
        PrintWriter out=resp.getWriter();
        out.println(jsonArray);
    }

    public JsonArray loadCities(String country){
        try {
            connection=DBCon.getComboDataSource().getConnection();
            query="SELECT Name FROM city WHERE Country=\'"+country+"\';";
            statement=connection.prepareStatement(query);
            resultSet=statement.executeQuery();
            jsonArray=new JsonArray();
            while (resultSet.next()){
                JsonObject jsonObject=new JsonObject();
                jsonObject.addProperty("cityname",resultSet.getString("Name"));
                jsonArray.add(jsonObject);
            }
        } catch (SQLException e) {
            log.error("Error while loading cities. "+e);
        }finally {
            try {
                if(connection!=null){
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                log.error("Error closing the connection related objects while loading cities. "+e);
            }
        }
        return jsonArray;
    }
}
