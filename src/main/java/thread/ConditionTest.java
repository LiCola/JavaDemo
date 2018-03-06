package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by LiCola on 2017/11/20.
 */
public class ConditionTest implements Runnable {

  private volatile boolean fire = false;
  private ReentrantLock lock = new ReentrantLock();
  private Condition condition = lock.newCondition();


  public static void println(Object object){
    System.out.println(System.currentTimeMillis()+"ms : "+object.toString());
  }
  
  @Override
  public void run() {
    
    try {
      println(
          "work :isHeldByCurrentThread:" + lock.isHeldByCurrentThread() + " hasQueuedThreads:"
              + lock.hasQueuedThreads());
      lock.lock();
      println("work :get lock");
      try {
//        println("work :block before await condition");
//        condition.await();
//        println("work :await finish");

        while (!fire) {
          println("work :loop true entry block next await condition");
          condition.await();
          println("work :await finish next loop");
        }
      } finally {
        println("work :release lock");
        lock.unlock();
      }
      println("work thread:meet the conditions  fired!");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void fire() {
    println(
        "main :isHeldByCurrentThread:" + lock.isHeldByCurrentThread() + " hasQueuedThreads:" + lock
            .hasQueuedThreads());
    lock.lock();
    try {
      this.fire = true;
      println("main :get lock");
      condition.signal();
      println("main :signal");
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

    println("main sleep!");
    Thread.sleep(1000);
    println("main invoke fire!");
    conditionTest.fire();


  }
}
