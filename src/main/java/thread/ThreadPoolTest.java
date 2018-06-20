package thread;

import com.licola.llogger.LLogger;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by LiCola on 2017/12/7.
 */
public class ThreadPoolTest {

  public static final void main(String[] args) throws InterruptedException {

//    threadPoolDeadlock();

    threadLarge();

  }

  private static void threadLarge() {

    ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, 5, 1000,
        TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(5));

    int size = 10;
    for (int i = 0; i < size; i++) {
      threadPool.execute(new Runnable() {
        @Override
        public void run() {
          long time = (long) (Math.random() * 1000);
          LLogger.d("sleep time:" + time);
          try {
            Thread.sleep(time);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      });
    }

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

  private static void threadPoolDeadlock()
      throws InterruptedException {

    //single线程池 因为任何时刻都只能有一个任务运行，如果运行中的任务和新任务关联 就会锁死
    ExecutorService threadExecutor = Executors.newSingleThreadExecutor();

    //    ExecutorService threadExecutor = Executors.newCachedThreadPool();

    int workNumber = 2;
    for (int i = 0; i < workNumber; i++) {
      threadExecutor.execute(new TaskA(i, threadExecutor));
    }
//    executorService.shutdown();
  }


}
