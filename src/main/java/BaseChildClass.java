
import com.licola.llogger.LLogger;
/**
 * Created by LiCola on 2017/7/13.
 */
public class BaseChildClass {

  public static final void main(String[] args)
      throws IllegalAccessException, InstantiationException {
    /**
     * Child 的new初始化过程 会先调用Base的构造方法
     * 而Base的构造方法中调用了test方法，但是public的test被子类重写
     * 所以Base父类的构造方法中实际调用子类的实例test方法，
     * 但是这个时候子类的实例赋值语句和构造方法还有执行 就输出了默认值
     * 所以：父类构造方法中调用可被子类重写的方法，是一种不好的编码方式，容易引起混淆或者隐藏bug，应该只调用private方法
     * 通过上结论，父子类都有init方法逻辑时，两者的init逻辑方法应该相互隔离,即init方法必须是private私有的。
     */
//    Child child = new Child();
//    child.test();
    Child child=Child.class.newInstance();
    child.test();
  }


  public  static class Base {

    static {
      LLogger.d("base static invoke");
    }

    public Base() {
      LLogger.d("base construction");
      test();
    }

    public  void test(){
      LLogger.d("base test invoke");
    }
  }

  public static class Child extends Base {

    private int a = 123;

    public Child() {
      LLogger.d("child construction");
    }

    public void test() {
      LLogger.d(a);
    }
  }
}
