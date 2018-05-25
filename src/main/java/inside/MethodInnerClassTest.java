package inside;
import com.licola.llogger.LLogger;
import java.io.File;

/**
 * Created by LiCola on 2017/9/27.
 */
public class MethodInnerClassTest {

  private int a = 100;

  public static final void main(String[] args) {
    LLogger.d("main");
  }


  public void test(final int param) {

    final File file = new File("file.txt");

    final String str = "hello";

    class Inner {

      public void innerMethod() {
        LLogger.d("outer var a:" + a);
        LLogger.d("method param :" + param);
        LLogger.d("method var str:" + str);
        LLogger.d("method other instance:" + file);
      }
    }

    Inner inner = new Inner();
    inner.innerMethod();

  }

   class InnerClass {

    public void doSome() {
      LLogger.d("external var:" + a);
    }
  }
}
