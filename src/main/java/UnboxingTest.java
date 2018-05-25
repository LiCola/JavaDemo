import com.licola.llogger.LLogger;

public class UnboxingTest {

  public static final void main(String[] args) {
//    Object object = true ? new Double(1) : new Integer(2);
//    LLogger.d("object = " + object);
//
//    Object object2 = true ? new Integer(1) : new Double(2);
//    LLogger.d("object2 = " + object2);

//    Integer i = new Integer(1);
//    if (i.equals(1))
//      i = null;
//    Double d = new Double(2.0);
//    Object o;
////    o = true ? i : d; // NullPointerException!
//    if (true){
//      o=i;
//    }else {
//      o=d;
//    }
////    LLogger.d(o.getClass().getName());
//    LLogger.d(o);

    for (int i = 0; i < 10; i++) {
      LLogger.d((Integer) i);
    }


    String string="data";
    if (string instanceof String){
      LLogger.d("cast success");
    }
  }
}
