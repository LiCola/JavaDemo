package thread; import com.licola.llogger.LLogger;
import java.util.Random;

/**
 * Created by LiCola on 2017/10/13.
 */
public class ThreadLocalTest {

   static ThreadLocal<Integer> localInt = new ThreadLocal<Integer>(){
    @Override
    protected Integer initialValue() {
      LLogger.d("initialValue 有机会给get null 的情况 返回初始值");
      return super.initialValue();
    }
  };

   static ThreadLocal<String> localStr=new ThreadLocal<>();

  public static final void main(String[] args) throws InterruptedException {
    threadsGetAndSet();

    Random random=new Random();
//    ThreadLocalRandom threadLocalRandom=ThreadLocalRandom.current();
  }

  private static void threadsGetAndSet() throws InterruptedException {
    Thread childThread = new Thread(new Runnable() {
      @Override
      public void run() {
        LLogger.d("child thread localInt init value:" + localInt.get());
        localStr.set("子线程 也拥有自己的本地变量 而且是多个");
        localInt.set(200);
        LLogger.d("child thread localInt value:" + localInt.get());
        LLogger.d("child thread localStr value:" + localStr.get());
      }
    });

    localStr.set("每个线程都能和ThreadLocal线程本地变量关联起来，而且一个线程可以关联多个ThreadLocal");
    localInt.set(100);
    childThread.start();
    childThread.join();

    LLogger.d("main thread localInt:" + localInt.get());
    LLogger.d("main thread localStr:"+localStr.get());
  }


}
