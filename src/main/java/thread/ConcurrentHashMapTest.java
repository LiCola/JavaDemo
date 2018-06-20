package thread; import com.licola.llogger.LLogger;

import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by LiCola on 2018/1/30.
 */
public class ConcurrentHashMapTest {


  public static final void main(String[] args) {
//    testIterator();
    ConcurrentHashMap<String,Integer> concurrentHashMap=new ConcurrentHashMap<>();
    concurrentHashMap.put("a",1);
    concurrentHashMap.put("b",2);
    concurrentHashMap.get("a");
    LLogger.d(concurrentHashMap.size());
  }

  private static void testIterator() {
    final ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
    map.put("a","abstract");
    map.put("b","basic");

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
          LLogger.d(entry.getKey()+" :"+entry.getValue());
        }
      }
    };
    Thread threadGet=new Thread(new RunnableGet());

    threadGet.start();

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    map.put("a","call");
    try {
      threadGet.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    LLogger.d("put update get ");
    Thread threadAfter=new Thread(new RunnableGet());
    threadAfter.start();

  }
}
