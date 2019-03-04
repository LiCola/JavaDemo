package json;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.licola.llogger.LLogger;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * @author LiCola
 * @date 2019-03-04
 */
public class JsonEffectTest {

  public static final void main(String[] args) {
    String inputObject = readFile(new File("./src/main/java/json/config.json"));
    String inputArray = readFile(new File("./src/main/java/json/list.json"));

    int times = 100;

    formGson(inputObject, ConfigBean.class);
    formFast(inputObject, ConfigBean.class);
    testObject(inputObject, times);

    formGsonList(inputArray, ListItemBean.class);
    formFastList(inputArray, ListItemBean.class);
    testArray(inputArray, times);

  }

  private static void testArray(String input, int times) {
    int sumGson = 0;
    for (int i = 0; i < times; i++) {
      sumGson += formGsonList(input, ListItemBean.class);
    }
    LLogger.d("gson array: sun:" + sumGson + " avg:" + (sumGson * 1.0f / times));

    int sumFast = 0;
    for (int i = 0; i < times; i++) {
      sumFast += formFastList(input, ListItemBean.class);
    }
    LLogger.d("fast array: sun:" + sumFast + " avg:" + (sumFast * 1.0f / times));
  }

  private static void testObject(String input, int times) {
    int sumGson = 0;
    for (int i = 0; i < times; i++) {
      sumGson += formGson(input, ConfigBean.class);
    }
    LLogger.d("gson: sun:" + sumGson + " avg:" + (sumGson * 1.0f / times));

    int sumFast = 0;
    for (int i = 0; i < times; i++) {
      sumFast += formFast(input, ConfigBean.class);
    }
    LLogger.d("fast: sun:" + sumFast + " avg:" + (sumFast * 1.0f / times));
  }

  public static <T> long formFast(String input, Class<T> classOfT) {
    long startTime = System.currentTimeMillis();

    T object = JSON.parseObject(input, classOfT);

    long endTime = System.currentTimeMillis();
    return endTime - startTime;
  }

  public static <T> long formFastList(String input, Class<T> classOfT) {
    long startTime = System.currentTimeMillis();

    List<T> object = JSON.parseArray(input, classOfT);

    long endTime = System.currentTimeMillis();
    return endTime - startTime;
  }

  public static <T> long formGsonList(String input, Class<T> classOfT) {
    long startTime = System.currentTimeMillis();

    Gson gson = new Gson();
    TypeToken<?> typeToken = TypeToken.getParameterized(List.class, classOfT);
    TypeAdapter<?> adapter = gson.getAdapter(typeToken);
    try {
      List<T> object = (List<T>) adapter.fromJson(input);
    } catch (IOException e) {
      e.printStackTrace();
    }

    long endTime = System.currentTimeMillis();
    return endTime - startTime;
  }

  public static <T> long formGson(String input, Class<T> classOfT) {
    long startTime = System.currentTimeMillis();

    Gson gson = new Gson();
    T object = gson.fromJson(input, classOfT);

    long endTime = System.currentTimeMillis();
    return endTime - startTime;
  }


  public static String readFile(File file) {
    if (!file.exists()) {
      return null;
    }

    StringBuilder result = new StringBuilder();

    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(file));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    String line;
    try {
      while ((line = reader.readLine()) != null) {
        result.append(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return result.toString();
  }
}
