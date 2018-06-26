package thirdparty.okhttp;

import com.licola.llogger.LLogger;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Created by LiCola on 2018/6/4.
 */
public class OkHttpTest {

  public static final void main(String[] args) {

    File cacheDir = new File("cache");
    if (!cacheDir.exists()) {
      cacheDir.mkdirs();
    }

    OkHttpClient client = new OkHttpClient.Builder()
//        .addInterceptor(new MyInterceptor())
//        .addNetworkInterceptor(new MyNetworkInterceptor())
        .cache(new Cache(cacheDir, 1024))
        .build();

//    RequestBody requestBody= new FormBody.Builder()
//        .addEncoded("user_id","1")
//        .addEncoded("token","123")
//        .build();

    Request request = new Request.Builder()
//        .url("http://square.github.io/okhttp/")
//        .url("http://t.cn/R1RHiMA")//重定向地址
//        .url("http://www.google.com/")
        .url("http://chileme.d0575.net/user/other?user_id=1")
//        .url("http://chileme.d0575.net/user/other")
//        .post(requestBody)
        .build();

    try {
      Response response = client.newCall(request).execute();
      LLogger.d(response.toString());
      LLogger.d(response.body().string());

    } catch (IOException e) {
      e.printStackTrace();
    }


    Request request2 = new Builder()
//        .url("http://chileme.d0575.net/user/other?user_id=2")
        .url("http://square.github.io/okhttp/")
        .build();
    try {
      Response response = client.newCall(request2).execute();
      LLogger.d(response.toString());
      LLogger.d(response.body().string());
    } catch (IOException e) {
      e.printStackTrace();
    }


  }


  static ResponseBody buffer(final ResponseBody body) throws IOException {
    Buffer buffer = new Buffer();
    body.source().readAll(buffer);
    return ResponseBody.create(body.contentType(), body.contentLength(), buffer);
  }

}
