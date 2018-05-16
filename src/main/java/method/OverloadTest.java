package method;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * Created by LiCola on 2018/5/7.
 */
public class OverloadTest {

  static abstract class Human {

  }

  static class Man extends Human {

  }

  static class Woman extends Human {

  }

  public void sayHi(Human human) {
    System.out.println("hi human");
  }


  public void sayHi(Man man) {
    System.out.println("hi man");
  }


  public void sayHi(Woman woman) {
    System.out.println("hi woman");
  }

  public static final void main(String[] args){
    Human man=new Man();
    Woman woman=new Woman();

    OverloadTest test=new OverloadTest();
    test.sayHi(man);
    test.sayHi(woman);


  }
}
