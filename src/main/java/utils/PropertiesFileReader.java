package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;


// This class reads properties from configuration and email configuration files.
public class PropertiesFileReader {
    private static final Logger logger = LogManager.getLogger(PropertiesFileReader.class);
    private final Properties emailProperties;

    private String emailFilePath = "src/test/resources/test_data_files/email_configuration.properties";


    public PropertiesFileReader() {
        emailProperties = readProperties(emailFilePath);
    }

    public String getEmail() {
        return getProperty(emailProperties, "userEmail");
    }

    public String getPort() {
        return getProperty(emailProperties, "port");
    }

    public String getHost() {
        return getProperty(emailProperties, "host");
    }

    public String getEmailPassword() {
        return getProperty(emailProperties, "passwordEmail");
    }


    private Properties readProperties(String filePath) {
        Properties properties = new Properties();
        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
    public String readProperty(String filePath, String key) {
        Properties properties = new Properties();
        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            properties.load(inputStream);
            return properties.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Or handle the exception according to your requirements
        }
    }


    private String getProperty(Properties properties, String propertyName) {
        if (properties != null) {
            return properties.getProperty(propertyName);
        }
        return null;
    }

    public String readValueByKey(String key,String filePath) {
        String value = null;
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            Properties properties = new Properties();
            properties.load(fileInputStream);

            // Retrieve the value associated with the given key
            value = properties.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }
}


