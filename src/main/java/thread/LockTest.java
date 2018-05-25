package thread; import com.licola.llogger.LLogger;

import java.util.Random;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by LiCola on 2017/8/19.
 */
public class LockTest {

  public static final void main(String[] args) throws InterruptedException {
//    testCounter();
//    testLockSupport();
    testLockOperate();
  }

  private static void testLockOperate() {
    ReentrantReadWriteLock lock=new ReentrantReadWriteLock();
    lock.writeLock();
  }

  private static void testLockSupport() throws InterruptedException {
    Thread thread = new Thread() {
      @Override
      public void run() {
        LockSupport.park();
        LLogger.d("work thread exit");
      }
    };
    thread.start();
    Thread.sleep(1000);
    LockSupport.unpark(thread);
  }

  private static void testCounter() throws InterruptedException {
    Counter counter = new Counter();
    int length = 100;
    Thread[] threads = new Thread[length];
    long[] sleepTimes = new long[length];
    Random random = new Random();

    for (int i = 0; i < length; i++) {
      sleepTimes[i] = random.nextInt(10) * 10;
      threads[i] = new Thread(new RunnableCounter(true, counter, sleepTimes[i]));
    }

    testThreadsTime(counter, length, threads);

    for (int i = 0; i < length; i++) {
      threads[i] = new Thread(new RunnableCounter(false, counter, sleepTimes[i]));
    }
    testThreadsTime(counter, length, threads);

  }

  private static void testThreadsTime(Counter counter, int length, Thread[] threads)
      throws InterruptedException {
    long startTime = System.currentTimeMillis();
    for (int i = 0; i < length; i++) {
      threads[i].start();
    }

    for (int i = 0; i < length; i++) {
      threads[i].join();
    }

    long userTime = System.currentTimeMillis() - startTime;
    LLogger.d("counter :" + counter.getCount() + " userTime:" + userTime);
  }

  public static class RunnableCounter implements Runnable {

    boolean freeLock;
    Counter counter;
    long sleepTime;

    public RunnableCounter(boolean freeLock, Counter counter, long sleepTime) {
      this.counter = counter;
      this.sleepTime = sleepTime;
      this.freeLock = freeLock;
    }

    @Override
    public void run() {
      try {
        Thread.sleep(sleepTime);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      if (freeLock) {
        counter.increase();
      } else {
        counter.increaseSyn();
      }
    }
  }

  public static class Counter {

    private final ReentrantLock lock = new ReentrantLock();
    private volatile int count;

    void increase() {
      lock.lock();
      try {
        count++;
      } finally {
        lock.unlock();
      }
    }

    void increaseSyn() {
      synchronized (this) {
        count++;
      }
    }

    public int getCount() {
      return count;
    }
  }

}
