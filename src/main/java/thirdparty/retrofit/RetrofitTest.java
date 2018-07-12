package thirdparty.retrofit;

import com.licola.llogger.LLogger;
import java.io.IOException;
import java.util.List;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import thirdparty.okhttp.OkHttpTest;

/**
 * Created by LiCola on 2018/7/10.
 */
public class RetrofitTest {

  public static final String API_URL = "https://api.github.com";


  public static class Contributor {
    public final String login;
    public final int contributions;

    public Contributor(String login, int contributions) {
      this.login = login;
      this.contributions = contributions;
    }

    @Override
    public String toString() {
      return "Contributor{" +
          "login='" + login + '\'' +
          ", contributions=" + contributions +
          '}';
    }
  }

  public interface GitHub {
    @GET("/repos/{owner}/{repo}/contributors")
    Call<List<Contributor>> contributors(
        @Path("owner") String owner,
        @Path("repo") String repo);
  }

  public static final void main(String[] args) throws IOException {
    Retrofit retrofit=new Retrofit.Builder()
        .baseUrl(API_URL)
        .client(OkHttpTest.makeOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    GitHub gitHub=retrofit.create(GitHub.class);

    Call<List<Contributor>> call = gitHub.contributors("square", "retrofit");

    call.enqueue(new Callback<List<Contributor>>() {
      @Override
      public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
        LLogger.d(response.body());
      }

      @Override
      public void onFailure(Call<List<Contributor>> call, Throwable t) {
        LLogger.d(t);
      }
    });

//    List<Contributor> contributors = call.execute().body();
//    for (Contributor contributor : contributors) {
//      LLogger.d(contributor.login,contributor.contributions);
//    }
  }
}
