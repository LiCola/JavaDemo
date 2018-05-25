package compiler; import com.licola.llogger.LLogger;

/**
 * Created by LiCola on 2017/12/26.
 */
public class DexCompilerTest {

  public int intValue = 100;
  public static final String finalStr ;

  static {
    finalStr="123s";
  }

  public static final void main(String[] args) {
    DexCompilerTest compilerTest = new DexCompilerTest();
    compilerTest.testMethod();
    LLogger.d("final String:" + finalStr);
  }

  public void testMethod() {
    return;
  }
}
