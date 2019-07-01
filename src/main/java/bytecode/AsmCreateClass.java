package bytecode;

import com.licola.llogger.LLogger;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author LiCola
 * @date 2019-06-20
 */
public class AsmCreateClass {

  public static void main(String[] args)
      throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException {
    LLogger.init();
    printClass();
    installGenerationClass(writeClass());
  }

  private static void installGenerationClass(byte[] classFileBytes)
      throws IOException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
    File file = new File("./src/main/java/bytecode/AsmGeneration.class");
    FileOutputStream outputStream = new FileOutputStream(
        file);
    outputStream.write(classFileBytes);
    outputStream.close();
    LLogger.d("创建类:" + file.getAbsolutePath());

    Class<?> aClass = new MyClassLoader().defineClass("bytecode.AsmGeneration", classFileBytes);
    Object newInstance = aClass.newInstance();

    Method methodGetName = aClass.getDeclaredMethod("getName");
    Object methodResult = methodGetName.invoke(newInstance, null);
    LLogger.d(methodGetName, methodResult);
  }

  private static class MyClassLoader extends ClassLoader {

    public Class<?> defineClass(String name, byte[] b) {
      return defineClass(name, b, 0, b.length);
    }
  }

  private static byte[] writeClass() throws IOException {
    ClassWriter writer = new ClassWriter(0);
    writer
        .visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "bytecode/AsmGeneration", null, "java/lang/Object",
            null);
    writer.visitField(Opcodes.ACC_PRIVATE, "name", "Ljava/lang/String;", null, null).visitEnd();
    writer.visitField(Opcodes.ACC_PRIVATE, "age", "I", null, null).visitEnd();

    MethodVisitor methodConstructVisitor = writer
        .visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
    methodConstructVisitor.visitVarInsn(Opcodes.ALOAD, 0);
    methodConstructVisitor
        .visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
    methodConstructVisitor.visitInsn(Opcodes.RETURN);
    methodConstructVisitor.visitMaxs(1, 1);
    methodConstructVisitor.visitEnd();

    MethodVisitor methodGetName = writer
        .visitMethod(Opcodes.ACC_PUBLIC, "getName", "()Ljava/lang/String;", null, null);
    methodGetName.visitVarInsn(Opcodes.ALOAD, 0);
    methodGetName
        .visitFieldInsn(Opcodes.GETFIELD, "bytecode/AsmGeneration", "name", "Ljava/lang/String;");
    methodGetName.visitInsn(Opcodes.ARETURN);
    methodGetName.visitMaxs(2, 2);
    methodGetName.visitEnd();

    byte[] classFileBytes = writer.toByteArray();
    return classFileBytes;
  }

  private static void printClass() throws IOException {
    ClassReader reader = new ClassReader("bytecode.TargetClass");
    ClassPrinter printer = new ClassPrinter();
    reader.accept(printer, 0);
  }

  static class ClassPrinter extends ClassVisitor {

    public ClassPrinter() {
      super(Opcodes.ASM5);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName,
        String[] interfaces) {
      super.visit(version, access, name, signature, superName, interfaces);
      LLogger.d(version, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature,
        String[] exceptions) {
      MethodVisitor methodVisitor = super
          .visitMethod(access, name, descriptor, signature, exceptions);
      LLogger.d(access, name, descriptor, signature, exceptions);

      return new MethodPrint(Opcodes.ASM5,methodVisitor);
    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature,
        Object value) {
      FieldVisitor fieldVisitor = super.visitField(access, name, descriptor, signature, value);
      LLogger.d(access, name, descriptor, signature, value);
      return fieldVisitor;
    }

    @Override
    public void visitAttribute(Attribute attribute) {
      super.visitAttribute(attribute);
      LLogger.d(attribute);
    }

    @Override
    public void visitEnd() {
      super.visitEnd();
      LLogger.d();
    }
  }

  private static class MethodPrint extends MethodVisitor{

    public MethodPrint(int api, MethodVisitor methodVisitor) {
      super(api, methodVisitor);
    }

    @Override
    public void visitInsn(int opcode) {
      super.visitInsn(opcode);
      LLogger.d(opcode);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String descriptor,
        boolean isInterface) {
      super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
      LLogger.d(opcode,owner,name,descriptor,isInterface);
    }
  }
}
