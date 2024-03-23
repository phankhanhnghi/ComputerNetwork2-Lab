import java.io.*;
import java.net.*;

public class TCP_Client_4 {
    private static final String SERVER_IP = "localhost"; // Server IP address
    private static final int SERVER_PORT = 7; // Port number for the Echo Protocol

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter outToServer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Read data from user
            System.out.print("Enter a string: ");
            String inputData = reader.readLine();

            // Send data to server
            outToServer.println(inputData);

            // Receive echoed data from server
            String echoedData = inFromServer.readLine();
            System.out.println("Received from server: " + echoedData);

            // Close the socket
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
