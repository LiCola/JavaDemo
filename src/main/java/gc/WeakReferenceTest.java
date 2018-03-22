package gc;

import java.lang.ref.WeakReference;

/**
 * Created by LiCola on 2018/3/21.
 */
public class WeakReferenceTest {



  public static final void main(String[] args) throws InterruptedException {

    String content=new String("content");
    WeakReference<String> stringWeakReference = new WeakReference<>(content);
    int times = 1;
    while (true) {
      String result = stringWeakReference.get();
      if (result == null) {
        System.out.println("gc this reference");
        return;
      }
      Thread.sleep(1000);
      content=null;
      System.out.println("times:" + times++ + ":" + result+" gc:"+(content==null));
      System.gc();
    }

  }
}
