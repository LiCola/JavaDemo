import com.licola.llogger.LLogger;

public class BaseClassTest {


  public static final void main(String[] args) {
    Base base = new Base() {
      @Override
      public void doSomething(String input) {
        LLogger.d("input = " + input);
      }
    };

    base.doSomething("data");


    //测试继承对方法参数的影响
    BaseArgs baseArgs=new SubArgs();
    baseArgs.print("hello");

    SubArgs subArgs=new SubArgs();
//    subArgs.PrintUtils("hello");
  }

  public static abstract class Base {

    public abstract void doSomething(String input);
  }


  public static class BaseArgs{
    void print(String... args){
      LLogger.d("base PrintUtils:"+args);
    }

    public int overload(int arg){

      return 0;
    }

    public float overload(float arg){
      return 0f;
    }
  }

  public static class SubArgs extends BaseArgs{

    @Override
    void print(String[] args) {
      LLogger.d("sub PrintUtils:"+args);
    }
  }
}
