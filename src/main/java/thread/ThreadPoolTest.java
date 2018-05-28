package thread;

import com.licola.llogger.LLogger;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by LiCola on 2017/12/7.
 */
public class ThreadPoolTest {

  private static final int THREAD_SIZE = 5;
  //  static ExecutorService executorService = Executors.newFixedThreadPool(THREAD_SIZE);//任务数>=线程数时 发生死锁
//  static ExecutorService executorService = Executors.newCachedThreadPool();//不限定线程数 不会死锁
  static ExecutorService executorService = new ThreadPoolExecutor(THREAD_SIZE, THREAD_SIZE << 1, 10,
      TimeUnit.SECONDS, new LinkedBlockingDeque<>());


  public static final void main(String[] args) throws InterruptedException {
//    threadPoolDeadlock(Executors.newSingleThreadExecutor());

    ThreadPoolExecutor service = (ThreadPoolExecutor) Executors.newCachedThreadPool(new ThreadFactory() {
      @Override
      public Thread newThread(Runnable r) {
        LLogger.d("线程创建");
        Thread thread = new Thread(r);
        return thread;
      }
    });
    service.execute(new Runnable() {
      @Override
      public void run() {
//        LLogger.trace();
        LLogger.d("run 1");
      }
    });

    Thread.sleep(100);

    service.execute(new Runnable() {
      @Override
      public void run() {
//        LLogger.trace();
        LLogger.d("run 2");
      }
    });
//    service.execute(new Runnable() {
//      @Override
//      public void run() {
//        LLogger.d("run 3");
//      }
//    });
//    service.execute(new Runnable() {
//      @Override
//      public void run() {
////        LLogger.trace();
//        LLogger.d("run 3");
//      }
//    });
  }


  private static class TaskA implements Runnable {

    private final int taskId;
    private final ExecutorService executorService;

    public TaskA(int taskId, ExecutorService executorService) {
      this.taskId = taskId;
      this.executorService = executorService;
    }

    @Override
    public void run() {
      LLogger.d(" task id:" + taskId + " run");
      try {
        Thread.sleep((long) (1000 * Math.random()));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      Future<?> submit = executorService.submit(new TaskB());
//
      try {
        submit.get();
      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
      }
      LLogger.d(" task id:" + taskId + " finish");
    }
  }

  private static class TaskB implements Runnable {

    @Override
    public void run() {
      LLogger.d(" task B execute and finish");
    }
  }

  private static void threadPoolDeadlock(ExecutorService executorService)
      throws InterruptedException {
    int workNumber = 2;
    for (int i = 0; i < workNumber; i++) {
      executorService.execute(new TaskA(i, executorService));
    }
//    executorService.shutdown();
  }


}
