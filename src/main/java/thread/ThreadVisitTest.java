package thread;

/**
 * Created by LiCola on 2018/4/3.
 */
public class ThreadVisitTest implements Runnable {

  int NUM;

  public static final void main(String[] args) throws InterruptedException {
//    testThreadVisit();

    Thread[] threads=new Thread[50];
    for (int i = 0; i < threads.length; i++) {
      ThreadVisitTest visitTest = new ThreadVisitTest();
      threads[i]=visitTest.testVolatileOrder();
    }

    for (Thread item :
        threads) {
      item.start();
    }
  }

  private int number = 0;
  private boolean isinited;

  private Thread testVolatileOrder() {
    Thread thread= new Thread(new Runnable() {
      @Override
      public void run() {
        while (!isinited) {
          System.out.println(Thread.currentThread() + " 还没初始化");
          Thread.yield();
        }
        int v = number;
        if (v != 20) {
          throw new RuntimeException("异常的访问数据");
        }
        System.out.println("thread+" + Thread.currentThread() + " 访问到 number:" + number);
      }
    });

    number = 20;
    isinited = true;

    return thread;
  }

  private static void testThreadVisit() {
    Thread[] threads = new Thread[30];
    Runnable runnable = new ThreadVisitTest();
    for (int i = 0, size = threads.length; i < size; i++) {
      threads[i] = new Thread(runnable);
      threads[i].start();
    }
  }

  @Override
  public void run() {
    NUM = 3;
    System.out.println("thread:" + Thread.currentThread() + " first num:" + NUM);
    NUM = 5;
    System.out.println("thread:" + Thread.currentThread() + " second num:" + NUM);
  }
}
