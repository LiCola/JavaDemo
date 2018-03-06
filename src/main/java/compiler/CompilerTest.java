package compiler;

/**
 * Created by LiCola on 2017/12/8.
 */
public class CompilerTest {
  private static int int1 =10;

  public static final int int0 = 1000;
  public static final String str1 ;

  private int m;
  private long ml;

  static {
    str1="123s";
  }

  public static final void main(String[] args) {
    System.out.println("run time");
  }

  public int inc() {
    return m + 1;
  }

  public long incLong(){
    long result=ml++;
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
