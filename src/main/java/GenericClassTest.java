/**
 * Created by LiCola on 2018/3/8.
 */
public class GenericClassTest<E extends String> {

  public void set(E e){
    char c = e.charAt(0);
    System.out.println("E method:"+c);
  }

  public E get(int index){
    return null;
  }
}
