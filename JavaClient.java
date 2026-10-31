import java.io.*;
import java.net.*;
import java.util.Scanner;

public class JavaClient {

    private static Socket socket = null;
    private static BufferedReader in = null;
    private static PrintWriter out = null;
    static Scanner kb = new Scanner(System.in);

    public JavaClient(int port, String address) {

                try 
                {
                    socket = new Socket(address, port);
                    System.out.println("Connected");

                    out = new PrintWriter(socket.getOutputStream(), true);
                }
                catch (UnknownHostException u) 
                {
                    System.err.println("Error: " + u.getMessage());
                }
                catch (IOException e) 
                {
                    System.err.println("Error: " + e.getMessage());
                }

                String clientMessage = "";
                    
                ClientReceiver receiver = new ClientReceiver(socket);
                Thread receiverThread = new Thread(receiver);

                receiverThread.start();

                while (!clientMessage.equalsIgnoreCase("Q")) {

                    System.out.print("You: ");
                    clientMessage = kb.nextLine();

                    out.println(clientMessage);
                        
                }
                
                try 
                {
                    if (out != null) out.close();
                    if (in != null) in.close();
                    if (socket != null) socket.close();
                    kb.close();
                }
                catch (IOException e) 
                {
                    System.err.println("Error: " + e.getMessage());
                }
        }
                
    public static void main(String[] args) {
        JavaClient client = new JavaClient(8080, "127.0.0.1");

    }
}