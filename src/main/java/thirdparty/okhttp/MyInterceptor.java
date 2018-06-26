package thirdparty.okhttp;

import com.licola.llogger.LLogger;
import java.io.IOException;
import java.io.Reader;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Created by LiCola on 2018/6/25.
 */
public class MyInterceptor implements Interceptor {
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
