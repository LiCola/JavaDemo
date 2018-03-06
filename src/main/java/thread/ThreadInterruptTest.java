package thread;

import java.io.IOException;

/**
 * Created by LiCola on 2017/8/19.
 */
public class ThreadInterruptTest {

  public static final void main(String[] args) throws InterruptedException {
//    testRunnableInterrupt();
//    testWaitingInterrupt();
    testIOStreamInterrupt();
  }

  private static class IOStreamThread extends Thread{

    @Override
    public void run() {
      while (!isInterrupted()){
        try {
          System.out.println(System.in.read());
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      System.out.println("exit read io");
    }

    public void cancel(){
      try {
        System.in.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
      interrupt();
    }
  }

  private static void testIOStreamInterrupt() throws InterruptedException {
    IOStreamThread ioStreamThread=new IOStreamThread();
    ioStreamThread.start();

    Thread.sleep(100);
    ioStreamThread.cancel();
  }

  private static void testRunnableInterrupt() throws InterruptedException {
    Thread thread = new Thread() {
      @Override
      public void run() {
        while (!isInterrupted()) {

        }
        System.out.println("done work");
      }
    };

    thread.start();
    Thread.sleep(100);
    thread.interrupt();
  }


  public static void testWaitingInterrupt() {
    Thread thread = new Thread() {
      @Override
      public void run() {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
          System.out.println(isInterrupted());
        }
      }
    };
    thread.start();

    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    thread.interrupt();
  }
}
