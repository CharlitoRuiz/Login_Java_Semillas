package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static {
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            System.err.println("❌ Error cargando archivo de configuración: " + e.getMessage());
        }
    }

    public static String get(String clave) {
        return properties.getProperty(clave);
    }

    public static int getTimeout() {
        return Integer.parseInt(properties.getProperty("timeout", "10"));
    }
}