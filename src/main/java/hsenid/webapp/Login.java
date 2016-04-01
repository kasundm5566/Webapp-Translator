package hsenid.webapp;

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

    User user;
    static String error = "Error in username or password!";
    Translator translator = new Translator();

    @Override
    /**
     * This method will gets parameters/values sent by the login.jsp and process
     * them.
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        user = new User(req.getParameter("uname"), req.getParameter("pass"));

        try {
            boolean status = ValidateByDB(user);
            if (status) {
                HttpSession httpSession = req.getSession(false);
                httpSession.setAttribute("username", user.getUserName());
                Vector<String> list = translator.LoadLanguages();
                httpSession.setAttribute("langs", list);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/translate.jsp");
                rd.forward(req, resp);
            } else {
                error = "User name and password does not match!";
                req.setAttribute("error_msg", error);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                rd.forward(req, resp);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    /**
     * @param user Passing a user to validate username and password
     * @return status Returns whether user passed the validation or not
     * @throws java.lang.Exception
     */
    public static boolean ValidateByDB(User user) throws Exception {
        boolean status = false;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            Connection connection = DBCon.getConnection();
            String query = "SELECT Name FROM user_cred WHERE Name=BINARY\"" + user.getUserName() + "\" && pass=md5(\"" + user.getPassword() + "\");";
            PreparedStatement statement1 = connection.prepareStatement(query);
            result = statement1.executeQuery();
            status = result.first();
        } catch (SQLException e) {
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
