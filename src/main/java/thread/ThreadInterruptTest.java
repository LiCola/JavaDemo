package thread;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by LiCola on 2017/8/19.
 */
public class ThreadInterruptTest {

  public static final void main(String[] args) throws InterruptedException {
//    interruptRunnable();
//    interruptWaiting();
    interruptBlockedSyn();
//    interruptBlockedLock();
//    interruptIOStream();
  }

  /**
   * 显式锁lock 当子线程获取不到锁时 能够响应外部的中断设置
   * lock能响应中断的本质是，lock的实现方式没有让线程进入阻塞状态，而是等待状态
   */
  private static void interruptBlockedLock() {

    Lock lock = new ReentrantLock();

    class ThreadA extends Thread {


      @Override
      public void run() {

        System.out.println("线程A 进入lock锁代码块 之前");
        try {
          lock.lockInterruptibly();
          System.out.println("线程A 获取到lock锁");
        } catch (InterruptedException e) {
          e.printStackTrace();
          System.out.println("A 线程响应中断");
        } finally {
          lock.unlock();
        }
      }
    }

    ThreadA threadA = new ThreadA();

    try {
      lock.lock();
      threadA.start();
      Thread.sleep(1000);
      threadA.interrupt();
//      threadA.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      System.out.println("main线程运行结束");
      lock.unlock();
    }


  }

  /**
   * 阻塞状态的线程，无法响应中断。
   * 这也是一段死锁代码，满足死锁发生的4个条件。
   */
  private static void interruptBlockedSyn() throws InterruptedException {

    final Object lockObj = new Object();

    class ThreadA extends Thread {

      @Override
      public void run() {
        System.out.println("entry synchronize block before");
        synchronized (lockObj) {
          System.out.println("run in synchronized block ");
          while (!Thread.currentThread().isInterrupted()) {
            System.out.println("run in synchronized block and not interrupt");
          }
        }
        System.out.println("exit synchronize block");
      }
    }

    synchronized (lockObj) {
      ThreadA threadA = new ThreadA();
      threadA.start();
      Thread.sleep(1000);

      threadA.interrupt();
//      threadA.join();
    }
  }


  private static class IOStreamThread extends Thread {

    @Override
    public void run() {
      while (!isInterrupted()) {
        try {
          System.out.println(System.in.read());
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      System.out.println("exit read io");
    }

    public void cancel() {
      try {
        System.in.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
      interrupt();
    }
  }

  private static void interruptIOStream() throws InterruptedException {
    IOStreamThread ioStreamThread = new IOStreamThread();
    ioStreamThread.start();

    Thread.sleep(100);
    ioStreamThread.cancel();
  }

  /**
   * Runnable运行状态 线程已经得到CPU时间片能够运行：
   * 如果线程在运行中，且没有IO操作
   * 调用interrupt方法只是会设置显现中断标志位，没有任何作用。
   * 在线程运行过程中，应该在合适的位置检查中断标志位，响应外部设置的中断操作
   */
  private static void interruptRunnable() throws InterruptedException {
    Thread thread = new Thread() {
      @Override
      public void run() {
        while (!isInterrupted()) {

        }
        System.out.println("done work");
      }
    };

    thread.start();
    Thread.sleep(100);
    thread.interrupt();
  }


  /**
   * Waiting/time_waiting等待状态 线程不会被分配CPU时间片，
   * 由其他线程唤醒（notify等）或到时由系统调度唤醒
   * 典型如sleep就会让线程进入限时等待状态。
   * 调用interrupt方法会使得线程抛出InterruptedException中断异常，
   * 特殊的抛出异常后，中断标志位被清空
   */
  public static void interruptWaiting() {
    Thread thread = new Thread() {
      @Override
      public void run() {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
          System.out.println(isInterrupted());
        }
      }
    };
    thread.start();

    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    thread.interrupt();
  }
}
