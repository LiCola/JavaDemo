package thread; import com.licola.llogger.LLogger;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Created by LiCola on 2017/8/19.
 */
public class AtomicTest {

  public static final void main(String[] args) throws InterruptedException {
    testAtomicOperate();

    //带时间戳的原子对象 防止ABA问题
    AtomicStampedReference<String> stringAtomicStampedReference = new AtomicStampedReference<>(
        "123", 1);

//    testCountDownLatch();
  }

  private static void testCountDownLatch() throws InterruptedException {
    final int threadSize = 5;//线程数
    final int workSize = 20;//任务数
    Executor executor = Executors.newFixedThreadPool(threadSize);//线程池
    ArrayBlockingQueue<String> workBlockingQueue = new ArrayBlockingQueue<>(workSize);
    for (int i = 0; i < workSize; i++) {
      workBlockingQueue.add("任务-" + i);
    }
    CountDownLatch countDownLatch = new CountDownLatch(workSize);

    LLogger.d("主线程开始" + System.currentTimeMillis());

    for (int i = 0; i < workSize; i++) {
      executor.execute(new WorkCountRunnable(workBlockingQueue.poll(), countDownLatch));
    }
    countDownLatch.await();//等待子线程的所有任务完成 主线程阻塞
    LLogger.d("主线程结束" + System.currentTimeMillis());
  }

  /**
   * Runnable可执行任务 提交到线程池中等待调用执行
   * {@link CountDownLatch}用于任务计数，如子线程对主线程通知子任务完成，主线程持有计数对象可以了解全局的子任务执行情况。
   * 从功能上来看子线程计数功能和{@link Thread#join()}差不多，但是如果主线程无法直接持有子线程对象，就无法使用join关联线程。
   * CountDownLatch就可以解决没有直接持有线程情况下的线程协同。
   */
  private static class WorkCountRunnable implements Runnable {

    String workName;
    CountDownLatch countDownLatch;

    public WorkCountRunnable(String workName, CountDownLatch countDownLatch) {
      this.workName = workName;
      this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
      LLogger.d(Thread.currentThread().toString() + " 处理任务:" + workName);
      try {
        Thread.sleep(new Random().nextInt(1000));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      countDownLatch.countDown();
    }
  }

  public static class AtomicIntegerThread extends Thread {

    private AtomicInteger atomicInteger;
    private IntegerOperate integerOperate;
    private AtomicIntegerFieldUpdaterDemo<String> stringAtomicIntegerFieldUpdaterDemo;

    private Random random = new Random();

    public AtomicIntegerThread(AtomicInteger atomicInteger, IntegerOperate integerOperate,
        AtomicIntegerFieldUpdaterDemo<String> stringAtomicIntegerFieldUpdaterDemo) {
      this.atomicInteger = atomicInteger;
      this.integerOperate = integerOperate;
      this.stringAtomicIntegerFieldUpdaterDemo = stringAtomicIntegerFieldUpdaterDemo;
    }

    @Override
    public void run() {

      try {
        Thread.sleep(random.nextInt(100));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      LLogger.d(Thread.currentThread()
          + " atomicInteger getAndIncrement:" + atomicInteger.getAndIncrement()
          + " integerOperate getAndIncrement:" + integerOperate.getAndIncrement()
          + " stringAtomicIntegerFieldUpdaterDemo getAndIncrement:" + stringAtomicIntegerFieldUpdaterDemo.getAndIncrement()
          + " old getAndSet:" + stringAtomicIntegerFieldUpdaterDemo.getAndSet(Thread.currentThread().toString())
      );
    }
  }

  private static void testAtomicOperate() throws InterruptedException {
    int num = 10000;
    Thread[] threads = new Thread[num];
    AtomicInteger atomicInteger = new AtomicInteger();
    IntegerOperate integerOperate = new IntegerOperate();
    AtomicIntegerFieldUpdaterDemo<String> atomicIntegerFieldUpdaterDemo = new AtomicIntegerFieldUpdaterDemo<>();
    for (int i = 0; i < num; i++) {
      threads[i] = new AtomicIntegerThread(atomicInteger, integerOperate,
          atomicIntegerFieldUpdaterDemo);
      threads[i].start();
    }

    for (int i = 0; i < num; i++) {
      threads[i].join();
    }

    LLogger.d("atomicInteger = " + atomicInteger.get());
    LLogger.d("integerOperate = " + integerOperate.get());
    LLogger.d("atomicIntegerFieldUpdaterDemo get int:" + atomicIntegerFieldUpdaterDemo.getAtomicInt() +" get ref:"+atomicIntegerFieldUpdaterDemo.getAtomicRef());

  }


}
