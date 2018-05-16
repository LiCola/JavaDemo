package method;

import java.lang.invoke.MethodType;

/**
 * Created by LiCola on 2018/5/7.
 */
public class MethodHandlesTest {

  public static final void main(String[] args){
    MethodType methodType=MethodType.methodType(void.class,String.class);
//    MethodHandles.lookup().findVirtual().bindTo().invokeExact()
  }

}
