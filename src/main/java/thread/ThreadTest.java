package thread; import com.licola.llogger.LLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by LiCola on 2017/8/10.
 */
public class ThreadTest {


  public static final void main(String[] args) throws InterruptedException {

//    testAtomicOperate();
//    testShareMemory();
    testShutdown();

//    int[] timesList=new int[]{10,10,100,1000,10000,100000,1000000,10000000,100000000};
//    for (int times :
//        timesList) {
//      testFori(times);
//    }
  }

  private static void testFori(int times) {
    long t0 = System.nanoTime();

    int k = 1;
    for (int i = 0; i < times ; i++) {
      k += k * k + i;
    }

    LLogger.d("user time:"+(System.nanoTime()-t0)* 1E-6+"ms for:"+times);
  }

  private volatile static boolean shutdown=false;//内存不可见 添加volatile修饰符 会让内存对其他线程可见

  static class HelloThread extends Thread {
    @Override
    public void run() {
      super.run();
      while(!shutdown){
        // do nothing
      }
      LLogger.d("exit hello");
    }
  }

  private static void testShutdown() throws InterruptedException {
    LLogger.d("HelloThread thread start");
    new HelloThread().start();
    Thread.sleep(1000);
    shutdown=true;
    LLogger.d("main thread exit");
  }

  private static int shared = 0;

  private static void incrShared() {
    shared++;
  }

  private static void testShareMemory() throws InterruptedException {
    List<String> stringList = new ArrayList<>();
    Thread childThread1 = new ChildThread(stringList);
    Thread childThread2 = new ChildThread(stringList);

    childThread1.start();
    childThread2.start();

    childThread1.join();
    childThread2.join();

    LLogger.d("shared: " + shared);
    LLogger.d("list: "+stringList);
  }

  private static class ChildThread extends Thread {

    List<String> list;

    public ChildThread(List<String> list) {
      super();
      this.list = list;
    }

    @Override
    public void run() {
      incrShared();
      list.add(Thread.currentThread().getName());
    }
  }


  AtomicInteger atomicInteger = new AtomicInteger(0);

  private static void testAtomicOperate() {
    Runtime runtime = Runtime.getRuntime();
    long totalMemory = runtime.totalMemory();
    long freeMemory = runtime.freeMemory();
    int availableProcessors = runtime.availableProcessors();
    LLogger.d(String
        .format("%s:%d,%s:%d,%s:%d", "availableProcessors", availableProcessors, "totalMemory",
            totalMemory, "freeMemory", freeMemory));

    ThreadTest threadTest = new ThreadTest();

    int size = 5;
    Thread[] threads = new Thread[size];
    for (int i = 0; i < size; i++) {
      threads[i] = new Thread(new Runnable() {
        @Override
        public void run() {

          LLogger.d(
              "running :" + Thread.currentThread() + " before:"
                  + threadTest.atomicInteger.get() + " alter:" + threadTest.atomicInteger
                  .incrementAndGet());

        }
      });
      threads[i].start();
    }

    while (true) {
      for (Thread thread : threads) {
        LLogger.d(
            thread.toString() + " status: " + thread.getState() + " isAlive: " + thread.isAlive()
                + " isInterrupted: " + thread.isInterrupted());
      }

      if (threadTest.atomicInteger.get() >= size) {
        LLogger.d("Thread data = " + threadTest.atomicInteger.toString());
        return;
      } else {
        LLogger.d("not calculate finish");
      }
    }
  }


}
