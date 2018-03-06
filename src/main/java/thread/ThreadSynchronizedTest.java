package thread;

/**
 * Created by LiCola on 2017/8/10.
 */
public class ThreadSynchronizedTest {

  private int targetData = 0;

  /**
   * 实例方法级的同步锁
   */
  public synchronized void increase() {
    targetData++;
  }

  public synchronized void reduce() {
    targetData--;
  }

  public static final void main(String[] args) throws InterruptedException {
//    testThreadCounter();

    /**
     * 新开两个线程 故意产生死锁
     */
//    startThreadEachLockA();
//    startThreadEachLockB();

    testInstanceMethodLock();

  }

  private static void testInstanceMethodLock() throws InterruptedException {
    ThreadSynchronizedTest synchronizedTest = new ThreadSynchronizedTest();

    int size=100;
    Thread[] threadIncreases=new Thread[size];
    Thread[] threadReduce=new Thread[size];

    for (int i = 0; i < size; i++) {
      threadIncreases[i]=new Thread(new Runnable() {
        @Override
        public void run() {
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          synchronizedTest.increase();
        }
      });

      threadReduce[i]=new Thread(new Runnable() {
        @Override
        public void run() {
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          synchronizedTest.reduce();
        }
      });
    }

    for (int i = 0; i < size; i++) {
      threadIncreases[i].start();
      threadReduce[i].start();
    }

    for (int i = 0; i < size; i++) {
      threadIncreases[i].join();
      threadReduce[i].join();
    }

    System.out.println("instance targetData = " + synchronizedTest.targetData);

  }

  private static Object lockA = new Object();
  private static Object lockB = new Object();

  private static void startThreadEachLockA() {
    Thread aThread = new Thread() {

      @Override
      public void run() {
        synchronized (lockA) {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
          }
          synchronized (lockB) {
          }
        }
      }
    };
    aThread.start();
  }

  private static void startThreadEachLockB() {
    Thread bThread = new Thread() {
      @Override
      public void run() {
        synchronized (lockB) {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
          }
          synchronized (lockA) {
          }
        }
      }
    };
    bThread.start();
  }

  private static void testThreadCounter() throws InterruptedException {
    int number = 100;
    Counter counter = new Counter();
    Thread[] threads = new Thread[number];
    for (int i = 0; i < number; i++) {
      threads[i] = new CouterThread(counter);
      threads[i].start();
    }

//    for (int i = 0; i < number; i++) {
//      threads[i].join();//主线程等待子线程执行结束后才执行
//    }

    while (true) {//主线程轮询一样可以有等待效果
      boolean isWork = false;
      for (Thread thread : threads) {
        isWork = thread.isAlive() | isWork;
      }

      if (isWork) {
        System.out.println("threads still work");
      } else {
        System.out.println("counter result: " + counter.getCount());
        return;
      }
    }
  }

  private static class CouterThread extends Thread {

    Counter counter;

    public CouterThread(Counter counter) {
      super();
      this.counter = counter;
    }

    @Override
    public void run() {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      counter.increase();
    }
  }

  private static class Counter {

    private int count = 0;

    public synchronized void increase() {
      count++;
    }

    public synchronized int getCount() {
      return count;
    }
  }

}
