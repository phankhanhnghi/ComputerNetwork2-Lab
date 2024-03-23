import java.io.*;
import java.net.*;

public class TCP_Server_1 {
    private static final int PORT = 12345; // Port number for the server

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("TCP Server is running...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);

                BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter outToClient = new PrintWriter(clientSocket.getOutputStream(), true);

                // Read string from client
                String receivedString = inFromClient.readLine();
                System.out.println("Received from client: " + receivedString);

                // Convert the string to uppercase
                String upperCaseString = receivedString.toUpperCase();

                // Send the uppercase string back to the client
                outToClient.println(upperCaseString);

                // Close the client socket
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
