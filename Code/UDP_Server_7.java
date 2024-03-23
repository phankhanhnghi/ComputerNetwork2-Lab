import java.io.*;
import java.net.*;

public class UDP_Server_7 {
    private static final int PORT = 9876; // Port number for the server

    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(PORT);
            System.out.println("UDP Server is running...");

            while (true) {
                byte[] receiveData = new byte[1024];
                byte[] sendData = new byte[1024];

                // Receive request from client
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());

                // Process client's request
                String responseMessage = processRequest(receivedMessage);

                // Send response to client
                sendData = responseMessage.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String processRequest(String request) {
        String[] menuOptions = {"1. Input two integer numbers",
                                "2. Maximum value of two numbers",
                                "3. Minimum value of two numbers",
                                "4. Exit"};

        if (request.equals("1")) {
            return menuOptions[0];
        } else if (request.equals("2") || request.equals("3")) {
            return "Please enter two integers firstly";
        } else if (request.equals("4")) {
            return menuOptions[3];
        } else {
            return "Invalid option";
        }
    }
}