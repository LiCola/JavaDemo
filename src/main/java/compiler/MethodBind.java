package compiler; import com.licola.llogger.LLogger;

/**
 * Created by LiCola on 2017/12/11.
 */
public class MethodBind {

  static class Base {

    public static int A=1000;

    public void calc(int a, long b) {
      LLogger.d("base calc invoke");
    }
  }

  static class Sub extends Base implements Interface0{

    public void calc(long a, long b) {
      LLogger.d("sub calc invoke");
    }
  }

  public static final void main(String[] args) {
    Base newInstance = new Sub();
    int a = 100;
    int b = 100;
    newInstance.calc(a, b);

//    LLogger.d(Sub.A);
  }

  public interface Interface0 {

    public static int A = 10;
  }

  public interface Interface1 extends Interface0 {

    int A = 100;
  }

  public interface Interface2 {

    int A = 200;
  }


}
