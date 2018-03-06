/**
 * Created by LiCola on 2018/1/17.
 */
public class MathTest {

  public static final void main(String[] args){
    int round = Math.round(11.5f);
    int roundMinus = Math.round(-11.5f);
    System.out.println("round："+round+" roundMinus:"+roundMinus);

    double ceil = Math.ceil(11.3);
    double ceilMinus = Math.ceil(-11.3);
    System.out.println("ceil:"+ceil+" ceilMinus:"+ceilMinus);
  }
}
