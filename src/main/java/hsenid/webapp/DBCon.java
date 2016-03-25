package hsenid.webapp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;

/**
 * Created by hsenid.
 *
 * @author hsenid
 */
public class DBCon {

    private static Connection connection;

    /**
     * @param host     URL of the database
     * @param database Name of the database to use
     * @param dbuser   Database user name
     * @param dbpass   Password of the database
     */
    public static void CreateConnection(String host, String database, String dbuser, String dbpass){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = (Connection) DriverManager.getConnection(host + database, dbuser, dbpass);
        }catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            
        }
    }

    /**
     * @return returns Connection object
     */
    public static Connection getConnection() {
        return connection;
    }
}
