package io;import com.licola.llogger.LLogger;
import com.licola.llogger.LLogger;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by LiCola on 2017/9/5. 关于字节流的操作测试
 */
public class ByteStreamTest {

  public static final void main(String[] args) throws IOException, ClassNotFoundException {

    File file = new File("./src/main/java/io/ByteTestFile.txt");
    fileBufferOutStream(file);
//    fileInputStream(file);
//    fileBufferInputStream(file);
    fileByteArrayInputStream(file);
  }

  private static void fileBufferOutStream(File file) throws IOException {
    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
        new FileOutputStream(file));

    String writeContent = "hello";
    byte[] bytes = writeContent.getBytes("UTF-8");

    try {
      LLogger.d("write:" + writeContent);
      bufferedOutputStream.write(bytes);
    } finally {
      bufferedOutputStream.flush();
      bufferedOutputStream.close();
    }


  }

  private static void fileByteArrayOutputStream(File file) {

  }

  /**
   * 直接从文件得到输入流 按字节读取 得到String字符串输出
   */
  private static void fileInputStream(File file) throws IOException {
    FileInputStream inputStream = new FileInputStream(file);//得到file 输入流
    byte[] bytes = new byte[inputStream.available()];//初始化 流可用长度的字节数组
    int readByte;//保存读取字节 的临时变量
    int bytesRead = 0;//读取字节的总数
    while ((readByte = inputStream.read()) != -1) {
      //从输入流中读取字节 赋值给 临时变量 判断是否读取结束
      bytes[bytesRead++] = (byte) readByte;//赋值到字节数字的每位
    }

    String resultStr = new String(bytes, 0, bytesRead, "UTF-8");//使用读取得到的字节数组 构造字符串
    LLogger.d("Read Result:" + bytesRead + " resultStr:" + resultStr);
  }

  /**
   * 使用buffer缓存文件输入流 一次得到全部的输入流
   */
  private static void fileBufferInputStream(File file) throws IOException {
    BufferedInputStream bufferedInputStream = new BufferedInputStream(
        new FileInputStream(file));//通过文件输入流 得到缓存输入流

    byte[] bytes = new byte[bufferedInputStream.available()];//构造字节数组
    int bytesRead = bufferedInputStream.read(bytes);//一次性 读取到字节数组中 其实就是直接从文件流中读到内存中

    String resultStr = new String(bytes, 0, bytesRead, "UTF-8");
    LLogger.d("Read Result:" + bytesRead + " resultStr:" + resultStr);
  }


  private static void fileByteArrayInputStream(File file) throws IOException {
    FileInputStream fileInputStream = new FileInputStream(file);//文件输入流
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();//动态字节数组 输出流
    byte[] buf = new byte[1024];//字节数组
    int bytesRead = 0;
    while ((bytesRead = fileInputStream.read(buf)) != -1) {
      //从文件输入流中 读取数据 得到可读的字节数 并把流输入到字节数组中
      byteArrayOutputStream.write(buf, 0, bytesRead);//向动态字节数据 中 写入临时的字节数组
    }
    String resultStr = byteArrayOutputStream.toString("UTF-8");//最后从动态字节数组中 使用全部读取到的数据 构造结果
    LLogger.d("resultStr:" + resultStr);

  }

}
