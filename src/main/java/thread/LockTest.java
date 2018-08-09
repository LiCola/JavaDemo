package thread;

import com.licola.llogger.LLogger;

import java.util.Random;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by LiCola on 2017/8/19.
 */
public class LockTest {

  public static final void main(String[] args) throws InterruptedException {
    testCounter();
//    testLockSupport();
//    testLockOperate();

  }

  private static void testLockOperate() {
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    lock.writeLock().lock();
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
    Random random = new Random();

    LLogger.d("counter :user lock");
    for (int i = 0; i < length; i++) {
      threads[i] = new Thread(new RunnableCounter(true, counter, random.nextInt(10) * 10));
    }

    startThreadsAncCountTime(counter, length, threads);

//        LLogger.d("counter :user syn");
//    for (int i = 0; i < length; i++) {
//      threads[i] = new Thread(new RunnableCounter(false, counter, sleepTimes[i]));
//    }
//    startThreadsAncCountTime(counter, length, threads);

  }

  private static void startThreadsAncCountTime(Counter counter, int length, Thread[] threads)
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
//      LLogger.d("i has sleep time:" + sleepTime+" i see:"+counter.getCount());
      if (freeLock) {
        counter.increase();
      } else {
        counter.increaseSyn();
      }
    }
  }

  public static class Counter {

    private final ReentrantLock lock = new ReentrantLock();
    private int count;

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
