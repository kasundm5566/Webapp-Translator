package hsenid.webapp;

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
 * Created by hsenid on 5/9/16.
 */
public class SearchPagination extends HttpServlet {

    private static final Logger log = LogManager.getLogger(SearchPagination.class);
    private Connection con = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet result = null;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        int count=searchCount(req.getParameter("searchName"));
        out.println(count);
    }

    public int searchCount(String fistName){
        int count=0;
        try {
            con = DBCon.getComboDataSource().getConnection();
            String query = "SELECT COUNT(*) AS count FROM user_cred WHERE FirstName LIKE \'"+fistName+"%';";
            preparedStatement = con.prepareStatement(query);
            result = preparedStatement.executeQuery();
            while (result.next()) {
                count=Integer.parseInt(result.getString("count"));
            }
        } catch (SQLException e) {
            log.error("Error while get the data count for the search pagination. " + e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (result != null) {
                    result.close();
                }
            } catch (SQLException e) {
                log.error("Error closing the connection related objects created while retrieving the data count for the search pagination. " + e);
            }
        }
        return count;
    }
}

