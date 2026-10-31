import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {
    
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ClientHandler(Socket socket) {
        
        this.socket = socket;

        try 
        {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (IOException e) 
        {
            System.err.println("Error: " + e.getMessage());
        }

    }

    @Override
    public void run() {

        try {
            
            String clientMessage;

            while ((clientMessage = in.readLine()) != null) {

                System.out.println("Received: " + clientMessage);

                if (clientMessage.equalsIgnoreCase("Q")) {
                    JavaServer.clients.remove(this);
                    socket.close();

                    if (JavaServer.clients.isEmpty()) {
                        JavaServer.shutDown();
                    }

                }

                for (ClientHandler client : JavaServer.clients) {
                    if (client != this) {
                        client.out.println(clientMessage);
                    }
                }

            }
        }
        catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        
    }
}