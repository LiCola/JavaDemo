package thread;

import com.licola.llogger.LLogger;
import java.util.Random;

/**
 * Created by LiCola on 2018/6/20.
 */
public class DeadLockTest {

  public static final void main(String[] args) {



    DeadLockObject deadLockObject = new DeadLockObject();

    new Thread(new Runnable() {
      @Override
      public void run() {
        LLogger.d("开始进入获取锁");
        deadLockObject.doWork();
      }
    }).start();

    new Thread(new Runnable() {
      @Override
      public void run() {
        LLogger.d("开始进入获取锁");
        deadLockObject.doWork();
      }
    }).start();
  }

  public static class DeadLockObject {

    public void doWork() {
      synchronized (this) {
        while (true) {

        }
      }
    }

  }
}
