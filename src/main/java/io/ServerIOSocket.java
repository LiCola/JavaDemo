package io;

import com.licola.llogger.LLogger;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 传统IO方式的响应服务端
 * @author LiCola
 * @date 2019-05-28
 */
public class ServerIOSocket {

  private static final AtomicInteger counter=new AtomicInteger(0);
  private static final ExecutorService executorService = Executors.newCachedThreadPool();

  public static void main(String[] args) throws IOException {

    LLogger.init();

    ServerSocket serverSocket = new ServerSocket();
    serverSocket.bind(new InetSocketAddress("localhost",8080));
    while (true){
      Socket socket = serverSocket.accept();
      processWithNewThread(socket);
    }
  }

  private static void processWithNewThread(Socket socket) {
    Runnable runnable=()->{
      InetSocketAddress socketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
      LLogger.d("host:"+socketAddress.getHostName()+" port:"+socketAddress.getPort()+":"+counter.incrementAndGet());
      try {
        String result= readBytes(socket.getInputStream());
        LLogger.d(result+":"+counter.getAndDecrement());
        socket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    };
    executorService.submit(runnable);
  }

  private static String readBytes(InputStream inputStream) throws IOException {

    long start=0;
    int total=0;
    int count=0;
    byte[] bytes = new byte[1024];

    long begin = System.currentTimeMillis();
    while ((count=inputStream.read(bytes))>-1){
      if (start<1){
        //第一次读取到数据
        start=System.currentTimeMillis();
      }
      total+=count;
    }

    long end = System.currentTimeMillis();
    return "wait=" + (start - begin) + "ms,read=" + (end - start) + "ms,total=" + total + "bs";
  }
}
