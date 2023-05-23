package Fashion;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Fashion {
    private static Fashion instance;
    private static final String PATH = "./src/main/java/settings/settings.txt";
    private int port;
    private String host;

    public int getPort() {
        return port;
    }

    public String getHost() {
        return host;
    }

    public Fashion() {
        try (FileReader fileReader = new FileReader(PATH)) {
            Properties properties = new Properties();
            properties.load(fileReader);
            port = Integer.parseInt(properties.getProperty("port"));
            host = properties.getProperty("host");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Fashion getInstance() {
        if (instance == null) {
            instance = new Fashion();
        }
        return instance;
    }
}
