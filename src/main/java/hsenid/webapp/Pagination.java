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
 * Created by hsenid on 5/5/16.
 */
public class Pagination extends HttpServlet {

    private static final Logger log = LogManager.getLogger(Pagination.class);
    Connection connection;
    PreparedStatement statement;
    ResultSet resultSet;
    JsonArray jsonArray=null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            connection = DBCon.getComboDataSource().getConnection();
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            connection = DBCon.getComboDataSource().getConnection();
            String query = "SELECT COUNT(*) AS count FROM user_cred;";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                out.println(resultSet.getString("count"));
            }
        } catch (SQLException e) {
            log.error("Error while get the data count for the pagination. " + e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("Error closing connection while get the data count for the pagination. " + e);
            }
        }
    }
}
