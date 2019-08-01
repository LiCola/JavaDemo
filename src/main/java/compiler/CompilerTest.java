package compiler;

import com.licola.llogger.LLogger;

/**
 * Created by LiCola on 2017/12/8.
 */
public class CompilerTest {

  private static int int1 = 10;

  public static final int int0 = 1000;
  public static final String str1;

  private int m;
  private long ml;

  static {
    str1 = "123s";
  }

  public static final void main(String[] args) {

    //三目运算符 隐含的类型转换
    Object i=1==1?new Integer(3):new Float(1);
    LLogger.d(i);
  }

  public static int getInt1() {
    return int1;
  }

  public static int getInt0() {
    return int0;
  }

  public int inc() {
    return m + 1;
  }

  public long incLong() {
    long result = ml++;
    return result;
  }

  public int incExecption() {
    int x;
    try {
      x = 1;
      return x;
    } catch (Exception e) {
      x = 2;
      return x;
    } finally {
      x = 3;
    }
  }

}
