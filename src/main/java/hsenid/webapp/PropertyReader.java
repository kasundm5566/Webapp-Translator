/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hsenid.webapp;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by hsenid.
 *
 * @author Kasun Dinesh
 */
public class PropertyReader {

    private Properties properties = new Properties();

    /**
     * This method will be used to read the system.properties file
     * @return
     * Properties object with the values from system.properties file
     */
    private Properties getProperty() {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream input;
        input = classLoader.getResourceAsStream("system.properties");        
        try {
            properties.load(input);
        } catch (IOException ex) {

        }
        return properties;
    }

    /**
     * Method to get the database URL
     * @return
     * Database URL
     */
    public String getHost() {
        properties = getProperty();
        return properties.getProperty("db.host");
    }

    /**
     * Method to get the database name
     * @return
     * Database
     */
    public String getDB() {
        properties = getProperty();
        return properties.getProperty("db.database");
    }

    /**
     * Method to get the database username
     * @return
     * Database user
     */
    public String getUser() {
        properties = getProperty();
        return properties.getProperty("db.user");
    }

    /**
     * Method to get the database password
     * @return
     * Database password
     */
    public String getPassword() {
        properties = getProperty();
        return properties.getProperty("db.password");
    }

    /**
     * Method to get the Yandex key
     * @return
     * yandex key
     */
    public String getYandexKey() {
        properties = getProperty();
        return properties.getProperty("yandex.key");
    }

    /**
     * Method to get the URL need to load the languages
     * @return
     * Yandex getlangs URL
     */
    public String getYandexLangsUrl() {
        properties = getProperty();
        return properties.getProperty("yandex.getlangsurl");
    }

    /**
     * Method to get the URL need to translate texts
     * @return
     * Yandex translation URL
     */
    public String getYandexTranslateUrl() {
        properties = getProperty();
        return properties.getProperty("yandex.translateurl");
    }
}
