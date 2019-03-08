package io;import com.licola.llogger.LLogger;
import com.licola.llogger.LLogger;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * Created by LiCola on 2017/9/25.
 */
public class CharStreamTest {

  public static final void main(String[] args) throws IOException {

//    File file=new File("./src/java/io/CharTestFile.txt");
//    fileOutputStreamWriter(file);
//    fileInputStreamReader(file);

//    printWrite();

    SystemScanner();

  }

  private static void SystemScanner() throws UnsupportedEncodingException, FileNotFoundException {
    System.setIn(new ByteArrayInputStream("hello".getBytes("UTF-8")));
    System.setOut(new PrintStream("./src/main/java/io/out.txt"));
    System.setErr(new PrintStream("./src/main/java/io/err.txt"));

    try{
      Scanner in = new Scanner(System.in);
      LLogger.d(in.nextLine());
      LLogger.d(in.nextLine());
    }catch(Exception e){
      LLogger.e(e);
    }
  }

  private static void printWrite() {
    PrintWriter printWriter=new PrintWriter(System.out);
    printWriter.print("PrintUtils write");
    printWriter.flush();
    printWriter.close();
  }

  private static void fileOutputStreamWriter(File file) throws IOException {
    OutputStreamWriter writer=new OutputStreamWriter(new FileOutputStream(file),"GB2312");

    String writeContent="Content!";
    try {
      writer.write(writeContent);
    } finally {
      writer.flush();
      writer.close();
    }
  }

  private static void  fileInputStreamReader(File file) throws IOException {
    InputStreamReader reader=new InputStreamReader(new FileInputStream(file));

    char[] chars = new char[1024];
    int charsRead = reader.read(chars);
    String readStr=new String(chars,0,charsRead);
    LLogger.d("readStr:"+readStr);
  }
}
