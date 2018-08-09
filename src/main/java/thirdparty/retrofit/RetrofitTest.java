package thirdparty.retrofit;

import com.licola.llogger.LLogger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.MockRetrofit.Builder;
import retrofit2.mock.NetworkBehavior;
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

  public static final class MockGitHub implements GitHub {

    private final BehaviorDelegate<GitHub> delegate;
    private final Map<String, Map<String, List<Contributor>>> ownerRepoContributors;

    public MockGitHub(BehaviorDelegate<GitHub> delegate) {
      this.delegate = delegate;
      ownerRepoContributors = new LinkedHashMap<>();

      // Seed some mock data.
      addContributor("square", "retrofit", "John Doe", 12);
      addContributor("square", "retrofit", "Bob Smith", 2);
      addContributor("square", "retrofit", "Big Bird", 40);
      addContributor("square", "picasso", "Proposition Joe", 39);
      addContributor("square", "picasso", "Keiser Soze", 152);
    }

    @Override
    public Call<List<Contributor>> contributors(String owner, String repo) {
      List<Contributor> response = Collections.emptyList();
      Map<String, List<Contributor>> repoContributors = ownerRepoContributors.get(owner);
      if (repoContributors != null) {
        List<Contributor> contributors = repoContributors.get(repo);
        if (contributors != null) {
          response = contributors;
        }
      }
      return delegate.returningResponse(response).contributors(owner, repo);
    }

    void addContributor(String owner, String repo, String name, int contributions) {
      Map<String, List<Contributor>> repoContributors = ownerRepoContributors.get(owner);
      if (repoContributors == null) {
        repoContributors = new LinkedHashMap<>();
        ownerRepoContributors.put(owner, repoContributors);
      }
      List<Contributor> contributors = repoContributors.get(repo);
      if (contributors == null) {
        contributors = new ArrayList<>();
        repoContributors.put(repo, contributors);
      }
      contributors.add(new Contributor(name, contributions));
    }
  }

  public static final void main(String[] args) throws IOException {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(API_URL)
        .client(OkHttpTest.makeOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build();

//    normal(retrofit);
    mock(retrofit);

  }

  private static void mock(Retrofit retrofit) throws IOException {
    NetworkBehavior behavior = NetworkBehavior.create();
    MockRetrofit mockRetrofit = new Builder(retrofit)
        .networkBehavior(behavior)
        .build();

    BehaviorDelegate<GitHub> delegate = mockRetrofit.create(GitHub.class);
    MockGitHub gitHub = new MockGitHub(delegate);

    // Query for some contributors for a few repositories.
    printContributors(gitHub, "square", "retrofit");
    printContributors(gitHub, "square", "picasso");

    // Using the mock-only methods, add some additional data.
    LLogger.d("Adding more mock data...\n");
    gitHub.addContributor("square", "retrofit", "Foo Bar", 61);
    gitHub.addContributor("square", "picasso", "Kit Kat", 53);

    // Reduce the delay to make the next calls complete faster.
    behavior.setDelay(500, TimeUnit.MILLISECONDS);

    // Query for the contributors again so we can see the mock data that was added.
    printContributors(gitHub, "square", "retrofit");
    printContributors(gitHub, "square", "picasso");
  }

  private static void normal(Retrofit retrofit) throws IOException {
    GitHub gitHub = retrofit.create(GitHub.class);

    Call<List<Contributor>> call = gitHub.contributors("square", "retrofit");

    List<Contributor> contributors = call.execute().body();
    for (Contributor contributor : contributors) {
      LLogger.d(contributor);
    }

//    call.enqueue(new Callback<List<Contributor>>() {
//      @Override
//      public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
//        LLogger.d(response.body());
//      }
//
//      @Override
//      public void onFailure(Call<List<Contributor>> call, Throwable t) {
//        LLogger.d(t);
//      }
//    });

  }

  private static void printContributors(GitHub gitHub, String owner, String repo)
      throws IOException {
    LLogger.d(String.format("== Contributors for %s/%s ==", owner, repo));
    Call<List<Contributor>> contributors = gitHub.contributors(owner, repo);
    for (Contributor contributor : contributors.execute().body()) {
      LLogger.d(contributor);
    }
    LLogger.d();
  }
}
