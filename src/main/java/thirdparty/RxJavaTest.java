package thirdparty;
import com.licola.llogger.LLogger;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by LiCola on 2018/5/19.
 */
public class RxJavaTest {

  public static final void main(String[] args) {
    Disposable disposable = Observable.just("Hello RxJava")
        .subscribe(new Consumer<String>() {
          @Override
          public void accept(String s) throws Exception {
            LLogger.d("subscribe :"+s+" Thread:"+Thread.currentThread());
          }
        });
  }
}
