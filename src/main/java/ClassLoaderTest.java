
import com.licola.llogger.LLogger;

/**
 * Created by LiCola on 2017/11/28.
 */
public class ClassLoaderTest {

  public static class TargetClass{
    static {
      LLogger.d("static block invoke");
    }
  }

  public static final void main(String[] args){
    ClassLoader classLoader=ClassLoaderTest.class.getClassLoader();
    while (classLoader!=null){
      LLogger.d(classLoader.getClass().getName());
      classLoader=classLoader.getParent();
    }
    //输出 Java类库的ClassLoader 系统ClassLoader由C++实现返回null
    LLogger.d(String.class.getClassLoader());


    ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
    LLogger.d("ClassLoader 静态方法 能够得到默认的系统类加载器："+systemClassLoader);
    try {
      Class<?> aClass = systemClassLoader.loadClass("java.util.ArrayList");
      ClassLoader loader = aClass.getClassLoader();
      //由于委派机制 及时使用Launcher$AppClassLoader加载器 加载类 但是ArrayList也是由BootStrap ClassLoader加载完成的
      LLogger.d("委派机制 使得指定加载器 会从BootStrap ClassLoader中加载 输出为:"+loader);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    //拼接内部静态类的全路径名称
    String targetClassName=ClassLoaderTest.class.getName()+"$TargetClass";
    try {
      //ClassLoader 加载 不会执行静态代码块
      systemClassLoader.loadClass(targetClassName);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    try {
      //forName会执行静态代码块
      Class.forName(targetClassName);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
}
