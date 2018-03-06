package thread;

/**
 * Created by LiCola on 2017/10/9.
 */
public class ThreadTest2 {

  private int data;

  public static final void main(String[] args) throws InterruptedException {
    testThreadChange();
  }

  private static void testThreadChange() throws InterruptedException {
    ThreadTest2 threadTest2 = new ThreadTest2();
    threadTest2.changeThreadDate();
  }

  /**
   * 两个线程共享内存 可以访问同一个元素 两个线程分别操作，但是线程间数据操作不互相关联 即使多线程，数据操作也是符合预期的，影响数据的只是线程的执行先后顺序
   */
  private void changeThreadDate() throws InterruptedException {
    Thread thread1 = new Thread(new Runnable() {
      @Override
      public void run() {
        data = 1;
        System.out.println("change data=1");
      }
    });

    Thread thread2 = new Thread(new Runnable() {
      @Override
      public void run() {
        data = 2;
        System.out.println("change data=2");
      }
    });

    thread1.start();
    thread2.start();

    thread1.join();
    thread2.join();

    System.out.println("data:" + data);

  }


}
