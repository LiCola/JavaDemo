package bytecode;

import com.licola.llogger.LLogger;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.Modifier;
import javassist.NotFoundException;

/**
 * @author LiCola
 * @date 2019-06-19
 */
public class JavassistCreateClass {

  public static void main(String[] args)
      throws NotFoundException, CannotCompileException, IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

    LLogger.init();

    ClassPool classPool = ClassPool.getDefault();

    //定义类
    CtClass ctClass = classPool.makeClass("User");

    CtClass classStr = classPool.get("java.lang.String");

    //添加字段
    CtField ctField = new CtField(classStr, "name", ctClass);
    ctField.setModifiers(Modifier.PRIVATE);
    ctClass.addField(ctField);

    //添加构造方法
    CtClass[] parameters = {classStr};
    CtConstructor ctConstructor = new CtConstructor(parameters, ctClass);
    ctConstructor.setBody("{this.name=$1;}");
    ctClass.addConstructor(ctConstructor);

    //添加方法
    ctClass.addMethod(CtNewMethod.setter("setName", ctField));
    ctClass.addMethod(CtNewMethod.getter("getName", ctField));

    CtMethod toStringMethod = new CtMethod(classStr, "toString", null, ctClass);
    toStringMethod.setModifiers(Modifier.PUBLIC);
    toStringMethod.setBody("{return \"name=\"+$0.name;}");
    ctClass.addMethod(toStringMethod);

    ctClass.writeFile("./src/main/java/bytecode");
    LLogger.d(ctClass.toString());

    Class<?> aClass = ctClass.toClass();
    Constructor<?> constructor = aClass.getConstructor(String.class);
    Object instance = constructor.newInstance("cola");
    LLogger.d(aClass.getMethod("getName").invoke(instance));
    LLogger.d(aClass.getMethod("toString").invoke(instance));
  }
}
