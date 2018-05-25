package reflection;
import com.licola.llogger.LLogger;
/**
 * Created by LiCola on 2018/5/7.
 */
public class BaseTest {

  public static final void main(String[] args){
    Class<Integer> type = Integer.TYPE;
    LLogger.d(type);

  }
}
