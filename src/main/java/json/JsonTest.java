package json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.licola.llogger.LLogger;
import org.json.JSONObject;

/**
 * @author LiCola
 * @date 2019-03-08
 */
public class JsonTest {

  public static final String WRAPSTR = "{}";
  public static final String WRAPSTR_WRAP = "{\n"
      + "    \"key\": {}\n"
      + "}";

  public static final void main(String[] args) {
    Gson gson = new GsonBuilder()
        .create();
    WrapperBean bean = gson.fromJson(WRAPSTR_WRAP, WrapperBean.class);
    LLogger.d(bean,bean.getKey());

    JSONObject jsonObject = new JSONObject(WRAPSTR_WRAP);
    LLogger.d(jsonObject);
  }

  private static final class WrapBean {

    public String name;
  }

  private static final class WrapperBean{

    /**
     * key : {}
     */

    private KeyBean key;

    public KeyBean getKey() {
      return key;
    }

    public void setKey(KeyBean key) {
      this.key = key;
    }

    public static class KeyBean {

    }
  }

  public class EmptyCheckTypeAdapterFactory implements TypeAdapterFactory {

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
      return null;
    }
  }
}
