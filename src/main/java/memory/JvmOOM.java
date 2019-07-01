package memory;

import com.licola.llogger.LLogger;
import java.util.ArrayList;
import java.util.List;

/**
 * -Xms16m -Xmx32m -XX:+HeapDumpOnOutOfMemoryError
 * 开启两个线程，一个线程会发生成OutOfMemoryError堆内存溢出
 * 另一个线程只打印表示运行状态。
 * 简单说一个线程异常退出不会影响其他线程
 * @author LiCola
 * @date 2019-06-26
 */
public class JvmOOM {

  public static void main(String[] args) {

    LLogger.init();

    new Thread(() -> {
      List<byte[]> list = new ArrayList<>();

      while (true) {
        byte[] bytes = new byte[1 << 20];
        list.add(bytes);
        LLogger.d("写线程运行");
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }

    },"Thread-OOM").start();

    new Thread(() -> {

      while (true) {
        LLogger.d("其他线程运行");
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }

    },"Thread-Other").start();
  }
}
