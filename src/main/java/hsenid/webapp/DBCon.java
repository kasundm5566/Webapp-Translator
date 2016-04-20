package hsenid.webapp;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.beans.PropertyVetoException;

/**
 * Created by hsenid.
 *
 * @author Kasun Dinesh
 */
public class DBCon {
    private static final Logger log = LogManager.getLogger(DBCon.class);
    private static ComboPooledDataSource comboPooledDataSource;

    /**
     * @param driver   Driver using to create the database connection
     * @param host     URL of the database
     * @param database Name of the database to use
     * @param dbuser   Database user name
     * @param dbpass   Password of the database
     */
    public static void createComboDataSource(String driver, String host, String database, String dbuser, String dbpass) {
        try {
            PropertyReader propertyReader = new PropertyReader("c3p0.properties");
            log.info("Initializing the database connection.");
            comboPooledDataSource = new ComboPooledDataSource();
            comboPooledDataSource.setInitialPoolSize(Integer.parseInt(propertyReader.readProperty("initial.pool.size")));
            comboPooledDataSource.setMinPoolSize(Integer.parseInt(propertyReader.readProperty("min.pool.size")));
            comboPooledDataSource.setMaxPoolSize(Integer.parseInt(propertyReader.readProperty("max.pool.size")));
            comboPooledDataSource.setAcquireIncrement(Integer.parseInt(propertyReader.readProperty("acquire.increment")));
            comboPooledDataSource.setCheckoutTimeout(Integer.parseInt(propertyReader.readProperty("checkout.timeout")));
            comboPooledDataSource.setDriverClass(driver);
            comboPooledDataSource.setJdbcUrl(host + database);
            comboPooledDataSource.setUser(dbuser);
            comboPooledDataSource.setPassword(dbpass);
            log.info("Database connection initialized successfully.");
        } catch (PropertyVetoException ex) {
            log.error("Error while connecting to the database. " + ex);
        }
    }

    /**
     * @return returns ComboPooledDataSource object
     */
    public static ComboPooledDataSource getComboDataSource() {
        return comboPooledDataSource;
    }

    // Closing the database connection pool
    public static void closeComboPoolDataSource() {
        if (comboPooledDataSource != null) {
            log.info("Closing the database connection.");
            getComboDataSource().close();
        }
    }
}
