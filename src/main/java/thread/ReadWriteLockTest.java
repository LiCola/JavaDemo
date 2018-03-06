package thread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by LiCola on 2017/11/25.
 */
public class ReadWriteLockTest {

  public static final void main(String[] args) throws InterruptedException {

    final ReadWriteLockInstance queue = new ReadWriteLockInstance();
    for (int i = 0; i < 4; i++) {
      new Thread(new Runnable() {

        @Override
        public void run() {
          while (true) {
            queue.read();
          }
        }
      }).start();
      new Thread(new Runnable() {

        @Override
        public void run() {
          while (true) {
            queue.write(new Random().nextInt(1000));
          }
        }
      }).start();

    }

  }


  static class ReadWriteLockInstance {

    private int data=10;
    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public  void read()  {

      rwl.readLock().lock();
      try {
        System.out.println(Thread.currentThread().getName() + " be ready to read data!");
        Thread.sleep(data);
        System.out.println(Thread.currentThread().getName() + " have read data :" + data);
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        rwl.readLock().unlock();
      }
      try {
        Thread.sleep(data);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    public void write(int data) {

      rwl.writeLock().lock();
      try {
        System.out.println(Thread.currentThread().getName() + " be write to write data!");
        Thread.sleep(data);
        this.data = data;
        System.out.println(Thread.currentThread().getName() + " have write data :" + data);

      } catch (Exception e) {
        e.printStackTrace();
      } finally {

        rwl.writeLock().unlock();
      }

    }
  }
}
