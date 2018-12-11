package reflection;

import com.licola.llogger.LLogger;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author LiCola
 * @date 2018/11/20
 */
public class MethodTest {

  public void age(int age) {
    LLogger.d("int age=" + age);
  }


  public void age(Integer age) {
    LLogger.d("Integer age=" + age);
  }

  public static final void main(String[] args)
      throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    MethodTest methodTest = new MethodTest();
    Method methodInt = methodTest.getClass().getMethod("age", int.class);
    methodInt.invoke(methodTest,new Integer(27));
    methodInt.invoke(methodTest,28);

    Method methodInteger = methodTest.getClass().getMethod("age", Integer.class);
    methodInteger.invoke(methodTest,new Integer(27));
    methodInteger.invoke(methodTest,27);

  }
}
