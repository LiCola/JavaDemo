import com.licola.llogger.LLogger;

/**
 * Created by LiCola on 2017/11/30.
 */
public class SingleInstanceTest {

  public static final void main(String[] args) throws InterruptedException {

    SingleInit init=null;
    if (init==null){
      LLogger.d("init null");
    }else {
      LLogger.d("init not null");
    }
    LLogger.d(SingleInit.getInstance());

//    LLogger.d(SingleInit.name);

//   Thread threadInstance= new Thread(new Runnable() {
//      @Override
//      public void run() {
//        LLogger.d("work thread run");
//        try {
//          Thread.sleep(2000);
//        } catch (InterruptedException e) {
//          e.printStackTrace();
//        }
//        SingleInit init=null;
//        Class<SingleInit> singleInitClass = SingleInit.class;
//        LLogger.d("define SingleInit class:"+singleInitClass);
//
//        try {
//          Thread.sleep(2000);
//        } catch (InterruptedException e) {
//          e.printStackTrace();
//        }
//
//        init=SingleInit.getInstance();
//        LLogger.d(init.toString());
//      }
//    });
//
//    threadInstance.start();
//
//    threadInstance.join();
//
//    LLogger.d("main thread exit");
  }
}
