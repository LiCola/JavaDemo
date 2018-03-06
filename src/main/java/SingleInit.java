/**
 * Created by LiCola on 2017/11/30.
 */
public class SingleInit {

  public final static String name;

  public volatile static SingleInit ourInstance;

  public static SingleInit getInstance() {
//    System.out.println("static getInstance invoke");
    if (ourInstance == null) {
      ourInstance = new SingleInit();
    }
    return ourInstance;
  }

  private SingleInit() {
//    System.out.println("single init method invoke this:" + this.toString());
  }

  static {
    name = "123s";
//    System.out.println("single static block invoke");
  }

}
