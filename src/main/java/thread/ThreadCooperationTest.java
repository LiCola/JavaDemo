package thread; import com.licola.llogger.LLogger;

/**
 * Created by LiCola on 2017/8/10.
 */
public class ThreadCooperationTest {

  public static void main(String[] args) throws InterruptedException {
    testWaitThread();

  }


  public static class WaitThread extends Thread {

    private volatile boolean fire = false;

    @Override
    public void run() {
      try {
        synchronized (this) {
          while (!fire) {
            wait();
          }
        }
        LLogger.d(Thread.currentThread() + " fired!");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    }

    private synchronized void fire() {
      this.fire = true;
      notify();
    }
  }

  private static void testWaitThread() throws InterruptedException {
    WaitThread waitThread = new WaitThread();
    waitThread.start();
    Thread.sleep(100);
    LLogger.d("main thread fire");
    waitThread.fire();
  }
}
