/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hsenid.webapp;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;

/**
 * Created by hsenid.
 *
 * @author hsenid
 */
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        PropertyReader propReader = new PropertyReader();
        DBCon.CreateConnection(propReader.getHost(), propReader.getDB(), propReader.getUser(), propReader.getPassword());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            DBCon.getConnection().close();
        } catch (SQLException ex) {

        }
    }
}
