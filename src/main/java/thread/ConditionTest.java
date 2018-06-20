package thread;

import com.licola.llogger.LLogger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by LiCola on 2017/11/20.
 */
public class ConditionTest implements Runnable {

  private volatile boolean fire = false;
  private ReentrantLock lock = new ReentrantLock();
  private Condition condition = lock.newCondition();


  @Override
  public void run() {

    try {
      LLogger.d(
          "锁是否被当前线程持有isHeldByCurrentThread:" + lock.isHeldByCurrentThread() + " 是否有线程在等待该锁hasQueuedThreads:"
              + lock.hasQueuedThreads());
      lock.lock();
      LLogger.d("get lock");
      try {
//        LLogger.d("work :block before await condition");
//        condition.await();
//        LLogger.d("work :await finish");

        while (!fire) {
          LLogger.d("loop true entry block next await condition");
//          condition.awaitNanos(TimeUnit.MILLISECONDS.toNanos(100));//限期等待
          condition.await();//无线等待
          LLogger.d("await finish next loop");
        }
      } finally {
        LLogger.d("release lock");
        lock.unlock();
      }
      LLogger.d("meet the conditions  fired!");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void fire() {
    LLogger.d(
        "锁是否被当前线程持有isHeldByCurrentThread:" + lock.isHeldByCurrentThread() + " 是否有线程在等待该锁hasQueuedThreads:" + lock
            .hasQueuedThreads());
    lock.lock();
    try {
      this.fire = true;
      LLogger.d("get lock");
      condition.signal();
      LLogger.d("signal");
    } finally {
      lock.unlock();
    }
  }

  public static final void main(String[] args) throws InterruptedException {
    ConditionTest conditionTest = new ConditionTest();
    Thread fireThread = new Thread(conditionTest);
    /**
     * 线程先启动 才能完整的执行流程
     * 否则其他线程 发起的signal信号无法接收到
     * 因为如果不先启动线程 而子线程没有因为await的执行 而放弃锁并把子线程添加到条件队列
     * 主线程发起的signal信号 condition条件队列中没有元素 代码执行没有效果
     * 所以需要在子线程 检查真正的条件（线程相关对象定义的volatile不同线程可见的变量） 才可以抛弃线程执行先后
     * 其实就是检查 两个条件，1：真正共享内存变量 2：显式条件
     */
    fireThread.start();

    LLogger.d("sleep!");
    Thread.sleep(1000);
//    LLogger.d(" invoke fire!");
    LLogger.d("never invoke fire! next entry looper");
//    conditionTest.fire();
    while (true){

    }

  }
}
