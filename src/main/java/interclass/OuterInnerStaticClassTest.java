package interclass;

import com.licola.llogger.LLogger;

/**
 * Created by LiCola on 2017/6/13.
 */
public class OuterInnerStaticClassTest {

  private static int shared = 100;
  public static int shared2 = 100;
  static int shared3 = 100;

  public static final void main(String[] args) {
    OuterInnerStaticClassTest outerInnerStaticClassTest = new OuterInnerStaticClassTest();
    outerInnerStaticClassTest.outMethod();
  }

  public static class StaticInnerClass {

    public void innerMethod() {
      LLogger.d("inner call out class field:" + shared + " shared2:" + shared2 + " shared3:"
          + shared3);
      LLogger.d("inner call out method :" + outerMethod());
    }
  }

  private static String outerMethod() {
    return "";
  }

  public void outMethod() {
    StaticInnerClass innerClass = new StaticInnerClass();
    innerClass.innerMethod();
  }
}
