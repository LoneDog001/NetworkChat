package server;

import file.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public final Logger file = Logger.getInstance();
    public Socket clientSocket;
    public ServerSocket serverSocket;

    public void toRead(int port) {
        try {
            serverSocket = new ServerSocket(port);
            file.log("Сервер запущен");
            System.out.println("Сервер запущен");

            while (true) {
                clientSocket = serverSocket.accept();
                Relation connection = new Relation(clientSocket);
                Relation.connections.add(connection);
                connection.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
    }

    private void closeAll() {
        try {
            serverSocket.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
