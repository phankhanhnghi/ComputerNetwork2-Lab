import java.io.*;
import java.net.*;

public class TCP_Client_3 {
    private static final String SERVER_IP = "localhost"; // Server IP address
    private static final int SERVER_PORT = 12345; // Port number for the server

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter outToServer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Read two integers and arithmetic operation from user
            System.out.print("Enter the first integer: ");
            int num1 = Integer.parseInt(reader.readLine());
            System.out.print("Enter the second integer: ");
            int num2 = Integer.parseInt(reader.readLine());
            System.out.print("Enter the arithmetic operation (+, -, *, /): ");
            String operation = reader.readLine();

            // Send the integers and arithmetic operation to the server
            outToServer.println(num1);
            outToServer.println(num2);
            outToServer.println(operation);

            // Receive the result from the server
            String result = inFromServer.readLine();
            System.out.println("Received from server: " + result);

            // Close the socket
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}