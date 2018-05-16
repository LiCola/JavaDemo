package thread;

/**
 * Created by LiCola on 2017/11/21.
 */
public class VolatileTest implements Runnable {

  /**
   * volatile 保证内存可见性。
   * 如果没有修饰，其他线程每次while读取的都是自己工作内存的旧值。代码块无法进入退出输出。
   * 如果有修饰，主线程对volatile变量的修改，会导致其他线程的工作内存中的该volatile拷贝值的无效，会重新取值
   * volatile只能保证内存可见性，不保证变量的原子操作，即无法在依赖旧值的情况线程安全的更新值
   */
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
