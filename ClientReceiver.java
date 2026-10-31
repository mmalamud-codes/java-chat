import java.io.*;
import java.net.*;

public class ClientReceiver implements Runnable {
    
    private Socket socket;
    private BufferedReader in;

    public ClientReceiver (Socket socket) {
        
        this.socket = socket;

        try 
        {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (IOException e) 
        {
            System.err.println("Error: " + e.getMessage());
        }

    }

    String clientMessage;

    @Override
    public void run() {

        try 
        {
            while ((clientMessage = in.readLine()) != null) {
                System.out.println("\nUnknown: " + clientMessage);
                System.out.print("You: ");
            }
        }
        catch (IOException e) 
        {
            System.out.println("Connection closed.");
        }

    }


}