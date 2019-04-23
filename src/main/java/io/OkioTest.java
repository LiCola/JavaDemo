package io;

import com.licola.llogger.LLogger;
import java.io.File;
import java.io.IOException;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;
import okio.Source;

/**
 * Created by LiCola on 2017/9/25.
 */
public class OkioTest {

  private static final char lineBreak = '\n';

  public static final void main(String[] args) throws IOException {

    LLogger.init();

//    File file = new File("./src/main/java/io/OkioData.txt");
    File file = new File("./src/main/java/io/bytes.txt");
    if (!file.exists()) {
      boolean newFile = file.createNewFile();
    }
//    writeFile(file);
    readFile(file);

//    File fileDest = new File("./src/main/java/io/OkioDataCopy.txt");
//    copyFile(file, fileDest);
  }

  private static void copyFile(File srcFile, File destFile) throws IOException {
    BufferedSource source=Okio.buffer(Okio.source(srcFile));
    BufferedSink sink=Okio.buffer(Okio.sink(destFile));
    sink.writeAll(source);
    sink.flush();
    sink.close();

    source.close();
  }

  private static void readFile(File file) throws IOException {
    Source source = Okio.source(file);
    BufferedSource bufferedSource = Okio.buffer(source);
    byte[] readByte = bufferedSource.readByteArray();
    LLogger.d(new String(readByte));
//    String line2 = bufferedSource.readUtf8LineStrict();
//    LLogger.d("line2:" + line2);

  }

  private static void writeFile(File file) throws IOException {
    Sink sink = Okio.sink(file);
    BufferedSink bufferedSink = Okio.buffer(sink);
//    bufferedSink.writeInt(32);
//    bufferedSink.writeByte(lineBreak);

    bufferedSink.writeUtf8("value1");
    bufferedSink.writeByte(lineBreak);
    bufferedSink.writeUtf8("value2");
    bufferedSink.writeByte(lineBreak);

    bufferedSink.flush();
    bufferedSink.close();
  }
}
