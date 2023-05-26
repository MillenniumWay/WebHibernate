package Connection;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class PropertiesUtil {

    private static final Properties PROPERTIES = new Properties();

    static {
        try {
            loadProperties();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void loadProperties() throws RuntimeException, SQLException {
        try (var inputStream = PropertiesUtil.class.getClassLoader()
                .getResourceAsStream("app.properties")) {

            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }

    private PropertiesUtil() {
    }
}

