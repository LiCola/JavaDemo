package thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by LiCola on 2018/3/5.
 */
public class CyclicBarrierTest {

  public static final void main(String[] args){
    int num=3;
    Thread[] threads=new Thread[num];

    //集合点的实现方式 循环栏栅 子线程间协同
    CyclicBarrier barrier=new CyclicBarrier(num, new Runnable() {
      @Override
      public void run() {
        System.out.println("all arrived "+System.currentTimeMillis()+" executed by "+Thread.currentThread().getName());
      }
    });

    for (int i = 0; i < threads.length; i++) {
      threads[i]=new Tourist(barrier);
      threads[i].start();
    }
  }

  public static class Tourist extends Thread{

    CyclicBarrier barrier;

    public Tourist(CyclicBarrier barrier) {
      this.barrier = barrier;
    }

    @Override
    public void run() {
      try {
        //模拟先各自独立运行
        Thread.sleep((long) (Math.random()*1000));

        //集合点A
        barrier.await();
        System.out.println(this.getName()+" arrived A "+System.currentTimeMillis());

        //集合后模拟再各自独立运行
        Thread.sleep((long) (Math.random()*1000));

        //集合点B
        barrier.await();
        System.out.println(this.getName()+" arrived A "+System.currentTimeMillis());

      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (BrokenBarrierException e) {
        e.printStackTrace();
      }
    }
  }
}
