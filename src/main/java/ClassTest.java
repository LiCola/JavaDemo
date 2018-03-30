import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by LiCola on 2017/7/31.
 */
public class ClassTest {
    public static final void main(String[] args)
        throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
      test1();

      try {
        Class<?> aClass = Class.forName("String.");
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
    }

  private static void test1()
      throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
    List<String> list= new ArrayList<>(Arrays.asList("Hello","World","Java"));
    Class<? extends List> aClass = list.getClass();
    Field[] fields = aClass.getDeclaredFields();
    for (Field field : fields) {
      field.setAccessible(true);
      System.out.println(field.getName()+":"+field.get(list));
    }

    Constructor<?>[] declaredConstructors = aClass.getDeclaredConstructors();
    Constructor<? extends List> declaredConstructor = aClass.getDeclaredConstructor(int.class);
    List instance = declaredConstructor.newInstance(3);
    System.out.println("List class isInstance:"+List.class.isInstance(list));


  }
}
