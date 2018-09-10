package io;

import com.licola.llogger.LLogger;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by LiCola on 2018/6/14.
 */
public class UriTest {

  public static final void main(String[] args) throws IOException {
    URI uri = URI.create("https://juejin.im/post/5ad802faf265da50407bcc49");
    LLogger.d(uri);
    URL url = uri.toURL();
    URLConnection urlConnection = url.openConnection();
    InputStream inputStream = urlConnection.getInputStream();
    int available = inputStream.available();
    LLogger.d(available);
  }
}
