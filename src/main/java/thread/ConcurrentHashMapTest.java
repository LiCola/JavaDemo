package thread;

import com.licola.llogger.LLogger;

import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiFunction;

/**
 * Created by LiCola on 2018/1/30.
 */
public class ConcurrentHashMapTest {

  private final ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();

  public static final void main(String[] args) {
    LLogger.init();
//    testBase();
//    testIterator();

    ConcurrentHashMapTest concurrentHashMapTest = new ConcurrentHashMapTest();

    concurrentHashMapTest.testThread();
  }

  private void compute(){
    map.compute(1, new BiFunction<Integer, Integer, Integer>() {
      @Override
      public Integer apply(Integer key, Integer value) {

        if (value==null){
          return 1;
        }else {
          return value+1;
        }
      }
    });
  }

  private void update() {
    Integer result = map.get(1);
    if (result == null) {
      map.put(1, 1);
    } else {
      map.put(1, result + 1);
    }

  }

  private void testThread() {

    ExecutorService executorService = Executors.newCachedThreadPool();

    int size=20;

    for (int i = 0; i < size; i++) {
      executorService.execute(() -> {
//        update();//ConcurrentHashMap 虽然线程安全 但是复合操作并不安全
        compute();//提供的复合操作方法
      });
    }

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    LLogger.d(map.get(1));

    executorService.shutdown();
  }

  private static void testBase() {
    ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();
    concurrentHashMap.put("a", 1);
    concurrentHashMap.put("b", 2);
    concurrentHashMap.get("a");
    LLogger.d(concurrentHashMap.size());
  }

  private static void testIterator() {
    final ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
    map.put("a", "abstract");
    map.put("b", "basic");

    class RunnableGet implements Runnable {

      @Override
      public void run() {
        for (Entry<String, String> entry :
            map.entrySet()) {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          LLogger.d(entry.getKey() + " :" + entry.getValue());
        }
      }
    }
    ;
    Thread threadGet = new Thread(new RunnableGet());

    threadGet.start();

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    map.put("a", "able");
    try {
      threadGet.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    LLogger.d("put update get ");
    Thread threadAfter = new Thread(new RunnableGet());
    threadAfter.start();

  }
}
