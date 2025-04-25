package a03_UDP_Demo2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class SendMessageDemo {
    public static void main(String[] args) throws Exception {
        //1.创建对象
        DatagramSocket ds = new DatagramSocket();

        Scanner in = new Scanner(System.in);

        while (true) {
            String str = in.nextLine();
            if("886".equals(str)){
                break;
            }
            byte[] bytes = str.getBytes();
            InetAddress address = InetAddress.getByName("LYY");
            int port = 10086;
            DatagramPacket dp = new DatagramPacket(bytes, bytes.length, address, port);

            ds.send(dp);
        }

        ds.close();

    }
}
