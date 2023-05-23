package file;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static Logger instance;
    private static final String PATH = "./src/main/java/file/log.txt";
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd - MM - yyyy  HH:mm:ss");

    public static Logger getInstance(){
        if (instance == null){
            instance = new Logger();
        }
        return instance;
    }

    public boolean log(String message){
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(PATH, true));
            writer.write(LocalDateTime.now().format(dateTimeFormatter) + " " + message + "\n");
            writer.flush();
            return true;
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }
}
