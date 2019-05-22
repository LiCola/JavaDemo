package json;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.licola.llogger.LLogger;
import java.util.HashMap;

/**
 * @author LiCola
 * @date 2019-05-20
 */
public class GsonTest {

  public static void main(String[] args) {
    LLogger.init();

    HashMap<String, Object> map = Maps.newHashMapWithExpectedSize(3);
    map.put("name", "cola");
    map.put("age", "18");
    map.put("sex", null);
    map.put("wight", 170);

    Gson gson = new Gson();
    String jsonStr = gson.toJson(map);
    LLogger.d(jsonStr);

    Gson gsonNulls = new GsonBuilder().serializeNulls().create();
    String jsonNulls = gsonNulls.toJson(map);
    LLogger.d(jsonNulls);
  }
}
