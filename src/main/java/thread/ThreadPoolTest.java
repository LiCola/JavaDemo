package thread; import com.licola.llogger.LLogger;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by LiCola on 2017/12/7.
 */
public class ThreadPoolTest {

  private static final int THREAD_SIZE = 5;
//  static ExecutorService executorService = Executors.newFixedThreadPool(THREAD_SIZE);//任务数>=线程数时 发生死锁
//  static ExecutorService executorService = Executors.newCachedThreadPool();//不限定线程数 不会死锁
  static ExecutorService executorService = new ThreadPoolExecutor(THREAD_SIZE,THREAD_SIZE<<1,10,
    TimeUnit.SECONDS,new LinkedBlockingDeque<>());



  public static final void main(String[] args) throws InterruptedException {
    threadPoolDeadlock();

    
  }


  private static class TaskA implements Runnable {

    private final int taskId;

    public TaskA(int taskId) {
      this.taskId = taskId;
    }

    @Override
    public void run() {
      LLogger.d(Thread.currentThread() +" task id:"+taskId+" run");
      try {
        Thread.sleep((long) (1000*Math.random()));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

//      Future<?> submit = executorService.submit(new TaskB());
//
//      try {
//        submit.get();
//      } catch (InterruptedException | ExecutionException e) {
//        e.printStackTrace();
//      }
      LLogger.d(Thread.currentThread() +" task id:"+taskId+" finish");
    }
  }

  private static class TaskB implements Runnable {

    @Override
    public void run() {
      LLogger.d(Thread.currentThread() + " task B execute and finish");
    }
  }

  private static void threadPoolDeadlock() throws InterruptedException {
    int workNumber=30;
    for (int i = 0; i < workNumber; i++) {
      executorService.execute(new TaskA(i));
    }
    executorService.shutdown();
  }


}
