package a03_UDP_Demo2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ReceiveMessageDemo {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket(10086);

        while (true) {
            byte[] bytes = new byte[1024];
            DatagramPacket dp = new DatagramPacket(bytes, bytes.length);
            ds.receive(dp);

            byte[] data = dp.getData();
            int len = dp.getLength();
            String receiveData = new String(data, 0, len);
            if("886".equals(receiveData.substring(0, len))) break;
            InetAddress address = dp.getAddress();
            int port = dp.getPort();
            String name = address.getHostName();
            String ip = address.getHostAddress();

            System.out.println("ip为：" + ip + "， 主机名为" + name + "，端口为：" + port + "的人，发送了数据:" + receiveData);
        }
        ds.close();


    }
}
