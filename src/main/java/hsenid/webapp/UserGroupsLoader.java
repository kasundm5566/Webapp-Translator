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
 * Created by hsenid on 5/20/16.
 */
public class UserGroupsLoader extends HttpServlet {

    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet userGroupsResult = null;
    private String query = null;
    private JsonArray jsonArray = null;
    private static final Logger log = LogManager.getLogger(UserGroupsLoader.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        User user=new User(req.getParameter("userName"));
        JsonArray userGroups=loadUserGroups(user);
        out.println(userGroups);
    }

    public JsonArray loadUserGroups(User user) {
        try {
            connection = DBCon.getComboDataSource().getConnection();
            query = "SELECT Name FROM userdata.group WHERE ID IN (SELECT group_id FROM user_group ug,userdata.user_cred uc WHERE ug.user_id=uc.ID AND uc.UserName=\'" + user.getUserName() + "\');";
            statement = connection.prepareStatement(query);
            userGroupsResult = statement.executeQuery();
            jsonArray=new JsonArray();
            while (userGroupsResult.next()) {
                JsonObject jsonObject=new JsonObject();
                jsonObject.addProperty("groupname",userGroupsResult.getString("Name"));
                jsonArray.add(jsonObject);
            }
        } catch (SQLException e) {
            log.error("Error while loading the groups of a selected user. "+e);
        } finally {
            try {
                if(connection!=null){
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (userGroupsResult != null) {
                    userGroupsResult.close();
                }
            } catch (SQLException e) {
                log.error("Error while closing the connection related objects created to load the groups of a selected user. " + e);
            }
        }
        return jsonArray;
    }
}
