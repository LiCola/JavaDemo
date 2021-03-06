package memory;
import com.licola.llogger.LLogger;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by LiCola on 2018/3/26.
 */
public class ReferenceQueueTest {

  private static final ReferenceQueue<byte[]> referenceQueue = new ReferenceQueue<>();
  private static final int SIZE_1M = 1 << 20;
  private static final int GC_SIZE = 100;
  private static final int OOM_SIZE = 100000;


  public static final void main(String[] args) {


    LLogger.init();

    WeakHashMap<Object, Object> weakHashMap = new WeakHashMap<>();

    Object value = new Object();
    Map<Object, Object> map = new HashMap<>();

    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          int count = 0;
          Reference<byte[]> weak;
          LLogger.d("开启回收检测线程");
          while ((weak = (Reference<byte[]>) referenceQueue.remove()) != null) {
            byte[] content=weak.get();
            LLogger.d((count++) + " 回收:" + weak.hashCode()+" hash:"+ Arrays
                .hashCode(content));
          }
        } catch (InterruptedException e) {

        }
      }
    });
    thread.setDaemon(true);
    thread.start();

    int SIZE = GC_SIZE;
//    int SIZE = OOM_SIZE;
    for (int i = 0; i < SIZE; i++) {
      byte[] bytes = new byte[SIZE_1M];
//      Reference<byte[]> otherReclearference = new SoftReference<>(bytes, referenceQueue);
      Reference<byte[]> otherReference = new WeakReference<>(bytes, referenceQueue);
      map.put(otherReference, value);
      otherReference.clear();
    }

//    while (true){
//
//    }

    LLogger.d("map size:" + map.size());
  }
}
