package classload;
import com.licola.llogger.LLogger;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * Created by LiCola on 2017/12/18.
 */
public class ClassLoaderTest {


  public static final void main(String[] args)
      throws ClassNotFoundException, IllegalAccessException, InstantiationException {
    testMyClassLoad();
//    testDiffClassLoad();
  }

  private static void testDiffClassLoad() {
    LLogger.d("String Class Loader:"+String.class.getClassLoader());
    LLogger.d("int Class Loader:"+int.class.getClassLoader());
    LLogger.d("HashMap Class Loader:"+HashMap.class.getClassLoader());
    LLogger.d("ClassLoaderTest Class Loader:"+ClassLoaderTest.class.getClassLoader());
  }

  private static void testMyClassLoad()
      throws ClassNotFoundException, InstantiationException, IllegalAccessException {
    ClassLoader mClassLoader = new ClassLoader() {
      @Override
      public Class<?> loadClass(String name) throws ClassNotFoundException {
        String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
        LLogger.d("name:"+name+" filename:"+fileName);

        InputStream inputStream = getClass().getResourceAsStream(fileName);
        if (inputStream == null) {
          return super.loadClass(name);
        }

        byte[] bytes = null;
        try {
          bytes = new byte[inputStream.available()];
          inputStream.read(bytes);
        } catch (IOException e) {
          throw new ClassNotFoundException(name, e);
        }
        return defineClass(name, bytes, 0, bytes.length);
      }
    };

    Object object = mClassLoader.loadClass("classload.ClassLoaderTest").newInstance();

    LLogger.d(object.getClass());

    LLogger.d(object instanceof ClassLoaderTest);

  }
}
