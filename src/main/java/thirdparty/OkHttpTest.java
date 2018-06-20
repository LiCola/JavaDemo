package thirdparty;

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

  public static final void main(String[] args) throws IOException {

    File cacheDir = new File("cache");
    if (!cacheDir.exists()) {
      cacheDir.mkdirs();
    }

    OkHttpClient client = new OkHttpClient.Builder()
        .addInterceptor(new MyInterceptor())
        .addNetworkInterceptor(new MyNetworkInterceptor())
        .cache(new Cache(cacheDir, 1024))
        .build();

    RequestBody requestBody= new FormBody.Builder()
        .addEncoded("user_id","1")
        .addEncoded("token","123")
        .build();


    Request request = new Request.Builder()
//        .url("http://square.github.io/okhttp/")
//        .url("http://t.cn/R1RHiMA")//重定向地址
//        .url("http://www.google.com/")
        .url("http://chileme.d0575.net/user/other?user_id=1&token=123")
//        .url("http://chileme.d0575.net/user/other")
        .post(requestBody)
        .build();
//    Response response = null;
//    try {
//      response = client.newCall(request).execute();
//    } catch (IOException e) {
//      e.printStackTrace();
//      throw new IllegalArgumentException(e);
//    }
//    LLogger.d(response.toString());
//    LLogger.d(response.body().string());

    client.newCall(request).enqueue(new Callback() {
      @Override
      public void onFailure(Call call, IOException e) {
        LLogger.e(e);
      }

      @Override
      public void onResponse(Call call, Response response) throws IOException {
//        LLogger.trace();
        ResponseBody body = response.body();
        String string = body.string();
        LLogger.d(string);
      }
    });
  }

  private static class MyInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
      Request request = chain.request();
      LLogger.d(request.toString());

      boolean checkToken=false;

      String method = request.method();

      HttpUrl url = request.url();
      if ("GET".equals(method)){
        if (url.queryParameter("token")!=null){
          checkToken=true;
        }
      }else if ("POST".equals(method)){
        RequestBody requestBody = request.body();
        if (requestBody instanceof FormBody){
          int size = ((FormBody) requestBody).size();
          for (int i = 0; i < size; i++) {
            String encodedName = ((FormBody) requestBody).encodedName(i);
            if (encodedName.equals("token")){
              checkToken=true;
              break;
            }
          }
        }
      }


      Response response = chain.proceed(request);

      LLogger.d(response.toString());
      if (!"application/json; charset=UTF-8".equals(response.header("Content-Type"))) {
        return response;
      }

      ResponseBody responseBody = response.body();

      Reader reader = responseBody.charStream();
      JSONObject jsonObject;
      try {
        jsonObject = new JSONObject(new JSONTokener(reader));
      } finally {
        LLogger.d();
        reader.close();
      }
      LLogger.d(jsonObject);

      int statusCode = jsonObject.optInt("status");
//      statusCode=-100;
      if (checkToken&&statusCode==-100){

        Request newRequest;
        if ("GET".equals(method)){

          String newToken="token-value";
          //需要锁检查


          HttpUrl newUrl = url.newBuilder()
              .removeAllQueryParameters("token")
              .addQueryParameter("token",newToken)
              .build();

          newRequest= request.newBuilder()
              .url(newUrl)
              .build();
        }else {
          newRequest= request.newBuilder()
              .build();
        }

        response = chain.proceed(newRequest);//重新发起请求
      }

      Object result = jsonObject.opt("result");

      Buffer buffer = new Buffer();
      buffer.write(result.toString().getBytes());

      Response responseClone = response.newBuilder()
          .body(
              ResponseBody.create(responseBody.contentType(), responseBody.contentLength(), buffer))
          .build();

      return responseClone;
    }
  }

  static ResponseBody buffer(final ResponseBody body) throws IOException {
    Buffer buffer = new Buffer();
    body.source().readAll(buffer);
    return ResponseBody.create(body.contentType(), body.contentLength(), buffer);
  }

  private static class MyNetworkInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
      Request request = chain.request();
      Response response = chain.proceed(request);

      ResponseBody body = response.body();

      return response;
    }
  }
}
