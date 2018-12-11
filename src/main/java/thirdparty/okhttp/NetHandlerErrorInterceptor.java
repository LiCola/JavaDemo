package thirdparty.okhttp;

import com.licola.llogger.LLogger;
import java.io.IOException;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;

/**
 * Created by LiCola on 2018/7/10.
 */
public class NetHandlerErrorInterceptor implements Interceptor {

  @Override
  public Response intercept(Chain chain) throws IOException {
    LLogger.d("错误处理-拦截器开始");
    Request request = chain.request();

    HttpUrl httpUrl = request.url();

    HttpUrl newUrl = httpUrl.newBuilder()
        .build();


    Response response = chain.proceed(request);

    if (!response.isSuccessful()){
      throw new IOException("网络连接成功，但是返回失败");
    }

    LLogger.d("错误处理-拦截器结束");
    return response;
  }
}
