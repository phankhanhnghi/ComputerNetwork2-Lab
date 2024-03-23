import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDP_Server {
    public static void main(String[] args) {
        try {
            DatagramSocket sv = new DatagramSocket(1234);
            byte buff1[] = new byte[256];
           
            DatagramPacket q = new DatagramPacket(buff1, buff1.length);
            sv.receive(q);
            
            String data = new String(q.getData()).trim();
            String kq = data.toUpperCase();
            
            byte buff2[] = new byte[256];
            buff2 = kq.getBytes();
            
            InetAddress addcl = q.getAddress();
            int portcl = q.getPort();
            
            DatagramPacket k = new DatagramPacket(buff2,buff2.length,addcl,portcl);
            
            sv.send(k);
            sv.close();
            }
            catch (Exception e) {} 
                }
}
