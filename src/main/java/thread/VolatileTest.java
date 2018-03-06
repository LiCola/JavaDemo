package thread;

/**
 * Created by LiCola on 2017/11/21.
 */
public class VolatileTest {

  volatile int a = 1;
  volatile int b = 2;

  public void change() {
    this.a = 3;
    this.b = this.a;
  }

  public void print() {
    System.out.println("b=" + b + " : a=" + a);
  }

  public static final void main(String[] args) {
    while (true) {
      final VolatileTest volatileTest = new VolatileTest();
      new Thread(new Runnable() {
        @Override
        public void run() {
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          volatileTest.change();
        }
      }).start();

      new Thread(new Runnable() {
        @Override
        public void run() {
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          volatileTest.print();
        }
      }).start();
    }
  }
}
