import java.io.*;
import java.net.*;

public class TCP_Server_2 {
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

                // Calculate LCM of a and b
                int lcm = calculateLCM(a, b);

                // Send the LCM back to the client
                outToClient.println(lcm);

                // Close the client socket
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int calculateLCM(int a, int b) {
        return a * (b / gcd(a, b));
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
