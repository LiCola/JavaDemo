package thread;

import com.licola.llogger.LLogger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author LiCola
 * @date 2019/1/4
 */
public class ThreadNoSafeTest {

  public static final void main(String[] args){

    simpleDateFormat();
  }

  private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

  /**
   * 多个线程共享的SimpleDateFormat对象，内部解析数据时，发生不期望的局部数据共享，导致解析数据异常
   */
  private static void simpleDateFormat() {
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    for (int i = 0; i < 100; i++) {
      Runnable task = new Runnable() {
        @Override
        public void run() {
          String format = "2019-01-04T11:28:08";
//          String format = simpleDateFormat.format(new Date());
          try {
            Date date = simpleDateFormat.parse(format);
            LLogger.d(date);
          } catch (ParseException e) {
            e.printStackTrace();
          }
        }
      };
      executorService.submit(task);
    }
  }
}
