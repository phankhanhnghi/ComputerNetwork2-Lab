import java.io.*;
import java.net.*;

public class TCP_Server_6 {
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

                // Read two integers from client
                int a = Integer.parseInt(inFromClient.readLine());
                int b = Integer.parseInt(inFromClient.readLine());
                System.out.println("Received from client: " + a + " and " + b);

                // Send even numbers in the range [a, b] back to the client
                for (int i = a; i <= b; i++) {
                    if (i % 2 == 0) {
                        outToClient.println(i);
                    }
                }

                // Close the client socket
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
