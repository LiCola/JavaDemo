package io;

import com.licola.llogger.LLogger;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Random;

/**
 * 固定方式方式，20个线程发起的，随机休眠，向服务端写入1M数据的客户端
 * @author LiCola
 * @date 2019-05-28
 */
public class SocketClient {

  public static void main(String[] args) throws IOException {
    LLogger.init();

    for (int i = 0; i < 20; i++) {
      Socket socket = new Socket();
      socket.connect(new InetSocketAddress("localhost",8080));
      processWithNewThread(socket,i);
    }
  }

  private static void processWithNewThread(Socket socket, int index) {
    Runnable runnable=()->{
      try {
        //睡眠随机的5-10秒，模拟数据尚未就绪
        Thread.sleep((new Random().nextInt(6) + 5) * 1000);
        //写1M数据，为了拉长服务器端读数据的过程
        socket.getOutputStream().write(prepareBytes());
        //睡眠1秒，让服务器端把数据读完
        Thread.sleep(1000);
        socket.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    };
    new Thread(runnable).start();
  }

  static byte[] prepareBytes() {
    byte[] bytes = new byte[1024*1024*1];
    for (int i = 0; i < bytes.length; i++) {
      bytes[i] = 1;
    }
    return bytes;
  }
}
