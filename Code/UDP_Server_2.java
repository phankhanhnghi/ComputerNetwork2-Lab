import java.io.*;
import java.net.*;

public class UDP_Server_2 {
    public static void main(String args[]) {
        DatagramSocket serverSocket = null;
        try {
            serverSocket = new DatagramSocket(9876);

            byte[] receiveData = new byte[1024];
            byte[] sendData;

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                ByteArrayInputStream inputStream = new ByteArrayInputStream(receivePacket.getData());
                DataInputStream dataInputStream = new DataInputStream(inputStream);
                int a = dataInputStream.readInt();
                int b = dataInputStream.readInt();
                int c = dataInputStream.readInt();

                int lcm = calculateLCM(a, b, c);

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                dataOutputStream.writeInt(lcm);

                sendData = outputStream.toByteArray();

                InetAddress IPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                serverSocket.send(sendPacket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null)
                serverSocket.close();
        }
    
}

private static int calculateLCM(int a, int b, int c) {
    return lcm(lcm(a, b), c);
}

private static int lcm(int a, int b) {
    return a * (b / gcd(a, b));
}

private static int gcd(int a, int b) {
    while (b != 0) {
        int temp = b;
        b = a % b;
        a = temp;
    }
    return a;
}
}