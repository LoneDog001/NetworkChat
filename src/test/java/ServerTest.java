import file.Logger;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServerTest {
    private static final Logger log = Logger.getInstance();
    String host;
    int port;

    @Test
    void main(){
        try {
            FileReader reader = new FileReader("./src/main/java/settings/settings.txt");
            Properties properties = new Properties();
            properties.load(reader);
            port = Integer.parseInt(properties.getProperty("port"));
            host = properties.getProperty("host");
        } catch (IOException e) {
            log.log(e.getMessage());
            System.out.println(e.getMessage());
        }
        assertEquals(9954, port);
        assertEquals("127.0.0.1" , host);
    }
}
