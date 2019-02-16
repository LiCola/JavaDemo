import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import com.licola.llogger.LLogger;

/**
 * Created by 李可乐 on 2017/5/16.
 */
public class StringTest {

  public static final String PREFIX="prefix";
  public static final String SUFFIX="suffix";

  public static final String JOIN=PREFIX+SUFFIX;

  public static void main(String[] args) throws UnsupportedEncodingException {

//    String value1 = testInit();
//    testJoint(value1);
//    testWrap();

    testCode();
    String target = "https://uboxs-img.oss-cn-hangzhou.aliyuncs.com/hbc.zip";
    LLogger.d(target);

  }

  public void join(){
    String with=PREFIX+SUFFIX;
  }

  private static void testCode() throws UnsupportedEncodingException {
    String strCode = new String("asd".getBytes("UTF-8"), Charset.forName("GB2312"));
  }

  private static void testWrap() {
    String s1 = "abc";
    StringBuffer s2 = new StringBuffer(s1);

    LLogger.d(s1.equals(s2));
  }

  private static void testJoint(String value1) {
    //String的拼接问题
    //只有使用引号包含文本的方式创建的String对象之间使用“+”连接产生的新对象才会被加入字符串池中。
    //对于所有包含new方式新建对象（包括null）的“+”连接表达式，它所产生的新对象都不会被加入字符串池中
    String value5 = "value" + "value";
    String value6 = value1 + value1;
    String value7 = new String("value") + new String("value");
    String value8 = value1 + value1;
    String value9 = "value" + "value";
    LLogger.d(value5 == value6);
    LLogger.d(value5 == value7);
    LLogger.d(value5 == value9);
    LLogger.d(value6 == value7);
    LLogger.d(value6 == value8);

    final int i1 = 10;
    String s3 = "a10";
    String s4 = "a" + i1;
    LLogger.d(s3 == s4);
  }

  private static String testInit() {
    String data1 = "data1";
    String data2 = "data1";
    LLogger.d(data1 == data2);//肯定会输出true
    //String的构造问题
    String value1 = "value";
    String value2 = "value";
    String value3 = new String("value");
    String value4 = new String("value").intern();
    LLogger.d(value1 == value2);
    LLogger.d(value1 == value3);
    LLogger.d(value1 == value4);
    return value1;
  }
}
