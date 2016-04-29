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
        PrintWriter out=resp.getWriter();
        String s2=req.getParameter("process");
        if(s2.equals("typeahead")){
            JsonArray jsonArray=searchTypeahead(req.getParameter("searchUsername"));
            out.println(jsonArray);
        }else{

        }
    }

    public JsonArray searchTypeahead(String match){
        Connection connection = null;
        PreparedStatement statement;
        ResultSet resultSet;
        JsonObject jsonObject;
        JsonArray jsonArray=new JsonArray();
        try {
            connection=DBCon.getComboDataSource().getConnection();
            String query="SELECT UserName FROM user_cred HAVING UserName LIKE\'"+match+"%\';";
            statement=connection.prepareStatement(query);
            resultSet=statement.executeQuery();
            while(resultSet.next()){
                jsonObject=new JsonObject();
                jsonObject.addProperty("username", resultSet.getString("UserName"));
                jsonArray.add(jsonObject);
            }
        } catch (SQLException e) {
            log.error("Error while loading typeahead. "+e);
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("Error while closing connection created to load typeahead. " + e);
            }
        }
        return jsonArray;
    }
}
