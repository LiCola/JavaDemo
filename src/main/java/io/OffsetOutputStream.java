package io;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by LiCola on 2017/9/5.
 */
public class OffsetOutputStream extends OutputStream {

  private OutputStream outputStream=null;

  public OffsetOutputStream(OutputStream outputStream) {
    this.outputStream = outputStream;
  }

  @Override
  public void write(int b) throws IOException {
    int read=b+2;//偏移两位
    if (read>=(97+26)){
      read-=26;
    }
    outputStream.write(read);
  }
}
