import com.licola.llogger.LLogger;

/**
 * Created by LiCola on 2018/3/8.
 */
public class GenericClassTest<E extends String> {

  public void set(E e){
    char c = e.charAt(0);
    LLogger.d("E method:"+c);
  }

  public E get(int index){
    return null;
  }
}
