package collections;import com.licola.llogger.LLogger;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * Created by LiCola on 2018/2/7.
 */
public class TreeTest {

  public static final void main(String[] args){
    TreeMap<String,String> treeMap=new TreeMap<>();
    treeMap.put("t","tree");

    treeMap.put("c","call");
    treeMap.put("b","basic");

    treeMap.put("a","abstract");

    for (Entry<String, String> entry : treeMap.entrySet()) {
      LLogger.d("key:"+entry.getKey()+" value:"+entry.getValue());
    }

    LLogger.d(treeMap.firstEntry());
    LLogger.d(treeMap.lastEntry());

    LLogger.d(treeMap.floorEntry("d"));
    LLogger.d(treeMap.ceilingEntry("d"));


  }

}
