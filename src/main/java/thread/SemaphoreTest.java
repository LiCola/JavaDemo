package thread;

import java.util.concurrent.Semaphore;

/**
 * Created by LiCola on 2018/3/5.
 */
public class SemaphoreTest {

  public static final void main(String[] args) throws InterruptedException {
//    testLimitSemaphore();

    Semaphore semaphore=new Semaphore(1);
    semaphore.acquire();
    semaphore.acquire();
    System.out.println("2 acquire after");
  }

  private static void testLimitSemaphore() {
    int num=10;
    AccessControlService accessControlService=new AccessControlService();
    Thread[] threads=new Thread[num];
    for (int i=0;i<num;i++){
      int finalI = i;
      threads[i]=new Thread(new Runnable() {
        @Override
        public void run() {
          accessControlService.login("user"+ finalI);
        }
      });
    }

    for(Thread thread:threads){
      thread.start();
    }
  }

  public static class AccessControlService{
    public static class ConcurrentLimitException extends RuntimeException{
    }

    private static final int MAX_PERMITS=10;

    private Semaphore permits=new Semaphore(MAX_PERMITS,true);

    public boolean login(String name){
      if (!permits.tryAcquire()){
        throw new ConcurrentLimitException();
      }

      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      return true;
    }

    public void logout(String name){
      permits.release();
    }
  }
}
