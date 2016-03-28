package hsenid.webapp;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.SQLException;

/**
 * Created by hsenid.
 *
 * @author Kasun Dinesh
 */
public class ContextListener implements ServletContextListener {
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        PropertyReader propReader = new PropertyReader("system.properties");
        DBCon.CreateConnection(propReader.readProperty("db.host"), propReader.readProperty("db.database"), propReader.readProperty("db.user"), propReader.readProperty("db.password"));
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            DBCon.getConnection().close();
        } catch (SQLException ex) {
            
        }
    }
}
