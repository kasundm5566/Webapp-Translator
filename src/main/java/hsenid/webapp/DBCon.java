package hsenid.webapp;

import java.sql.Connection;
import java.sql.DriverManager;

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
    public static void CreateConnection(String host, String database, String dbuser, String dbpass) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = (Connection) DriverManager.getConnection(host + database, dbuser, dbpass);
        } catch (Exception ex) {
            Login.error = "Something bad happened. Try again later.";
        }
    }

    /**
     * @return returns Connection object
     */
    public static Connection getConnection() {
        return connection;
    }
}
