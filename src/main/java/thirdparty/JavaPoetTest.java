package thirdparty;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import java.io.File;
import java.io.IOException;
import javax.lang.model.element.Modifier;
import com.sun.source.util.TreePath;


/**
 * Created by LiCola on 2018/6/6.
 */
public class JavaPoetTest {

  public static final void main(String[] args) throws IOException {
    MethodSpec main = MethodSpec.methodBuilder("main")
        .addModifiers(Modifier.PUBLIC,Modifier.STATIC)
        .returns(void.class)
        .addParameter(String[].class, "args")
        .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
        .build();

    TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
        .addJavadoc("test doc 中文")
        .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
        .addMethod(main)
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
