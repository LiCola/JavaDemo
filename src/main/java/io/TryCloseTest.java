package io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author LiCola
 * @date 2019-03-08
 */
public class TryCloseTest {


  public static final void main(String[] args) throws IOException {

    File targetFile = new File("./src/main/java/io/TargetFile.txt");
    targetFile.createNewFile();
    outputAutoClose(targetFile);
    outputClose(targetFile);
  }

  private static void outputAutoClose(File file) throws IOException {

    try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
      fileOutputStream.write("file write".getBytes(Charset.forName("UTF-8")));
    }
  }

  private static void outputClose(File file) throws IOException {

    FileOutputStream outputStream = new FileOutputStream(file);
    try (FileOutputStream fileOutputStream = outputStream) {
      fileOutputStream.write("file write".getBytes(Charset.forName("UTF-8")));
    } finally {
      outputStream.close();
    }
  }

}
