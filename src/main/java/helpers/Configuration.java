package helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
    // https://www.baeldung.com/java-properties
    public static String getBaseUrl() {
        return getProperty("baseUrl");
    }

    public static String getKey() {
        return getProperty("key");
    }

    public static String getToken() {
        return getProperty("token");
    }

    private static String getProperty(String propertyName) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/config.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties.getProperty(propertyName);
    }
}