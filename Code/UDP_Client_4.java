import java.net.*;

public class UDP_Client_4 {
    private static final int PORT = 7; // Port number for the Echo Protocol

    public static void main(String[] args) {
        try {
            InetAddress serverAddress = InetAddress.getByName("localhost");
            DatagramSocket clientSocket = new DatagramSocket();

            // Message to be sent to the server
            String message = "Hello, UDP Echo Server!";
            byte[] sendData = message.getBytes();

            // Send the message to the server
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, PORT);
            clientSocket.send(sendPacket);

            // Receive the echoed message from the server
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            // Convert the received data to string
            String echoedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Received from server: " + echoedMessage);

            // Close the socket
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
