package collections;

import com.licola.llogger.LLogger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author LiCola
 * @date 2018/12/6
 */
public class ViewMap {

  public static class Json {

    String keyImage;
    String id;

    public Json(String keyImage, String id) {
      this.keyImage = keyImage;
      this.id = id;
    }
  }


  public static class ViewGroup {

    List<String> view;

    public ViewGroup() {
      this.view = new ArrayList<>();
    }

    void addView(String view) {
      this.view.add(view);
    }

    @Override
    public String toString() {
      return "ViewGroup{" +
          "view=" + view +
          '}';
    }
  }

  public static final void main(String[] args) {
    List<Json> list = new ArrayList<>();
    list.add(new Json("图1", "id1"));
    list.add(new Json("图1", "id0"));
    list.add(new Json("图2", "id1"));
    buildViews(list);
  }

  private static void buildViews(List<Json> inputs) {
    Map<String, ViewGroup> imageMap = new HashMap<>();
    Map<String, ViewGroup> idMap = new HashMap<>();
    int index=0;
    for (Json input : inputs) {
      String keyImage = input.keyImage;
      String keyId = input.id;
      String view="这个是视图"+index++;

      ViewGroup imageGroup = imageMap.get(keyImage);

      if (imageGroup == null) {
        //要把容器绘制到屏幕上
        imageGroup = new ViewGroup();
        //用keyImage 也要把图片控件加载到屏幕上
        imageMap.put(keyImage, imageGroup);
      }
      imageGroup.addView(view);

      ViewGroup idGroup = idMap.get(keyId);
      if (idGroup==null){
        idGroup=new ViewGroup();
        idMap.put(keyId,idGroup);
      }
      idGroup.addView(view);
    }

    for (Entry<String, ViewGroup> entry : imageMap.entrySet()) {
      LLogger.d(entry.getKey());
      LLogger.d(entry.getValue());
    }
  }
}
