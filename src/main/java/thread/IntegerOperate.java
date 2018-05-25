package thread; import com.licola.llogger.LLogger;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by LiCola on 2017/9/7.
 */
public class IntegerOperate {

  private volatile int date = 0;//volatile值保证 内存可见 只是同步的基础 并不能完成真正的同步
  private final ReentrantLock lock = new ReentrantLock();

  public IntegerOperate() {
  }

  public int getAndIncrement() {
    LLogger.d("Thread:" + Thread.currentThread().toString() +
        " isHeldByCurrentThread:" + lock.isHeldByCurrentThread() + " hasQueuedThreads:" + lock
        .hasQueuedThreads() + " getQueueLength:" + lock.getQueueLength());
    lock.lock();
    int old;
    try {
      old = date;
      ++date;
    } finally {
      lock.unlock();
    }
    return old;
  }

  public int get() {
    return date;
  }
}
