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

    private final String fileName;

    public PropertyReader(String fileName) {
        this.fileName = fileName;
    }
    
    /**
     * This method will be used to read the system.properties file
     * @param fileName Name of the settings file
     * @return Properties object with the values from the given settings file
     */
    private Properties loadProperties() {
        Properties properties = new Properties();
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream input;
        input = classLoader.getResourceAsStream(fileName);
        try {
            properties.load(input);
        } catch (IOException ex) {

        }finally{
            try {
                input.close();
            } catch (IOException ex) {
                
            }
        }
        return properties;
    }

    /**
     * @param property Setting name need to be read from the properties file
     * @return Value of the given property(setting)
     */
    public String readProperty(String property) {
        Properties prop;
        prop=loadProperties();
        return prop.getProperty(property);
    }
}
