package thirdparty;

import com.licola.llogger.LLogger;
import org.json.JSONObject;

/**
 * Created by LiCola on 2018/6/6.
 */
public class JsonTest {

  public static final void main(String[] args){
    JSONObject jsonObject=new JSONObject("{\"status\":0,\"msg\":\"发送成功\"}");
    jsonObject.put("result","");
    LLogger.d(jsonObject);
  }
}
