package compiler;

import com.licola.llogger.LLogger;
import java.util.HashSet;
import java.util.Set;

/**
 * @author LiCola
 * @date 2019-08-01
 */
public class SyntacticTest {

  public static void main(String[] args) {

    LLogger.init();

    Set<Integer> setInt = new HashSet<>();
    for (int i = 0; i < 100; i++) {
      setInt.add(i);
      setInt.remove(i - 1);
    }
    LLogger.d(setInt);

    Set<Short> setShort = new HashSet<>();
    for (short i = 0; i < 100; i++) {
      setShort.add(i);
      //注意提示 简化的-1 导致类型转换
      setShort.remove(i - 1);
      //写法上 以下才能达到预期效果
//      short index= (short) (i-1);
//      setShort.remove(index);
    }

    LLogger.d(setShort);

  }
}
