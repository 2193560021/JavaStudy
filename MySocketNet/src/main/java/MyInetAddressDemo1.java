import java.net.InetAddress;
import java.net.UnknownHostException;

public class MyInetAddressDemo1 {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress address = InetAddress.getByName("LYY");
        System.out.println("address = " + address);

        String name = address.getHostName();
        System.out.println("name = " + name);

        System.out.println(address.getHostAddress());
    }
}
