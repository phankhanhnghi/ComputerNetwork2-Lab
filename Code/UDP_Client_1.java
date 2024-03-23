import java.io.*;
import java.net.*;
import java.util.Scanner;

public class UDP_Client_1 {
    public static void main(String[] args) {
        
try{
    Socket s = new Socket("localhost",1234);
    BufferedReader Network_in = new BufferedReader(new InputStreamReader(s.getInputStream()));
    BufferedWriter Network_out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

    Scanner keyboard = new Scanner(System.in);
    System.out.println("Please input a string:");
    String data = keyboard.nextLine();

    Network_out.write(data+"\n");
    Network_out.flush();

    String result = Network_in.readLine();

    System.out.println("Data from Server:" + result);

    s.close();
    keyboard.close();
    }catch(Exception e){}
    }
}