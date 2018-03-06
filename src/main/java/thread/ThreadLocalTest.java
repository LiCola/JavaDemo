package thread;

/**
 * Created by LiCola on 2017/10/13.
 */
public class ThreadLocalTest {

  static ThreadLocal<Integer> local = new ThreadLocal<>();

  public static final void main(String[] args) throws InterruptedException {
    Thread childThread = new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println("child thread local init value:" + local.get());
        local.set(200);
        System.out.println("child thread local value:" + local.get());
      }
    });

    local.set(100);
    childThread.start();
    childThread.join();

    System.out.println("main thread local:" + local.get());

//    ThreadLocalRandom threadLocalRandom=ThreadLocalRandom.current();
  }


}