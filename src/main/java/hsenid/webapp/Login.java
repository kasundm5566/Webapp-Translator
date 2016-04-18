package hsenid.webapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by hsenid.
 *
 * @author Kasun Dinesh
 */
public class Login extends HttpServlet {

    private User user;
    static String error = "Error in username or password!";
    private Translator translator = new Translator();
    private static final Logger log = LogManager.getLogger(Login.class);
    private Connection connection;

    @Override
    /**
     * This method will gets parameters/values sent by the login.jsp and process
     * them.
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        user = new User(req.getParameter("uname"), req.getParameter("pass"));
        try {
            boolean status = validateByDb(user);
            if (status) {   // User validated.
                log.info("User \'" + user.getUserName() + "\' validated.");
                DBCon.connectionMap.put(user.getUserName(),connection);
                HttpSession httpSession = req.getSession(false);
                httpSession.setAttribute("username", user.getUserName());   // Create a new session.
                Vector<String> list = translator.loadLanguages();   // Languages will be loaded to a vector.
                httpSession.setAttribute("langs", list);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/translate.jsp");
                rd.forward(req, resp);  // Forward to a new page with data such as language list, user details.
                log.info("New session initialized.");
            } else {
                log.warn("Wrong username, password combination.");
                error = "User name and password does not match!";
                connection.close();
                req.setAttribute("error_msg", error);   // Set an error message to pass to a next page.
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                rd.forward(req, resp);  // Forward to a new page with error message.
            }
        } catch (SQLException e) {
            log.error("Error while validating user. " + e);
            try {
                connection.close();
            } catch (SQLException e1) {
                log.error("Error while closing the connection of validating user. " + e1);
            }
            throw new ServletException(e);
        }
    }

    /**
     * @param user Passing a user to validate username and password
     * @return status Returns whether user passed the validation or not
     * @throws java.lang.Exception
     */
    public boolean validateByDb(User user) throws ServletException, SQLException {
        boolean status = false;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = DBCon.getComboDataSource().getConnection();
            String query = "SELECT Name FROM user_cred WHERE Name=BINARY\"" + user.getUserName() + "\" && pass=md5(\"" + user.getPassword() + "\");";
            PreparedStatement statement1 = connection.prepareStatement(query);
            result = statement1.executeQuery(); // Execute the query to validate the user.
            status = result.first();    // If there is a record, user will be validated (query is for matching both the username & password).
        } catch (SQLException e) {
            log.error("Error while validating user. " + e);
            connection.close();
            DBCon.connectionMap.remove(user.getUserName());
            throw new ServletException(e);
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (result != null) {
                result.close();
            }
        }
        return status;
    }
}
