package client;

import Fashion.Fashion;

import java.io.*;
import java.net.Socket;

public class Client {
    private static BufferedReader reader;
    private Socket clientSocket;
    private BufferedReader input;
    private PrintWriter output;

    public Client() {
        try {
            Fashion fashion = Fashion.getInstance();
            reader = new BufferedReader(new InputStreamReader(System.in));
            clientSocket = new Socket(fashion.getHost(), fashion.getPort());
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            output = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);
            System.out.println("Введите свое имя или ник: ");
            output.println(reader.readLine());

            Answer responce = new Answer(input);
            responce.start();

            String message = " ";
            while (!"exit".equals(message)) {
                message = reader.readLine();
                output.println(message);
            }
            responce.interrupt();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
    }

    private void closeAll() {
        try {
            input.close();
            output.close();
            clientSocket.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
