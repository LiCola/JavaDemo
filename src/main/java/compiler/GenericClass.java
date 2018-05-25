package compiler; import com.licola.llogger.LLogger;

import java.util.List;

/**
 * Created by LiCola on 2018/3/15.
 */
public class GenericClass<V> {

  public List<String> list;

  public GenericClass(List<String> list) {
    this.list = list;
  }

  public void setList(List<V> list) {
  }

  public List<String> getList() {
    return list;
  }


  public static <E extends String> void setOther(List<E> eList){

  }

}
