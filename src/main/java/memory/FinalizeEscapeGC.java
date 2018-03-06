package memory;

/**
 * Created by LiCola on 2017/12/14.
 * 次代码演示两点：1：对象可以被GC时自我拯救 2：但是这个机会只有一次，因为一个对象的finalize最多被系统自动调用一次
 */
public class FinalizeEscapeGC {

  public static FinalizeEscapeGC SAVE_HOOK =null;

  public void isAlive(){
    System.out.println("object i am still alive");
  }

  @Override
  protected void finalize() throws Throwable {
    super.finalize();
    System.out.println("finalize method invoke");
    //在被完结前 用静态变量钩子重新指向 自己 尝试拯救
    FinalizeEscapeGC.SAVE_HOOK =this;
  }

  public static final void main(String[] args) throws InterruptedException {
    SAVE_HOOK=new FinalizeEscapeGC();

    //置null 并尝试GC 让对象拯救自己一次
    SAVE_HOOK=null;
    System.gc();
    //GC线程优机低 暂停主线程等待
    Thread.sleep(500);

    if (SAVE_HOOK!=null){
      SAVE_HOOK.isAlive();
    }else {
      System.out.println("object i am dead");
    }


    //置null 并尝试GC 以上代码完全相同 但是拯救失败
    SAVE_HOOK=null;
    System.gc();
    //GC线程优机低 暂停主线程等待
    Thread.sleep(500);

    if (SAVE_HOOK!=null){
      SAVE_HOOK.isAlive();
    }else {
      System.out.println("object i am dead");
    }

  }
}
