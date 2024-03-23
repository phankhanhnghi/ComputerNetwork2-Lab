import java.io.*;
import java.net.*;

public class TCP_Client_1 {
    private static final String SERVER_IP = "localhost"; // Server IP address
    private static final int SERVER_PORT = 12345; // Port number for the server

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter outToServer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Read string from user
            System.out.print("Enter a string: ");
            String inputString = reader.readLine();

            // Send the string to the server
            outToServer.println(inputString);

            // Receive the converted string from the server
            String convertedString = inFromServer.readLine();
            System.out.println("Received from server: " + convertedString);

            // Close the socket
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
