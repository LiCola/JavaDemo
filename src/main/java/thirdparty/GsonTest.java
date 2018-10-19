package thirdparty;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.licola.llogger.LLogger;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author LiCola
 * @date 2018/9/7
 */
public class GsonTest {


  public static final void main(String[] args) {
    Gson gson = new Gson();

    Bean bean = new Bean("Str", 10);

    String toJson = gson.toJson(bean);
    LLogger.d(toJson);

    Bean bean1 = new Bean("int", 1);

    List<Bean> beans = Arrays.asList(bean, bean1);

    String toJsonArray = gson.toJson(beans);
    LLogger.d(toJsonArray);

    List list = gson.fromJson(toJsonArray, List.class);
    LLogger.d(list.get(0));


  }


  public static class Bean {

    private String name;

    private int version;

    public Bean() {
    }

    public Bean(String name, int version) {
      this.name = name;
      this.version = version;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getVersion() {
      return version;
    }

    public void setVersion(int version) {
      this.version = version;
    }

    @Override
    public String toString() {
      return "Bean{" +
          "name='" + name + '\'' +
          ", version=" + version +
          '}';
    }
  }
}
