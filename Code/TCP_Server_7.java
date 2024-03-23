import java.io.*;
import java.net.*;

public class TCP_Server_7 {
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

                // Send menu options to client
                outToClient.println("Menu:");
                outToClient.println("1. Input two integer numbers");
                outToClient.println("2. Maximum value of two numbers");
                outToClient.println("3. Minimum value of two numbers");
                outToClient.println("4. Exit");
                outToClient.println("Enter your choice:");

                // Read choice from client
                String choice = inFromClient.readLine();

                // Process client's choice
                String result = processChoice(choice, inFromClient);

                // Send result to client
                outToClient.println(result);

                // Close the client socket
                clientSocket.close();

                // Exit if client chose to exit
                if (choice.equals("4")) {
                    break;
                }
            }
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String processChoice(String choice, BufferedReader inFromClient) throws IOException {
        switch (choice) {
            case "1":
                // Receive two integers from client
                int a = Integer.parseInt(inFromClient.readLine());
                int b = Integer.parseInt(inFromClient.readLine());
                return "Received two integers from client: " + a + " and " + b;
            case "2":
                // Check if two integers were received earlier
                return "Maximum value of two numbers";
            case "3":
                // Check if two integers were received earlier
                return "Minimum value of two numbers";
            case "4":
                return "Exiting...";
            default:
                return "Invalid choice";
        }
    }
}
