package advance;


import com.licola.llogger.LLogger;
import java.util.function.Function;

/**
 * Created by LiCola on 2017/11/23.
 */
public class ClosureTest {


  public static final void main(String[] args){
    ClosureTest closureTest=new ClosureTest();
    closureTest.getRunnable(true).run();
  }

  public Integer globalInt=100;

  public Runnable getRunnable(Boolean input) {

    int dataInt = 10;
    String dataStr = "data";
    TestObject dataObject = new TestObject(1, "init Object");
    return new Runnable() {
      @Override
      public void run() {
        dataObject.changeValue(10,"123");
        LLogger.d(
            "局部内部类 实现闭包:" + "dataInt:" + dataInt + " dataStr:" + dataStr + " dataObject:"
                + dataObject);
        LLogger.d("访问外部全局变量："+globalInt+" input:"+input);

      }
    };
  }
}
