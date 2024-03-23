import java.net.*;

public class UDP_Server_4 {
    private static final int PORT = 7; // Port number for the Echo Protocol

    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(PORT);
            System.out.println("UDP Echo Server is running...");

            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received from client: " + receivedMessage);

                // Echo back to the client
                DatagramPacket sendPacket = new DatagramPacket(receivePacket.getData(), receivePacket.getLength(),
                        clientAddress, clientPort);
                serverSocket.send(sendPacket);
                serverSocket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}