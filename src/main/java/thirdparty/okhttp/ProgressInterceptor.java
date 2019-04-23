package thirdparty.okhttp;

import com.licola.llogger.LLogger;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * @author LiCola
 * @date 2018/12/21
 */
public class ProgressInterceptor implements Interceptor {

  @Override
  public Response intercept(Chain chain) throws IOException {
    Request request = chain.request();
    Response originalResponse = chain.proceed(request);
    return originalResponse.newBuilder()
        .body(new ProgressResponse(originalResponse.body(), new ProgressListener() {

          int progress = 0;

          @Override
          public void update(long bytesRead, long contentLength, boolean done) {

            if (contentLength<=-1){
              return;
            }

            long step = contentLength / 100;
            int newProgress = (int) (bytesRead / step);
            if (newProgress == progress) {
              return;
            }
            progress = newProgress;
            LLogger.d(Thread.currentThread(), bytesRead, contentLength, done, progress);
          }
        }))
        .build();
  }

  interface ProgressListener {

    void update(long bytesRead, long contentLength, boolean done);
  }

  private static final class ProgressResponse extends ResponseBody {

    private final ResponseBody responseBody;
    private final ProgressListener progressListener;
    private BufferedSource bufferedSource;

    public ProgressResponse(ResponseBody responseBody,
        ProgressListener progressListener) {
      this.responseBody = responseBody;
      this.progressListener = progressListener;
    }

    @Override
    public MediaType contentType() {
      return responseBody.contentType();
    }

    @Override
    public long contentLength() {
      return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
      if (bufferedSource == null) {
        bufferedSource = Okio.buffer(source(responseBody.source()));
      }
      return bufferedSource;
    }

    private Source source(Source source) {
      return new ForwardingSource(source) {

        long totalBytesRead = 0L;

        @Override
        public long read(Buffer sink, long byteCount) throws IOException {

          long bytesRead = super.read(sink, byteCount);
          totalBytesRead += bytesRead != -1 ? bytesRead : 0;
          progressListener.update(totalBytesRead, responseBody.contentLength(), bytesRead == -1);
          return bytesRead;
        }
      };
    }
  }
}
