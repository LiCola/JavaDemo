package bytecode;

import java.io.IOException;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

/**
 * @author LiCola
 * @date 2019-06-20
 */
public class AsmCreateClass {

  public static void main(String[] args) throws IOException {
    ClassReader reader=new ClassReader("");

    ClassWriter writer=new ClassWriter(ClassWriter.COMPUTE_MAXS);

  }
}
