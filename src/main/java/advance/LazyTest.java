package advance;

import com.licola.llogger.LLogger;
import java.util.function.Supplier;

/**
 * @author LiCola
 * @date 2019-05-27
 */
public class LazyTest {

  public static void main(String[] args) {

    /**
     * 懒性日志 的基本原理
     * 如果日志没有初始化，即d方法不会实际输出，
     * 采用惰性参数，可以忽略参数使用
     * 本质原理就是，接口-选择性调起。
     */
    LLogger.init();

    LLogger.d(LazyString.lazy(LazyTest::out));
  }

  public static String out() {
    return "out";
  }

  public static class LazyString {

    private final Supplier<?> supplier;

    public static final LazyString lazy(Supplier<?> supplier) {
      return new LazyString(supplier);
    }

    public LazyString(Supplier<?> supplier) {
      this.supplier = supplier;
    }

    @Override
    public String toString() {
      System.out.println("invoke toString");
      return String.valueOf(supplier.get());
    }
  }

}
