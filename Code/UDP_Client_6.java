import java.net.*;

public class UDP_Client_6 {
    private static final int SERVER_PORT = 9876; // Port number for the server
    private static final String SERVER_IP = "localhost"; // Server IP address

    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();

            // Sending 2 integers a and b to the server
            int a = 2;
            int b = 9;
            String sendData = a + "," + b;
            DatagramPacket sendPacket = new DatagramPacket(sendData.getBytes(), sendData.getBytes().length,
                    InetAddress.getByName(SERVER_IP), SERVER_PORT);
            clientSocket.send(sendPacket);

            // Receiving even numbers from the server
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            while (true) {
                clientSocket.receive(receivePacket);
                String receivedData = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received from server: " + receivedData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}