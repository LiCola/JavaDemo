package compiler;

import java.util.Arrays;
import java.util.List;

/**
 * @author LiCola
 * @date 2019-06-18
 */
public class ForTest {

  public static void main(String[] args) {

    String[] strings=new String[]{"1","2","3"};

    for (String string : strings) {
      System.out.printf(string);
    }

    List<String> list = Arrays.asList("1", "2", "3");
    for (String s : list) {
      System.out.printf(s);
    }
  }
}
