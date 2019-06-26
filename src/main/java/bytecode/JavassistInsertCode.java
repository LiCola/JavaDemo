package bytecode;

import com.licola.llogger.LLogger;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

/**
 * @author LiCola
 * @date 2019-06-19
 */
public class JavassistInsertCode {

  public static void main(String[] args)
      throws NotFoundException, CannotCompileException, IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

    //内部使用同一系列 类加载器 如果前面使用了类 类加载已经把类加载入内存 修改完类再尝试toClass 就会 attempted  duplicate class definition for name:
//    TargetClass targetInstance=new TargetClass();
//    targetInstance.print();


    ClassPool classPool = ClassPool.getDefault();

    CtClass targetCtClass = classPool.get("bytecode.TargetClass");

    CtMethod ctMethod = targetCtClass.getDeclaredMethod("print");
    ctMethod.insertBefore("System.out.printf(\"before 字节码插入内容\\n\",null);");
    ctMethod.insertAfter("System.out.printf(\"after 字节码插入内容\\n\",null);");

    targetCtClass.writeFile("./src/main/java");
    LLogger.d(targetCtClass.toString());

    Class<?> targetClassNew= targetCtClass.toClass();
    Object targetInstanceNew = targetClassNew.getConstructor().newInstance();
    Method method = targetClassNew.getMethod("print");
    method.invoke(targetInstanceNew);
  }
}
