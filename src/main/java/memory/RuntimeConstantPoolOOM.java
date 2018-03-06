package memory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiCola on 2017/12/15.
 */
public class RuntimeConstantPoolOOM {

  public static final void main(String[] args){
    List<String> list=new ArrayList<>();
    int i=0;
    while (true){
      list.add(String.valueOf(i++).intern());
    }
  }
}
