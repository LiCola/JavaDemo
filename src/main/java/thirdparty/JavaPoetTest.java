package thirdparty;

import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import java.io.File;
import java.io.IOException;
import javax.lang.model.element.Modifier;


/**
 * Created by LiCola on 2018/6/6.
 */
public class JavaPoetTest {

  public static final void main(String[] args) throws IOException {
    MethodSpec mainMethod = MethodSpec.methodBuilder("main")
        .addModifiers(Modifier.PUBLIC,Modifier.STATIC)
        .returns(void.class)
        .addParameter(String[].class, "args")
        .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
        .build();

    MethodSpec throwMethod=MethodSpec.methodBuilder("throwMethod")
        .addModifiers(Modifier.PUBLIC,Modifier.FINAL)
        .addException(IOException.class)
        .returns(TypeName.VOID)
        .build();

    TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorldByJavaPoet")
        .addJavadoc("test doc 中文")
        .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
        .addMethod(mainMethod)
        .addMethod(throwMethod)
        .build();

    JavaFile javaFile = JavaFile.builder("thirdparty", helloWorld)
        .build();

    File fileDir=new File("./src/main/java");
    if (!fileDir.exists()){
      fileDir.mkdirs();
    }

    javaFile.writeTo(fileDir);

  }
}
