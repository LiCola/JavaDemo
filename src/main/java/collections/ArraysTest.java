package collections;import com.licola.llogger.LLogger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by LiCola on 2017/8/21.
 */
public class ArraysTest {

  public static final void main(String[] args) {
    testArrays();
//    testStrings();
  }

  private static void testArrays() {
      int[] ints=new int[]{1,2,3,4};
    List list = Arrays.asList(ints);
    LLogger.d("list size :"+list.size());
    for (Object o : list) {
      LLogger.d(o.toString());
    }

  }

  private static void testStrings() {
    String[] arrays = new String[]{"e-hello", "c-world", "Break", "ability"};
    Arrays.sort(arrays, String.CASE_INSENSITIVE_ORDER);
    LLogger.d(Arrays.toString(arrays));

    String[] strings = Arrays.copyOf(arrays, 4);
    LLogger.d("strings = " + Arrays.toString(strings));
  }
}
