package a05_TCP_Demo1;

import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception {

        //TCP发送数据

        //创建Socket对象

        Socket socket = new Socket("127.0.0.1", 10001);

        OutputStream os = socket.getOutputStream();
        os.write("你好你好".getBytes());
        os.close();
        socket.close();
    }
}
