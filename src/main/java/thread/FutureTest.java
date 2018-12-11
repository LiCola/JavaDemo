package thread; import com.licola.llogger.LLogger;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by LiCola on 2017/10/11.
 */
public class FutureTest {


  public static final void main(String[] args) throws InterruptedException {
    workTaskInteger();
  }

  private static void workTaskInteger() throws InterruptedException {
    ExecutorService executorService= Executors.newSingleThreadExecutor();
    Future<Integer> integerFuture = executorService.submit(new TaskInteger());


    Thread.sleep(1000);
    

    LLogger.d("主线程等待");


    try {
      Integer result = integerFuture.get();
      LLogger.d("result:"+result);
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }

    executorService.shutdown();
  }


  private static class TaskInteger implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
      int sleepSeconds=new Random().nextInt(1000);
      LLogger.d(Thread.currentThread(),sleepSeconds);
      Thread.sleep(sleepSeconds);
      return sleepSeconds;
    }
  }
}
