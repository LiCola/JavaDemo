package collections;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by LiCola on 2017/10/20.
 */
public class CollectionsTest {
  public static final void main(String[] args){
//    baseTest();
    baseArray();
  }

  private static void baseArray() {
  }

  private static void baseTest() {
    List<String> list = new ArrayList<>(2);
    list.add("guan");
    list.add("bao");
    String[] array = new String[list.size()];
    array = list.toArray(array);
    array[0]="0";
    System.out.println(list.get(0)==array[0]);
    Class class1= int[].class;
    Class class2= int.class;

    System.out.println(class1==class2);

    String[] str = new String[] { "a", "b" };
    List list2 = Arrays.asList(str);
    /**
     * Arrays.asList() 返回的是List的内部实现子类
     * 只支持读操作 写操作未覆盖 会抛出默认UnsupportedOperationException错误
     */
    try {
      list2.add("3");
    } catch (UnsupportedOperationException e) {
      e.printStackTrace();
    }
    /**
     * 修改目标源 该目标源所产生的List也会修改
     * 因为他们本质上都指向相同内存地址 只是让数组支持了List的操作
     * 体现的是适配器模式
     */
    str[0]="0";
  }
}
