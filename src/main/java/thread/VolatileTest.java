package thread;

/**
 * Created by LiCola on 2017/11/21.
 */
public class VolatileTest implements Runnable {

  private volatile boolean isRunning = true;

  public boolean isRunning() {
    return isRunning;
  }

  public void setRunning(boolean running) {
    isRunning = running;
  }

  public static final void main(String[] args) throws InterruptedException {
    VolatileTest volatileTest = new VolatileTest();
    new Thread(volatileTest).start();
    Thread.sleep(1000);
    volatileTest.setRunning(false);
  }

  @Override
  public void run() {
    System.out.println("进入 run 代码块");
    while (isRunning()) {
    }
    System.out.println("isRunning 被设置为 false 线程终止");
  }
}
