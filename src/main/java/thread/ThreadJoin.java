package thread; import com.licola.llogger.LLogger;

/**
 * Created by LiCola on 2018/5/2.
 */
public class ThreadJoin {

  public static final void main(String[] args) throws InterruptedException {

    Thread thread1 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        LLogger.d("thread finish 1:" + Thread.currentThread());
      }
    });


    Thread thread2=new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          thread1.join();
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        LLogger.d("thread finish 2:" + Thread.currentThread());
      }
    });

    thread1.start();
    thread2.start();
    thread2.join();//

    LLogger.d("main join finish");
  }
}
