package thirdparty.okhttp;

import com.licola.llogger.LLogger;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.ConnectionPool;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * Created by LiCola on 2018/6/4.
 */
public class OkHttpTest {

  public static final void main(String[] args) {

    OkHttpClient client = makeOkHttpClient();

//    RequestBody requestBody= new FormBody.Builder()
//        .addEncoded("user_id","1")
//        .addEncoded("token","123")
//        .build();

    Request request = new Request.Builder()
//        .url("http://square.github.io/okhttp/")
//        .url("http://t.cn/R1RHiMA")//重定向地址
//        .url("http://www.google.com/")
//        .url("http://chileme.d0575.net/user/other?user_id=1")
//        .url("http://chileme.d0575.net/user/other")
        .url("https://uboxs-img.oss-cn-hangzhou.aliyuncs.com/hbc.zip")
//        .post(requestBody)
        .build();

//    try {
//      Response response = client.newCall(request).execute();
//      LLogger.d(response.toString());
//      LLogger.json(new JSONObject(response.body().string()));
//
//    } catch (IOException e) {
//      e.printStackTrace();
//    }

    client.newCall(request).enqueue(new Callback() {
      @Override
      public void onFailure(Call call, IOException e) {
        LLogger.d(e);
      }

      @Override
      public void onResponse(Call call, Response response) throws IOException {
        LLogger.d(response);
        Headers headers = response.headers();
        Set<String> names = headers.names();
        for (String name : names) {
          LLogger.d(name+":"+headers.get(name));
        }
        LLogger.d(headers);
//        LLogger.d(response.body().byteStream());
        String string = response.body().string();
        LLogger.d("网络流读取结束");
      }
    });


  }

  public static OkHttpClient makeOkHttpClient() {

    File cacheDir = new File("cache");
    if (!cacheDir.exists()) {
      cacheDir.mkdirs();
    }

    return new OkHttpClient.Builder()
        //        .addInterceptor(new MyInterceptor())
        .addInterceptor(new NetHandlerErrorInterceptor())
//        .addNetworkInterceptor(new NetworkErrorInterceptor())
        .addInterceptor(new ProgressInterceptor())
        .cache(new Cache(cacheDir, 1024))
        .build();
  }


  static ResponseBody buffer(final ResponseBody body) throws IOException {
    Buffer buffer = new Buffer();
    body.source().readAll(buffer);
    return ResponseBody.create(body.contentType(), body.contentLength(), buffer);
  }

}
