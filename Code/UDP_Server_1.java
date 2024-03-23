
import java.io.*;
import java.net.*;

public class UDP_Server_1 {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(1234);
            Socket con = ss.accept();
            BufferedReader in= new BufferedReader(new InputStreamReader
            (con.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter
            (con.getOutputStream()));
            String rdata = in.readLine();
            System.out.println(rdata);
            out.write(rdata.toUpperCase()+"\n");
            out.flush();
            con.close();
            ss.close();
            }
            catch (Exception e) {}
    }
}