package socket;import com.licola.llogger.LLogger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by LiCola on 2018/3/30.
 */
public class SocketUDPServer {


  public static final void main(String[] args) throws SocketException {

    DatagramSocket datagramSocket=new DatagramSocket(8888);
  }

  
}
