package thread; import com.licola.llogger.LLogger;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by LiCola on 2017/11/25.
 */
public class ConcurrentCollectionsTest {

  public static final void main(String[] args) {
    BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<String>();
    ConcurrentLinkedQueue<String> concurrentLinkedQueue;
    testSynchronousQueue();
  }

  private static void testSynchronousQueue() {
    //没有保存数据元素的队列 生产者必须配对消费者 共同处理元素
    BlockingQueue<String> blockingQueue = new SynchronousQueue<>();

    class RunnableProducer implements Runnable {

      @Override
      public void run() {
        while (true) {
          try {
            String taskTime = "task time:" + System.nanoTime();
            blockingQueue.put(taskTime);
            LLogger.d("put:" + taskTime);
            Thread.sleep(500);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }

    class RunnableConsumer implements Runnable {

      @Override
      public void run() {
        while (true) {
          try {
            String task = blockingQueue.take();
            LLogger.d("take:" + task);
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }

    new Thread(new RunnableProducer()).start();
    new Thread(new RunnableConsumer()).start();
//    new Thread(new RunnableConsumer()).start();
  }
}
