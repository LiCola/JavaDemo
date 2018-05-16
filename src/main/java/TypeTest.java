/**
 * Created by LiCola on 2017/6/10.
 */
public class TypeTest {

  public static void main(String[] args) {
    testPrimitive();

    testChar();

    testCast();

    testWrapType();

  }

  private static void testWrapType() {
    Byte b1 = -1;
    Integer i1 = 3;
    System.out.println(i1.equals(1 + 2));
    Long l1 = 3L;
    System.out.println(l1 == (1 + 2));
    System.out.println(l1.equals(1 + 2));
    ++i1;
  }

  private static void testCast() {
    short s1 = 1;
    s1 += 1;
    System.out.println("s1:" + s1);

    boolean floatCheck = 3 * 0.1 == 0.3;
    System.out.println("(3*0.1==0.3)=" + floatCheck);

    float f1 = 3.4f;
    double d1 = 3.4d;
    double d2 = f1;
    float f2 = (float) d1;
    System.out
        .println(" decimal  direct :" + "float:" + f1 + " double:" + d1 + " equal:" + (f1 == d1));
    System.out.println(
        " decimal  cast  :" + "float cast:" + d2 + " double:" + d1 + " equal:" + (d2 == d1));
    System.out.println(
        " decimal  cast  :" + "float cast:" + f1 + " double cast:" + f2 + " equal:" + (f1 == f2));
  }

  private static void testChar() {
    char char1 = '斗';//字符定义 直接
    char char2 = '鬥';//繁体 编码上无关联
    char char3 = 26007;//字符使用16位=2bit 表示 最大可以描述0-65536范围的内容
    char char4 = 0x6597;//16进制表示
    char char5 = '\u6597';//Unicode编码 表示
    System.out.println((int) char1);
    System.out.println((int) char2);
    System.out.println(char3);
    System.out.println(char4);
    System.out.println(char5);
  }

  private static void testPrimitive() {
    int data1 = 1 + 1;
    System.out.println(data1);

    byte value1 = 8;
    int data2 = 1 + value1;
    System.out.println(data2);

    short value2 = 10;
    int data3 = 1 + value2;
    System.out.println(data3);

    Integer value4 = 1;
    Long data4 = value4 + 1L;
    System.out.println(data4);
  }
}
