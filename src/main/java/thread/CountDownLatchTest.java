package thread;

import com.licola.llogger.LLogger;

import java.util.concurrent.CountDownLatch;

/**
 * Created by LiCola on 2018/3/5.
 */
public class CountDownLatchTest {

  public static final void main(String[] args) throws InterruptedException {
    testMainAwaitWork();
//    testWorkAwaitMain();
  }

  /**
   * 主从协作模式 主线程依赖子线程的结果，需要等待子线程们工作结束
   */
  private static void testWorkAwaitMain() throws InterruptedException {
    int num = 100;
    CountDownLatch latch = new CountDownLatch(num);
    Thread[] threads = new Thread[num];
    for (int i = 0; i < num; i++) {
      threads[i] = new Worker(latch);
      threads[i].start();
    }

    latch.await();//主线程 等待子线程的倒计数完成 主从协同
    LLogger.d("collect worker results");
  }


  /**
   * 主线程 统一唤起 子线程
   */
  private static void testMainAwaitWork() throws InterruptedException {
    int num = 10;
    CountDownLatch latch = new CountDownLatch(1);
    Thread[] threads = new Thread[num];
    for (int i = 0; i < num; i++) {
      threads[i] = new Racer(latch);
      threads[i].start();
    }


    LLogger.d("main will sleep after count down");
    Thread.sleep(1000);
    latch.countDown();
  }

  /**
   * 同时开始场景中，运行员线程等待主裁判线程发出开始指令的信号， 一旦发出后，所有运动员线程同时开始，计数初始为1，运动员线程调用await，主线程调用countDown，
   */
  public static class Racer extends Thread {

    CountDownLatch latch;

    public Racer(CountDownLatch latch) {
      this.latch = latch;
    }

    @Override
    public void run() {
//      LLogger.d(this + "entry await block");
      try {
        this.latch.await();
        LLogger.d(this + "await finish");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public static class Worker extends Thread {

    CountDownLatch latch;

    public Worker(CountDownLatch latch) {
      this.latch = latch;
    }

    @Override
    public void run() {
      try {
        Thread.sleep((long) (Math.random() * 1000));

        // simulate exception
        if (Math.random() < 0.02) {
          throw new RuntimeException("bad luck");
        }


      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        this.latch.countDown();
      }
    }
  }
}
