import java.io.*;
import java.net.*;

public class UDP_Server_3 {
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
                
                int num1 = dataInputStream.readInt();
                int num2 = dataInputStream.readInt();
                char operation = dataInputStream.readChar();

                int result = performOperation(num1, num2, operation);

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                dataOutputStream.writeInt(result);

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

    private static int performOperation(int num1, int num2, char operation) {
        int result = 0;
        switch (operation) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 != 0)
                    result = num1 / num2;
                else
                    System.out.println("Cannot divide by zero.");
                break;
            default:
                System.out.println("Invalid operation.");
        }
        return result;
    }
}