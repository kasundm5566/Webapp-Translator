package hsenid.webapp;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by hsenid.
 *
 * @author Kasun Dinesh
 */
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        PropertyReader propReader = new PropertyReader("system.properties");
        DBCon.createConnection(propReader.readProperty("db.driver"), propReader.readProperty("db.host"), propReader.readProperty("db.database"), propReader.readProperty("db.user"), propReader.readProperty("db.password"));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DBCon.closeConnection();
    }
}
