import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class TCP_Server {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(6789);
            Socket con = ss.accept();
            DataInputStream in = new DataInputStream(con.getInputStream());
            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            int number1 = in.readInt();
            int number2 = in.readInt();

            int result= number1 - number2;
            out.writeInt(result);
            
            con.close();
            ss.close();
            }catch (Exception e) {}
    }
}