
import com.licola.llogger.LLogger;

/**
 * Created by 李可乐 on 2017/5/17.
 */
public class BooleanTest {
    public static void main(String[] args) {
        Boolean value1 = true;
        Boolean value2 = true;
        Boolean value3 = new Boolean(true);
        LLogger.d(value1 == value2);//输出 true
        LLogger.d(value1 == value3);//输出 false

        LLogger.d(value1 == (boolean) value3);//输出 true
        LLogger.d(value1 && value2);//输出 true


    }
}
