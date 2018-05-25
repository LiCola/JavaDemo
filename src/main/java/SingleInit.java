import com.licola.llogger.LLogger;

/**
 * Created by LiCola on 2017/11/30.
 */
public class SingleInit {

  public final static String name;

  public volatile static SingleInit ourInstance;

  public static SingleInit getInstance() {
//    LLogger.d("static getInstance invoke");
    if (ourInstance == null) {
      ourInstance = new SingleInit();
    }
    return ourInstance;
  }

  private SingleInit() {
//    LLogger.d("single init method invoke this:" + this.toString());
  }

  static {
    name = "123s";
//    LLogger.d("single static block invoke");
  }

}
