/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hsenid.webapp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hsenid.
 *
 * @author hsenid
 */
public class Login extends HttpServlet {

    User user;
    static String error = "Error in username or password!";
    Translator translator=new Translator();

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
                ArrayList<String> list=translator.LoadLanguages();
                req.setAttribute("langs", list);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/translate.jsp");
                rd.forward(req, resp);
                //resp.sendRedirect("translate.jsp");
            } else {
                error = "User name and password does not match!";
                req.setAttribute("error_msg", error);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                rd.forward(req, resp);
            }
        } catch (Exception e) {
            error = "Something bad happened. Try again later.";
        }
    }

    /**
     * @param user Passing a user to validate username and password
     * @return status Returns whether user passed the validation or not
     */
    public static boolean ValidateByDB(User user) throws Exception {
        boolean status = false;
        Statement statement = null;
        ResultSet result = null;
        try {
            Connection connection = DBCon.getConnection();
            statement = connection.createStatement();
            String query = "SELECT Name FROM user_cred WHERE Name=\"" + user.getUsername() + "\" && pass=md5(\"" + user.getPassword() + "\");";
            result = statement.executeQuery(query);
            status = result.first();
        } catch (Exception e) {
            error = "Something bad happened. Try again later.";
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
