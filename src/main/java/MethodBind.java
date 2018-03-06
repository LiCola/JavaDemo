/**
 * Created by LiCola on 2017/12/11.
 */
public class MethodBind {

  static class Base {

    public  void calc(int a, long b) {
      System.out.println("base calc invoke");
    }
  }

  static class Sub extends Base {

    public void calc(long a, long b) {
      System.out.println("sub calc invoke");
    }
  }

  public static final void main(String[] args) {
    Base newInstance = new Sub();
    int a = 100;
    int b = 100;
    newInstance.calc(a, b);


  }
}
