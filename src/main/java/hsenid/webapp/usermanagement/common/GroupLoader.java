package hsenid.webapp.usermanagement.common;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import hsenid.webapp.common.DBCon;
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
 * Created by hsenid on 5/10/16.
 */
public class GroupLoader extends HttpServlet {

    private Connection connection = null;
    private PreparedStatement statement=null;
    private ResultSet resultSet=null;
    private JsonArray jsonArray = null;
    private String query = null;
    private static final Logger log = LogManager.getLogger(GroupLoader.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonArray jsonArray=loadGroups();
        resp.setContentType("text/html");
        PrintWriter out=resp.getWriter();
        out.println(jsonArray);
    }

    public JsonArray loadGroups(){
        try {
            connection= DBCon.getComboDataSource().getConnection();
            query="SELECT Name FROM userdata.group;";
            statement=connection.prepareStatement(query);
            resultSet=statement.executeQuery();
            jsonArray=new JsonArray();
            while (resultSet.next()){
                JsonObject jsonObject=new JsonObject();
                jsonObject.addProperty("groupname",resultSet.getString("Name"));
                jsonArray.add(jsonObject);
            }
        } catch (SQLException e) {
            log.error("Error while loading groups. "+e);
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
                log.error("Error closing the common related objects created while loading groups. "+e);
            }
        }
        return jsonArray;
    }
}
