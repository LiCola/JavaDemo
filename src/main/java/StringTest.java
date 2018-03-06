import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;

/**
 * Created by 李可乐 on 2017/5/16.
 */
public class StringTest {

  public static void main(String[] args) throws UnsupportedEncodingException {

    String data1 = "data1";
    String data2 = "data1";
    System.out.println(data1 == data2);//肯定会输出true
    //String的构造问题
    String value1 = "value";
    String value2 = "value";
    String value3 = new String("value");
    String value4 = new String("value").intern();
    System.out.println(value1 == value2);
    System.out.println(value1 == value3);
    System.out.println(value1 == value4);

    //String的拼接问题
    //只有使用引号包含文本的方式创建的String对象之间使用“+”连接产生的新对象才会被加入字符串池中。
    //对于所有包含new方式新建对象（包括null）的“+”连接表达式，它所产生的新对象都不会被加入字符串池中
    String value5 = "value" + "value";
    String value6 = value1 + value1;
    String value7 = new String("value") + new String("value");
    String value8 = value1 + value1;
    String value9 = "value" + "value";
    System.out.println(value5 == value6);
    System.out.println(value5 == value7);
    System.out.println(value5 == value9);
    System.out.println(value6 == value7);
    System.out.println(value6 == value8);

    String key = "";
    switch (key) {
      case "":

        break;
      case "0":

        break;

        default:

          break;
    }

    String s1 = "abc";
    StringBuffer s2 = new StringBuffer(s1);

    System.out.println(s1.equals(s2));

    String strCode = new String(s1.getBytes("UTF-8"), Charset.forName("GB2312"));

    final int i1=10;
    String s3="a10";
    String s4="a"+i1;
    System.out.println(s3==s4);

  }
}
