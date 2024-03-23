import java.io.*;
import java.net.*;

public class UDP_Server_5 {
    private static final int PORT = 12345; // Port number for the server

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("TCP Chat Server is running...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected.");

            // Input stream to receive messages from client
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // Output stream to send messages to client
            PrintWriter outToClient = new PrintWriter(clientSocket.getOutputStream(), true);

            BufferedReader serverReader = new BufferedReader(new InputStreamReader(System.in));

            String clientMessage;
            String serverMessage;

            while (true) {
                // Receive message from client
                clientMessage = inFromClient.readLine();
                System.out.println("Client: " + clientMessage);

                // Check if client wants to end conversation
                if (clientMessage.equalsIgnoreCase("bye")) {
                    break;
                }

                // Server inputs message to send to client
                System.out.print("Server: ");
                serverMessage = serverReader.readLine();
                outToClient.println(serverMessage);

                // Check if server wants to end conversation
                if (serverMessage.equalsIgnoreCase("bye")) {
                    break;
                }
            }

            // Close sockets
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}