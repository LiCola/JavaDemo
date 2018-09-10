package io;

import com.licola.llogger.LLogger;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.Scanner;

/**
 * @author LiCola
 * @date 2018/8/13
 */
public class MapperByteBufferTest {

  public static final void main(String[] args) throws IOException {
    File file = new File("./src/main/java/io/byte.txt");

    String content="input-content\n";
    write(file,content);

    read(file);
  }

  private static void write(File file, String content) throws IOException {
    byte[] srcs = content.getBytes();
    int length = srcs.length;
    MappedByteBuffer mappedByteBuffer = new RandomAccessFile(file, "rw")
        .getChannel()
        .map(MapMode.READ_WRITE, file.length(), length);

    for (int offset = 0; offset < length; offset++) {
      mappedByteBuffer.put(srcs[offset]);
    }
  }

  private static void read(File file) throws IOException {
    long length = file.length();
    byte[] dest = new byte[(int) length];
    MappedByteBuffer mappedByteBuffer = new RandomAccessFile(file, "r")
        .getChannel()
        .map(MapMode.READ_ONLY, 0, length);

    for (int offset = 0; offset < length; offset++) {
      byte b = mappedByteBuffer.get();
      dest[offset] = b;
    }

    Scanner scanner = new Scanner(new ByteArrayInputStream(dest)).useDelimiter(" ");
    while (scanner.hasNext()) {
      LLogger.d(scanner.next() + ' ');
    }
  }
}
