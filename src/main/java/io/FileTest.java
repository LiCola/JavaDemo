package io;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import com.licola.llogger.LLogger;

/**
 * Created by LiCola on 2017/6/7.
 */
public class FileTest {
  public static void main(String[] args) throws IOException {
    File file = new File("./src/main/java/io/poster-1.jpg");
    LLogger.d(file.length());
  }


}
