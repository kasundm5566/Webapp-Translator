package hsenid.webapp;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by hsenid.
 *
 * @author Kasun Dinesh
 */
public class DBCon {
    private static Connection connection;
    private static final Logger log = LogManager.getLogger(DBCon.class);

    /**
     * @param host     URL of the database
     * @param database Name of the database to use
     * @param dbuser   Database user name
     * @param dbpass   Password of the database
     */
    public static void createConnection(String driver, String host, String database, String dbuser, String dbpass) {
        try {
            log.info("Initializing the database connection.");
            Class.forName(driver).newInstance();
            /*ComboPooledDataSource cpd=new ComboPooledDataSource();
            cpd.setMinPoolSize(10);
            cpd.setJdbcUrl(host + database);
            cpd.setUser(dbuser);
            cpd.setPassword(dbpass);
            connection=cpd.getConnection();*/
            connection = (Connection) DriverManager.getConnection(host + database, dbuser, dbpass);
            log.info("Database connection initialized successfully.");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            log.error("Error while connecting to the database. " + ex);
        }
    }

    /**
     * @return returns Connection object
     */
    public static Connection getConnection() {
        return connection;
    }

    // Closing the databsae connection
    public static void closeConnection() {
        if (connection != null) {
            try {
                log.info("Closing the database connection.");
                getConnection().close();
            } catch (SQLException e) {
                log.error("Error while closing the database connection. " + e);
            }
        }
    }
}
