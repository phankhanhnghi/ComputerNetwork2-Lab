import java.net.*;

public class UDP_Server_6 {
    private static final int PORT = 9876; // Port number for the server

    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(PORT);
            System.out.println("UDP Server is running...");

            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                // Extracting the integers sent by the client
                String receivedData = new String(receivePacket.getData(), 0, receivePacket.getLength());
                String[] parts = receivedData.split(",");
                int a = Integer.parseInt(parts[0]);
                int b = Integer.parseInt(parts[1]);

                // Sending even numbers in the range [a, b] back to the client
                for (int i = a; i <= b; i++) {
                    if (i % 2 == 0) {
                        String sendData = String.valueOf(i);
                        DatagramPacket sendPacket = new DatagramPacket(sendData.getBytes(), sendData.getBytes().length,
                                clientAddress, clientPort);
                        serverSocket.send(sendPacket);
                        Thread.sleep(200); // To add a small delay between sending each number
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}