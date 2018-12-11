package thirdparty;

import com.licola.llogger.LLogger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by LiCola on 2018/6/6.
 */
public class JsonTest {

  public static final void main(String[] args){
//    JSONObject jsonObject=new JSONObject("{\"status\":0,\"msg\":\"发送成功\"}");
//    jsonObject.put("result","");
//    LLogger.d(jsonObject);

    JSONArray jsonArray = new JSONArray();
    int scale=10;
    int size=100;
    for (int i = 0; i < size; i++) {
      JSONObject object = new JSONObject();
      object.put("time",(float) i/10);
      object.put("pic",Integer.valueOf(String.format("%03d",i)));
      jsonArray.put(object);
    }

    LLogger.d("\n"+jsonArray);

  }
}
