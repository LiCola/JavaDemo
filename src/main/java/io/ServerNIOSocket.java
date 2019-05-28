package io;

import com.licola.llogger.LLogger;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author LiCola
 * @date 2019-05-28
 */
public class ServerNIOSocket {

  private static int clientCount = 0;
  private static AtomicInteger counter = new AtomicInteger(0);
  private static final ExecutorService executorService = Executors.newCachedThreadPool();

  public static void main(String[] args) throws IOException {

    LLogger.init();

    Selector selector = Selector.open();
    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
    serverSocketChannel.configureBlocking(false);
    serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    serverSocketChannel.bind(new InetSocketAddress("localhost", 8080));

    while (true) {
      selector.select();
      Set<SelectionKey> keys = selector.selectedKeys();
      Iterator<SelectionKey> iterator = keys.iterator();
      while (iterator.hasNext()) {
        SelectionKey key = iterator.next();
        iterator.remove();
        if (key.isAcceptable()) {
          ServerSocketChannel channel = (ServerSocketChannel) key.channel();
          SocketChannel socketChannel;
          while ((socketChannel = channel.accept()) != null) {
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
            InetSocketAddress inetSocketAddress = (InetSocketAddress) socketChannel.socket()
                .getRemoteSocketAddress();
            LLogger.d("host:" + inetSocketAddress.getHostName() + " port:" + inetSocketAddress
                .getPort() + " clint:" + (++clientCount));
          }
        } else if (key.isReadable()) {
          key.interestOps(key.interestOps() & (~SelectionKey.OP_READ));
          processWithNewThread((SocketChannel) key.channel(), key);
        }
      }
    }
  }



  private static void processWithNewThread(SocketChannel channel, SelectionKey key) {
    Runnable runnable = () -> {

      counter.incrementAndGet();

      try {
        String result = readBytes(channel);
        key.interestOps(key.interestOps() | SelectionKey.OP_READ);
        LLogger.d(result + ":" + counter.get());
        channel.close();
      } catch (Exception e) {
        e.printStackTrace();
      }

      counter.decrementAndGet();

    };

    executorService.submit(runnable);
  }

  static String readBytes(SocketChannel sc) throws Exception {
    long start = 0;
    int total = 0;
    int count = 0;
    ByteBuffer bb = ByteBuffer.allocate(1024);
    //开始读数据的时间
    long begin = System.currentTimeMillis();
    while ((count = sc.read(bb)) > -1) {
      if (start < 1) {
        //第一次读到数据的时间
        start = System.currentTimeMillis();
      }
      total += count;
      bb.clear();
    }
    //读完数据的时间
    long end = System.currentTimeMillis();
    return "wait=" + (start - begin) + "ms,read=" + (end - start) + "ms,total=" + total + "bs";
  }
}
