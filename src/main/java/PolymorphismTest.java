import com.licola.llogger.LLogger;

/**
 * Created by LiCola on 2017/8/9.
 */
public class PolymorphismTest {

  public interface Array {

    int get(int i);

    void set(int i, int x);

    int size();
  }

  public static final class NaiveArray implements Array {

    int[] array;

    public NaiveArray(int cap) {
      this.array = new int[cap];
    }

    @Override
    public int get(int i) {
      return array[i];
    }

    @Override
    public void set(int i, int x) {
      array[i] = x;
    }

    @Override
    public int size() {
      return array.length;
    }
  }

  public static final void main(String[] args) {

    long timeStart = System.nanoTime();
    long timeEnd = System.nanoTime();
    LLogger.d("user time=" + (timeEnd - timeStart));

    Array array = new NaiveArray(10000000);
    timeStart = System.nanoTime();
    computeSet(array);
    timeEnd = System.nanoTime();
    LLogger.d("user time=" + (timeEnd - timeStart));

    NaiveArray naiveArray = new NaiveArray(10000000);
    timeStart = System.nanoTime();
    computeSet(naiveArray);
    timeEnd = System.nanoTime();
    LLogger.d("user time=" + (timeEnd - timeStart));
  }

  private static void computeSet(Array array) {
    for (int i = 0; i < array.size(); i++) {
      array.set(i, i);
    }
  }

  private static void computeSet(NaiveArray array) {
    for (int i = 0; i < array.size(); i++) {
      array.set(i, i);
    }
  }


}
