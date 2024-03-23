import java.io.*;
import java.net.*;

public class UDP_Client_5 {
    private static final String SERVER_IP = "localhost";
    private static final int PORT = 12345; // Port number for the server

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_IP, PORT);

            // Input stream to receive messages from server
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Output stream to send messages to server
            PrintWriter outToServer = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader clientReader = new BufferedReader(new InputStreamReader(System.in));

            String clientMessage;
            String serverMessage;

            while (true) {
                // Client inputs message to send to server
                System.out.print("Client: ");
                clientMessage = clientReader.readLine();
                outToServer.println(clientMessage);

                // Check if client wants to end conversation
                if (clientMessage.equalsIgnoreCase("bye")) {
                    break;
                }

                // Receive message from server
                serverMessage = inFromServer.readLine();
                System.out.println("Server: " + serverMessage);

                // Check if server wants to end conversation
                if (serverMessage.equalsIgnoreCase("bye")) {
                    break;
                }
            }

            // Close socket
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
