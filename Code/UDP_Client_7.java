import java.io.*;
import java.net.*;

public class UDP_Client_7 {
    private static final int SERVER_PORT = 9876; // Port number for the server
    private static final String SERVER_IP = "localhost"; // Server IP address

    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            InetAddress serverAddress = InetAddress.getByName(SERVER_IP);

            while (true) {
                // Send request to server
                System.out.println("Please select an option:");
                System.out.println("1. Input two integer numbers");
                System.out.println("2. Maximum value of two numbers");
                System.out.println("3. Minimum value of two numbers");
                System.out.println("4. Exit");

                String option = reader.readLine();
                byte[] sendData = option.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, SERVER_PORT);
                clientSocket.send(sendPacket);

                // Receive response from server
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);

                String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Server response: " + receivedMessage);

                if (receivedMessage.equals("4. Exit")) {
                    break;
                }
            }

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}