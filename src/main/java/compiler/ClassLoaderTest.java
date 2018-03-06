package compiler;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * Created by LiCola on 2017/12/18.
 */
public class ClassLoaderTest {


  public static final void main(String[] args)
      throws ClassNotFoundException, IllegalAccessException, InstantiationException {
    ClassLoader mClassLoader = new ClassLoader() {
      @Override
      public Class<?> loadClass(String name) throws ClassNotFoundException {
        String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
        System.out.println("name:"+name+" filename:"+fileName);

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

    Object object = mClassLoader.loadClass("javaer.compiler.ClassLoaderTest").newInstance();

    System.out.println(object.getClass());

    System.out.println(object instanceof ClassLoaderTest);

    System.out.println("String Class Loader:"+String.class.getClassLoader());
    System.out.println("int Class Loader:"+int.class.getClassLoader());
    System.out.println("HashMap Class Loader:"+HashMap.class.getClassLoader());
    System.out.println("ClassLoaderTest Class Loader:"+ClassLoaderTest.class.getClassLoader());
  }
}
