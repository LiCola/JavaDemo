import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import com.licola.llogger.LLogger;

/**
 * Created by LiCola on 2017/12/11.
 */
public class ReflectionTest {

  public static final void main(String[] args){
    reflectClass();
    reflectArray();
    try {
      reflectGenericClass();
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    }
  }



  private static void reflectClass() {
    try {
      Class<?> aClass = Class.forName("java.lang.Integer");
      LLogger.d(aClass.getName());
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  private static void reflectArray() {
    Object newInstance = Array.newInstance(int.class, 5);//反射中 专门用于数组的反射类
    LLogger.d(newInstance.getClass());
  }

  private static void reflectGenericClass() throws NoSuchFieldException {
    Class<GenericTest> aClass = GenericTest.class;

    //类的 类型参数 多个遍历
    TypeVariable<Class<GenericTest>>[] typeParameters = aClass.getTypeParameters();
    LLogger.d("class Type Parameters 类的参数类型 ---");
    for (TypeVariable<Class<GenericTest>> typeVariable : typeParameters) {
      LLogger.d(typeVariable.getName()+" extends "+ Arrays.toString(typeVariable.getBounds()));
    }

    //类的 字段 以及泛型信息 多个遍历
    LLogger.d("class declared fields 类的域信息 ---");
    Field[] fields = aClass.getDeclaredFields();
    for (Field field : fields) {
      LLogger.d(field.toString());

      Type genericType = field.getGenericType();
      if (genericType instanceof ParameterizedType){
         ParameterizedType parameterizedType= (ParameterizedType) genericType;
        LLogger.d(" -raw type:"+parameterizedType.getRawType()+" type arguments:"+Arrays.toString(parameterizedType.getActualTypeArguments()));
      } else {
        LLogger.d(" -this field type none");
      }
    }

    LLogger.d("class methods 类的方法信息 ---");
    Method[] methods = aClass.getDeclaredMethods();
    for (Method method : methods) {
      LLogger.d(method.toString());

    }

  }

  static class GenericTest<U extends Comparable<U>,V>{
    U uType;
    V vType;

    List<String> stringList;
    Map<U,V> uvMap;

    public U test(List<? extends Number> numbers){
      return null;
    }
  }
}
