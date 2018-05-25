package interclass;
import com.licola.llogger.LLogger;
/**
 * Created by LiCola on 2017/9/15.
 */
public class OuterInnerClassTest {
  private int data=100;

  public static final void main(String[] args){
    LLogger.d("main");
  }

  public class Inner{
    public void innerMethod(){
      LLogger.d("out data:"+data);
      OuterInnerClassTest.this.action();
    }
  }


  private void action(){
    LLogger.d("outer action method");
  }

  public void test(){
    Inner inner=new Inner();
    inner.innerMethod();
  }
}
