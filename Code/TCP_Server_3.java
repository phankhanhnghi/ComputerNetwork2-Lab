import java.io.*;
import java.net.*;

public class TCP_Server_3 {
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

                // Read two integers and arithmetic operation from client
                int num1 = Integer.parseInt(inFromClient.readLine());
                int num2 = Integer.parseInt(inFromClient.readLine());
                String operation = inFromClient.readLine();
                System.out.println("Received from client: " + num1 + ", " + num2 + ", " + operation);

                // Perform the arithmetic operation
                int result = 0;
                switch (operation) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            outToClient.println("Division by zero is not allowed.");
                            clientSocket.close();
                            continue;
                        }
                        break;
                    default:
                        outToClient.println("Invalid operation.");
                        clientSocket.close();
                        continue;
                }

                // Send the result back to the client
                outToClient.println(result);

                // Close the client socket
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
