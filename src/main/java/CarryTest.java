
import com.licola.llogger.LLogger;

import java.util.Locale;

/**
 * Created by LiCola on 2017/8/5.
 */
public class CarryTest {

  public static final void main(String[] args){
    testFormat();
  }

  public static void testFormat() {
    float input = 2.156f;
    double da= input*100D/100.0D;
    int inputOffset = (int) (input * 100 / 100.0);
    LLogger.d("input = " + input);
    String result = String.format(Locale.CHINA, "%.2f", (int) (input * 100) / 100.0);
    LLogger.d("result " + result);
  }
}
