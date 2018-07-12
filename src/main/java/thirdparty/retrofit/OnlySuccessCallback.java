package thirdparty.retrofit;

import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by LiCola on 2018/7/10.
 */
public class OnlySuccessCallback<T> implements Callback<T> {

  private Callback<T> wrapCallback;

  public static <T> OnlySuccessCallback<T> create(Callback<T> wrapCallback) {
    return new OnlySuccessCallback<>(wrapCallback);
  }

  OnlySuccessCallback(Callback<T> wrapCallback) {
    this.wrapCallback = wrapCallback;
  }

  @Override
  public void onResponse(Call<T> call, Response<T> response) {
    if (response.isSuccessful()) {
      wrapCallback.onResponse(call, response);
    } else {
      wrapCallback.onFailure(call, new IOException("状态码非成功标识 code >= 200 && code < 300"));
    }
  }

  @Override
  public void onFailure(Call<T> call, Throwable t) {
    wrapCallback.onFailure(call, t);
  }
}
