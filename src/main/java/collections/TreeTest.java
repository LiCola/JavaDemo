package collections;

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
      System.out.println("key:"+entry.getKey()+" value:"+entry.getValue());
    }

    System.out.println(treeMap.firstEntry());
    System.out.println(treeMap.lastEntry());

    System.out.println(treeMap.floorEntry("d"));
    System.out.println(treeMap.ceilingEntry("d"));


  }

}
