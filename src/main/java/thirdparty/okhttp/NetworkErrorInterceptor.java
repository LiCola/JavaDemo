package thirdparty.okhttp;

import com.licola.llogger.LLogger;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Response.Builder;
import okhttp3.internal.Util;

/**
 * Created by LiCola on 2018/6/25.
 */
public class NetworkErrorInterceptor implements Interceptor {

  @Override
  public Response intercept(Chain chain) throws IOException {
    LLogger.d("错误发生-拦截器开始");
    Request request = chain.request();
    Response response = chain.proceed(request);

    Response errorResponse = new Builder()
        .request(chain.request())
        .protocol(Protocol.HTTP_1_1)
        .code(504)
        .message("Unsatisfiable Request (only-if-cached)")
        .body(Util.EMPTY_RESPONSE)
        .build();
    LLogger.d("错误发生-拦截器结束");

    return errorResponse;
  }
}
