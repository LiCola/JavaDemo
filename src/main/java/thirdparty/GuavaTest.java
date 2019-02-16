package thirdparty;

import com.google.common.base.Preconditions;

/**
 * @author LiCola
 * @date 2019/1/21
 */
public class GuavaTest {


  public static void main(String[] args){
      dothing(null);
  }

  public static void dothing(String input){
    Preconditions.checkNotNull(input);
  }
}
