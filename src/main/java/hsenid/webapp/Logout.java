package hsenid.webapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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
public class Logout extends HttpServlet {

    private static final Logger log = LogManager.getLogger(Logout.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            String user=req.getSession().getAttribute("user").toString();
            Connection connection=DBCon.connectionMap.get(user);
            try {
                connection.close();
                DBCon.connectionMap.remove(user);
                log.info("Connection of user \'"+user+"\' closed.");
            } catch (SQLException e) {
                log.error("Error closing connection of user \'" + user + "\'.");
            }
            session.invalidate();   // Invalidate the session.
            RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
            rd.forward(req, resp);
            log.info("Session invalidated.");
        }
    }
}
