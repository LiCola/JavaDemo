package thirdparty;
import com.licola.llogger.LLogger;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by LiCola on 2018/5/4.
 */
public class EventBusTest {

  public static final void main(String[] args){
    EventBusTest eventBusTest=new EventBusTest();
    EventBus eventBus=EventBus.getDefault();

    eventBus.register(eventBusTest);

    eventBus.post("123");
  }

  @Subscribe
  public void onEvent(String event){
    LLogger.d("event received event:"+event);
  }

}
