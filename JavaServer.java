import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class JavaServer {

    static ArrayList<ClientHandler> clients = new ArrayList<>();

    private static ServerSocket serverSocket;

    public static void shutDown() {
        try  {
            serverSocket.close();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        int port = 8080;

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server is listening on port " + port);

            while (true) {

                Socket clientSocket = serverSocket.accept();

                System.out.println("Client connected on " + clientSocket.getRemoteSocketAddress());

                ClientHandler handler = new ClientHandler(clientSocket);

                clients.add(handler);

                Thread thread = new Thread(handler);

                thread.start();

            }
        }
        catch (IOException e) {
            System.err.println("Cannot listen on port " + port + ": " + e.getMessage());
        }
    }
    
}