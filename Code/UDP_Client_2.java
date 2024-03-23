import java.io.*;
import java.net.*;

public class UDP_Client_2 {
    public static void main(String args[]) {
        DatagramSocket clientSocket = null;
        try {
            clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName("localhost");

            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter three integers (a, b, c) separated by space: ");
            String input = inFromUser.readLine();
            String[] numbers = input.split(" ");
            int a = Integer.parseInt(numbers[0]);
            int b = Integer.parseInt(numbers[1]);
            int c = Integer.parseInt(numbers[2]);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeInt(a);
            dataOutputStream.writeInt(b);
            dataOutputStream.writeInt(c);

            byte[] sendData = outputStream.toByteArray();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            clientSocket.send(sendPacket);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(receivePacket.getData()));
            int lcm = dataInputStream.readInt();
            System.out.println("Least Common Multiple received from Server: " + lcm);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (clientSocket != null)
                clientSocket.close();
        }
    }
}