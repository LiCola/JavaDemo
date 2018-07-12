package socket;import com.licola.llogger.LLogger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by LiCola on 2018/3/29.
 */
public class SocketTCPServer {

  private static final int PORT = 9999;
  private List<Socket> mSockets = new ArrayList<>();
  private ServerSocket mServerSocket = null;

  private ExecutorService mExecutorService = null;
  private String receviceMsg;
  private String sendMsg;

  public static final void main(String[] args) {
    new SocketTCPServer();
  }

  public SocketTCPServer() {
    try {
      mServerSocket = new ServerSocket(PORT);
      mExecutorService = Executors.newCachedThreadPool();
      LLogger.d("服务器启动");
      Socket client;
      while (true) {
        client = mServerSocket.accept();
        mSockets.add(client);
        mExecutorService.execute(new ServiceRunnable(client));
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  class ServiceRunnable implements Runnable {

    private Socket socket;
    private BufferedReader in = null;
    private PrintWriter printWriter = null;


    public ServiceRunnable(Socket socket) {
      this.socket = socket;
      try {
        printWriter = new PrintWriter(
            new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8")), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

        printWriter.println("成功连接服务器 来自服务器");
        LLogger.d("成功连接服务器 ");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    @Override
    public void run() {
      try {
        while (true) {
          if ((receviceMsg = in.readLine()) != null) {
            LLogger.d(" receive:" + receviceMsg);
            if ("0".equals(receviceMsg)) {
              LLogger.d("客户端请求断开连接");
              printWriter.println("服务器断开连接 来自服务器");

              mSockets.remove(socket);
              in.close();
              socket.close();
              break;
            } else {
              sendMsg = "服务器接收 :" + receviceMsg + " 来自客户端";
              printWriter.println(sendMsg);
            }
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
      }

    }
  }
}
