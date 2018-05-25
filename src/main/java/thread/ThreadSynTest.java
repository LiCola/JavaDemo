package thread; import com.licola.llogger.LLogger;

/**
 * Created by LiCola on 2018/3/23.
 */
public class ThreadSynTest implements Runnable {

  int b = 100;

  synchronized void m1() throws InterruptedException {
    b = 1000;
    Thread.sleep(500);//6
    LLogger.d(Thread.currentThread().toString() + " b:" + b);
  }

  synchronized void m2() throws InterruptedException {
    Thread.sleep(250);//5
    b = 2000;
  }

  public static final void main(String[] args) throws InterruptedException {
    for (int i = 0; i < 10; i++) {
      ThreadSynTest threadSynTest = new ThreadSynTest();
      Thread thread = new Thread(threadSynTest);//1

      thread.start();//2
      threadSynTest.m2();//3

      /**
       * 主线程 输出不定 可能是1000或2000
       * 本质就是主线程和子线程 的有锁同步代码 谁先执行问题
       * 在java中线程的调度由操作系统控制 线程的start只是通知开启 不确定马上执行
       * 所以主线程和子线程 都有可能先获取锁，而后面执行的代码最后决定输出结果
       */
      LLogger.d("time:" + i + " main thread b:" + threadSynTest.b);//4

      Thread.sleep(1000);
    }


  }


  @Override
  public void run() {
    try {
      m1();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
