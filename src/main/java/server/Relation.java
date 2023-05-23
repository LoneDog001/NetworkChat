package server;

import file.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Relation extends Thread {
    public static final List<Relation> connections = Collections.synchronizedList(new ArrayList<>());
    public final Logger file = Logger.getInstance();
    public BufferedReader input;
    public PrintWriter output;

    public Relation(Socket socket) {
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            String name = input.readLine();
            file.log(name + " в сети");
            sendTextAll(name + " в сети");
            String message;
            while (true) {
                message = input.readLine();
                if ("exit".equals(message)) {
                    break;
                }
                file.log(name + ": " + message);
                sendTextAll(name + ": " + message);
            }
            file.log(name + " покинул(а) чат");
            sendTextAll(name + " покинул(а) чат");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            closeAll();
        }
    }

    private void closeAll() {
        try {
            input.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendTextAll(String message) {
        synchronized (connections) {
            for (Relation connection : connections) {
                connection.output.println(message);
            }
        }
    }
}
