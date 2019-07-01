package thread;

import com.licola.llogger.LLogger;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created by LiCola on 2017/10/11.
 */
public class FutureTest {


  public static final void main(String[] args) throws InterruptedException, ExecutionException {
    LLogger.init();
//    future();
    completableFuture();
  }

  private static void completableFuture() throws ExecutionException, InterruptedException {
    CompletableFuture<String> completableFuture = CompletableFuture
        .supplyAsync(new Supplier<String>() {
          @Override
          public String get() {
            LLogger.d();
            try {
              Thread.sleep(1000);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
            return "async";
          }
        });

    //普通的阻塞式
//    String result = completableFuture.get();
//    LLogger.d(result);
    CompletableFuture<Void> voidCompletableFuture = completableFuture
        .thenAccept(new Consumer<String>() {
          @Override
          public void accept(String s) {
            LLogger.d(s);
          }
        });

    LLogger.d("get");
    voidCompletableFuture.get();
    LLogger.d("finish");
  }

  private static void future() throws InterruptedException {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Future<Integer> integerFuture = executorService.submit(new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        int sleepSeconds = new Random().nextInt(10000);
        LLogger.d(Thread.currentThread(), sleepSeconds);
        Thread.sleep(sleepSeconds);
        return sleepSeconds;
      }
    });

    LLogger.d("主线程等待结果 isDone:" + integerFuture.isDone() + " isCancelled:" + integerFuture
        .isCancelled());
    try {
      Integer result = integerFuture.get();
      LLogger.d("result:" + result + " isDone:" + integerFuture.isDone());
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }

    executorService.shutdown();


  }

}
