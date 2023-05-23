package server;

import Fashion.Fashion;

public class Main {
    public static void main(String[] args) {
        Fashion fashion = Fashion.getInstance();
        Server server = new Server();
        server.toRead(fashion.getPort());
    }
}
