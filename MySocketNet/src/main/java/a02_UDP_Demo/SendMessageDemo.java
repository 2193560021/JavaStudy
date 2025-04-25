package a02_UDP_Demo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class SendMessageDemo {
    public static void main(String[] args) throws Exception {
        //1.创建对象
        DatagramSocket ds = new DatagramSocket();

        String val = "Hello LYY!";
        byte[] bytes = val.getBytes();
        InetAddress address = InetAddress.getByName("LYY");
        int port = 10086;
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length, address, port);
        ds.send(dp);

        ds.close();

    }
}
