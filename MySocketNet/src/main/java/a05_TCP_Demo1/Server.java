package a05_TCP_Demo1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {

        ServerSocket ss = new ServerSocket(10001);

        //监听客户端链接
        Socket socket = ss.accept();

        InputStream is = socket.getInputStream();
        BufferedReader isr = new BufferedReader(new InputStreamReader(is));
        int b;
        while ((b = isr.read()) != -1 ){
            System.out.println((char) b);
        }

        socket.close();
        ss.close();

    }
}
