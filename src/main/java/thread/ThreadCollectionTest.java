package thread;

import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by LiCola on 2017/8/29.
 */
public class ThreadCollectionTest {

  public static final void main(String[] args) {
//    final List<String> list = collections.synchronizedList(new ArrayList<>());
    final CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

//    startSort(list);

//    startModifyThread(list);
//    startIteratorThread(list);

    unsafeConcurrentUpdate();
    ConcurrentHashMap<Integer,String> stringConcurrentHashMap=new ConcurrentHashMap<>();
    stringConcurrentHashMap.put(10,"s");
  }

  private static void unsafeConcurrentUpdate() {
//    Map<Integer, Integer> map = new HashMap<>();
    Map<Integer, Integer> map = new Hashtable<>();

    int size = 100;
    Thread[] threads = new Thread[size];
    Random random = new Random();
    for (int i = 0; i < size; i++) {

      threads[i] = new Thread() {
        @Override
        public void run() {
          for (int j = 0; j < size; j++) {
            int key = random.nextInt(size);
            System.out.println(Thread.currentThread().toString() + " key:" + key);
            map.put(key, j);
          }
        }
      };
      threads[i].start();
    }

    for (int i = 0; i < size; i++) {
      try {
        threads[i].join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

  }

  private static void startSort(List<String> list) {
    list.add("c");
    list.add("b");
    list.add("a");
    Collections.sort(list);

    for (String s : list) {
      System.out.println("for item:" + s);
    }
  }

  private static void startModifyThread(final List<String> list) {
    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < 100; i++) {
          list.add("item" + i);
//          System.out.println(Thread.currentThread().toString()+" 线程在写容器"+" index="+i);
          try {
            Thread.sleep((long) (Math.random() * 10));
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    });
    thread.start();
  }

  private static void startIteratorThread(final List<String> list) {
    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        while (true) {
          int index = 0;
          for (String s : list) {
//            System.out.println(Thread.currentThread().toString()+" 线程在读容器"+" index="+index++);
          }
        }
      }
    });
    thread.start();
  }

}
