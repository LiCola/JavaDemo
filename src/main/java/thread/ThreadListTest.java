package thread;

import com.licola.llogger.LLogger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by LiCola on 2017/8/29.
 */
public class ThreadListTest {

  public static final void main(String[] args) {

    startSort();

//    startModifyThread(list);
//    startIteratorThread(list);

  }


  private static void startSort() {
    //JDK1.8以前 CopyOnWriteArrayList的迭代器不支持修改操作 而sort就是使用迭代器修改
    CopyOnWriteArrayList<String> list=new CopyOnWriteArrayList<>();

    list.add("c");
    list.add("b");
    list.add("a");
    Collections.sort(list);

    Iterator<String> iterator = list.iterator();

    for (String s : list) {
      LLogger.d("for item:" + s);
    }
  }

  private static void startModifyThread(final List<String> list) {
    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < 100; i++) {
          list.add("item" + i);
//          LLogger.d(Thread.currentThread().toString()+" 线程在写容器"+" index="+i);
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
//            LLogger.d(Thread.currentThread().toString()+" 线程在读容器"+" index="+index++);
          }
        }
      }
    });
    thread.start();
  }

}
