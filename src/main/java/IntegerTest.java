import com.licola.llogger.LLogger;

/**
 * Created by 李可乐 on 2017/5/15.
 */
public class IntegerTest {


  public static void main(String[] args) {
//        testOperate();
    testHeightOperate();
    testBitOneBitCount();
  }

  private static void testBitOneBitCount() {
    int data=11;
    int tmp=data;

    int count=0;
    while (tmp>0){
      count++;
      tmp&=(tmp-1);
    }

    LLogger.d("count:"+count);
  }

  private static void testHeightOperate() {
    int result1 = Integer.highestOneBit(15);
    LLogger.d("highestOneBit:" + result1);

    int data1 = -2;
    int data2 = 7;
    String bitData1 = Integer.toBinaryString(data1);
    String bitData2 = Integer.toBinaryString(data2);
    LLogger.d("data1:" + data1 + " bit:" + bitData1);
    LLogger.d("data2:" + data2 + " bit:" + bitData2);
    LLogger.d("result:" + (data1 & data2));
  }

  private static void testOperate() {
    Integer i1 = 40;
    Integer i2 = 40;
    Integer i3 = 0;
    Integer i4 = new Integer(40);
    Integer i5 = new Integer(40);
    Integer i6 = new Integer(0);
    LLogger.d((i1 == i2));//常量池机制 会返回true
    LLogger.d(i1 == i2 + i3);//常量池的对象 结果是什么？
    LLogger.d(i4 == i5);//它们都直接new创建对象 指向不同的内存地址 结果为：false
    LLogger.d(i4 == i5 + i6);//直接创建的对象 结果又是什么？
//        Integer v1=40;
//        Integer v2=40;
//        LLogger.d(v1==v2);//输入 true
//
//        Integer v3=128;
//        Integer v4=128;
//        LLogger.d(v3==v4);//输出 false
    int value1 = 0;
    int value2 = 0;

    LLogger.d(value1++);
    LLogger.d(value1);
    LLogger.d(++value2);
    LLogger.d(value2);

    int result = 0;
    for (int i = 0; i < 10; i++) {
      result = result + 1;
    }
  }
}

