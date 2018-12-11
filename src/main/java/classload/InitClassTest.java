package classload;

import com.licola.llogger.LLogger;

/**
 * Created by LiCola on 2018/6/28.
 */
public class InitClassTest {

  static {
    //根据自动生成的<clinit>代码块 会静态变量赋值语句插入到原static代码块之后 所以最后赋值为10;
    i = 2;
  }

  static int i = 10;

  public static final void main(String[] args) {
//    LLogger.d(InitClassTest.i);

    testDeadLoopClass();
  }

  private static void testDeadLoopClass() {
    Runnable script = new Runnable() {
      @Override
      public void run() {
        LLogger.d("start load class");
        DeadLoopClass deadLoopClass = new DeadLoopClass();
        LLogger.d("init class over");
      }
    };

    Thread thread1 = new Thread(script);
    Thread thread2 = new Thread(script);
    thread1.start();
    thread2.start();
  }

  static class DeadLoopClass {

    static {
      if (true) {
        LLogger.d("init DeadLoopClass");
        while (true) {

        }
      }
    }
  }
}
