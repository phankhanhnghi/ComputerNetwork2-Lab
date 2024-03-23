import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TCP_Client {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 6789);

            DataInputStream network_in = new DataInputStream(s.getInputStream());
            DataOutputStream network_out = new DataOutputStream(s.getOutputStream());

            Scanner keyboard = new Scanner(System.in);

            System.out.println("Please input two integers:");
            int data1 = keyboard.nextInt();
            int data2 = keyboard.nextInt();

            network_out.writeInt(data1);
            network_out.writeInt(data2);

            int result = network_in.readInt();
            System.out.println("Data from Server:" + result);

            s.close();
        } catch (Exception e) {
            e.printStackTrace(); // Print the exception stack trace for debugging
        }
    }
}
