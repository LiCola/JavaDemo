package time;

import com.licola.llogger.LLogger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



/**
 * Created by LiCola on 2018/5/21.
 */
public class TimeTest {

  private static final long HOUR_TIME = 60 * 60 * 1000;


  public static final void main(String[] args) throws ParseException {
    long time = System.currentTimeMillis();

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    String timeFormat = simpleDateFormat.format(time);
    LLogger.d("time:"+time+" timeFormat:"+timeFormat);

    Date parseDate = simpleDateFormat.parse(timeFormat);

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(parseDate);
    long timeInMillis = calendar.getTimeInMillis();
    LLogger.d("date:" + parseDate.getTime() + " timeInMillis:" + timeInMillis/1000);

    Date date=new Date(System.currentTimeMillis());


    long timeOffset=time/HOUR_TIME*HOUR_TIME;

    LLogger.d(timeOffset);

  }
}
