package interclass;

/**
 * Created by LiCola on 2017/9/15.
 */
public class OuterInnerClassTest {
  private int data=100;

  public static final void main(String[] args){
    System.out.println("main");
  }

  public class Inner{
    public void innerMethod(){
      System.out.println("out data:"+data);
      OuterInnerClassTest.this.action();
    }
  }


  private void action(){
    System.out.println("outer action method");
  }

  public void test(){
    Inner inner=new Inner();
    inner.innerMethod();
  }
}
