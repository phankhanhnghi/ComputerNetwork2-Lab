import java.io.*;
import java.net.*;

public class TCP_Client_2 {
    private static final String SERVER_IP = "localhost"; // Server IP address
    private static final int SERVER_PORT = 12345; // Port number for the server

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter outToServer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Read two integers from user
            System.out.print("Enter the first integer (a): ");
            int a = Integer.parseInt(reader.readLine());
            System.out.print("Enter the second integer (b): ");
            int b = Integer.parseInt(reader.readLine());

            // Send the integers to the server
            outToServer.println(a);
            outToServer.println(b);

            // Receive the LCM from the server
            int lcm = Integer.parseInt(inFromServer.readLine());
            System.out.println("Received from server: Least Common Multiple (LCM) of " + a + " and " + b + " is " + lcm);

            // Close the socket
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
