package thirdparty;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.licola.llogger.LLogger;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LiCola
 * @date 2018/9/7
 */
public class GonsTest {

  static final String JSON_OBJECT_FORMAT = "{\"name\":\"123\"}";
  static final String JSON_ARRAY_FORMAT = "[{\"name\":\"123\"},{\"name\":\"321\"}]";

  public static final void main(String[] args) {
    Gson gson = new Gson();
//    Bean bean = gson.fromJson(JSON_OBJECT_FORMAT, Bean.class);
//    LLogger.d(bean.getName());


    Type type = new TypeToken<List<Bean>>() {
    }.getType();
    List<Bean> arrayJson = gson.fromJson(JSON_ARRAY_FORMAT,type);
    LLogger.d(arrayJson.getClass());
    for (Bean bean : arrayJson) {
      LLogger.d(bean);
    }


  }


  public static class Bean {

    private String name;

    public Bean() {

    }


    public Bean(String name) {
      this.name = name;
    }


    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    @Override
    public String toString() {
      return "Bean{" +
          "name='" + name + '\'' +
          '}';
    }
  }
}
