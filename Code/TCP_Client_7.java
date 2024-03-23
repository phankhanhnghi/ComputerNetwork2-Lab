package TCP;

import java.io.*;
import java.net.*;

public class TCP_Client_7 {
    private static final String SERVER_IP = "localhost"; // Server IP address
    private static final int SERVER_PORT = 12345; // Port number for the server

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter outToServer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String serverMessage;

            // Receive and print menu from server
            while ((serverMessage = inFromServer.readLine()) != null) {
                System.out.println(serverMessage);

                // Read choice from user
                System.out.print("Enter your choice: ");
                String choice = reader.readLine();

                // Send choice to server
                outToServer.println(choice);

                // Receive and print result from server
                serverMessage = inFromServer.readLine();
                System.out.println("Server response: " + serverMessage);

                // Exit if user chose to exit
                if (choice.equals("4")) {
                    break;
                }
            }

            // Close the socket
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}