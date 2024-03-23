import java.io.*;
import java.net.*;

public class UDP_Client_3 {
    public static void main(String args[]) {
        DatagramSocket clientSocket = null;
        try {
            clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName("localhost");

            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter two integers and arithmetic operation (+, -, *, /) separated by space: ");
            String input = inFromUser.readLine();
            String[] data = input.split(" ");
            
            int num1 = Integer.parseInt(data[0]);
            int num2 = Integer.parseInt(data[1]);
            char operation = data[2].charAt(0);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeInt(num1);
            dataOutputStream.writeInt(num2);
            dataOutputStream.writeChar(operation);

            byte[] sendData = outputStream.toByteArray();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            clientSocket.send(sendPacket);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(receivePacket.getData()));
            int result = dataInputStream.readInt();
            System.out.println("Result received from Server: " + result);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (clientSocket != null)
                clientSocket.close();
        }
    }
}