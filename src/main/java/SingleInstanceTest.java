/**
 * Created by LiCola on 2017/11/30.
 */
public class SingleInstanceTest {

  public static final void main(String[] args) throws InterruptedException {

    SingleInit init=null;
    if (init==null){
      System.out.println("init null");
    }else {
      System.out.println("init not null");
    }
    System.out.println(SingleInit.getInstance());

//    System.out.println(SingleInit.name);

//   Thread threadInstance= new Thread(new Runnable() {
//      @Override
//      public void run() {
//        System.out.println("work thread run");
//        try {
//          Thread.sleep(2000);
//        } catch (InterruptedException e) {
//          e.printStackTrace();
//        }
//        SingleInit init=null;
//        Class<SingleInit> singleInitClass = SingleInit.class;
//        System.out.println("define SingleInit class:"+singleInitClass);
//
//        try {
//          Thread.sleep(2000);
//        } catch (InterruptedException e) {
//          e.printStackTrace();
//        }
//
//        init=SingleInit.getInstance();
//        System.out.println(init.toString());
//      }
//    });
//
//    threadInstance.start();
//
//    threadInstance.join();
//
//    System.out.println("main thread exit");
  }
}
