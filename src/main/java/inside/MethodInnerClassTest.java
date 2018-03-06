package inside;

import java.io.File;

/**
 * Created by LiCola on 2017/9/27.
 */
public class MethodInnerClassTest {

  private int a = 100;

  public static final void main(String[] args) {
    System.out.println("main");
  }


  public void test(final int param) {

    final File file = new File("file.txt");

    final String str = "hello";

    class Inner {

      public void innerMethod() {
        System.out.println("outer var a:" + a);
        System.out.println("method param :" + param);
        System.out.println("method var str:" + str);
        System.out.println("method other instance:" + file);
      }
    }

    Inner inner = new Inner();
    inner.innerMethod();

  }

   class InnerClass {

    public void doSome() {
      System.out.println("external var:" + a);
    }
  }
}
