import java.io.*;
import java.net.*;

public class TCP_Server_4 {
    private static final int PORT = 7; // Port number for the Echo Protocol

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("TCP Echo Server is running...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);

                BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter outToClient = new PrintWriter(clientSocket.getOutputStream(), true);

                // Read data from client
                String receivedData = inFromClient.readLine();
                System.out.println("Received from client: " + receivedData);

                // Echo back to the client
                outToClient.println(receivedData);

                // Close the client socket
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
