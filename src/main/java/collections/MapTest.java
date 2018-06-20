package collections;import com.licola.llogger.LLogger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by LiCola on 2017/8/9.
 */
public class MapTest {

  public static final void main(String[] args) {
    testMap();
//    testTree();
//    testSet();
//    testLinked();
//    testDelete();
  }

  private static void testDelete() {
    HashMap<String,Integer> hashMap=new HashMap<>();

    hashMap.put("10",10);
    hashMap.put("20",20);
    hashMap.put("30",30);

    Iterator<Entry<String, Integer>> iterator = hashMap.entrySet().iterator();
    while (iterator.hasNext()){
      Entry<String, Integer> entry = iterator.next();

      iterator.remove();
    }

    LLogger.d(System.currentTimeMillis());

    LLogger.d(hashMap.isEmpty());
  }

  private static abstract class EmptyBase{

  }

  private static void testLinked() {
    LinkedHashMap<String,Integer> accessMap=new LinkedHashMap<>(16,0.75f,true);

    accessMap.put("c", 100);
    accessMap.put("d", 200);
    accessMap.put("a", 500);
//    accessMap.get("c");
    accessMap.put("d", 300);
    for (Entry<String, Integer> stringIntegerEntry : accessMap.entrySet()) {
      LLogger.d("key:"+stringIntegerEntry.getKey()+" value:"+stringIntegerEntry.getValue());
    }

    LinkedHashMap<String,String> cacheMap=new LRUCache<>(3);
    cacheMap.put("a","abstract");
    cacheMap.put("b","basic");
    cacheMap.put("c","call");
    cacheMap.get("a");

    cacheMap.put("d","call");
    LLogger.d(cacheMap);
  }

  private static void testSet() {
    HashSet<String> stringSet=new HashSet<>();
    for (int i = 0; i < 10; i++) {
      stringSet.add("set"+i);
    }
    boolean contains = stringSet.contains("");
    for (String s : stringSet) {
      LLogger.d("traverse："+s);
    }
  }

  private static void testTree() {
    TreeMap<String, Integer> map;
    map= new TreeMap<>();
//     map= new TreeMap<>(collections.reverseOrder());//逆序 传入容器集合类的反向比较器
//    map = new HashMap<>();
    for (int i = 9; i >=0; i--) {
      map.put("key" + i, i);
    }

    Integer key2 = map.get("key2");
    boolean containsValue = map.containsValue(1);
    map.remove("key0");
    Entry<String, Integer> firstEntry = map.firstEntry();
    LLogger.d(firstEntry.getKey());
    for (Entry<String, Integer> integerStringEntry : map.entrySet()) {
      LLogger.d(
          "integerStringEntry:{" + integerStringEntry.getKey() + ":" + integerStringEntry.getValue()
              + "}");
    }
  }

  private static class SameHash {
    int index;

    public SameHash(int index) {
      this.index = index;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      SameHash sameHash = (SameHash) o;
      return index == sameHash.index;
    }

    @Override
    public int hashCode() {
      return 16;
    }
  }

  private static void testMap() {

    HashMap<Integer,String> hashMap=new HashMap<>();


    for (int i = 0; i < 64; i++) {
      hashMap.put(i,"time:"+i);
    }


//    HashMap<SameHash,String> map=new HashMap<>();
//    for (int i = 0; i < 128; i++) {
//      map.put(new SameHash(i),"value:"+i);
//    }
//
//    LLogger.d(map.entrySet());

//    OverHashMap<OverEquals,String> overEqualsStringHashMap=new OverHashMap<>();
//
//    OverEquals overEquals = new OverEquals(10);
//    overEqualsStringHashMap.put(overEquals,"o10");
//    overEqualsStringHashMap.put(new OverEquals(10),"o20");


  }

  public static class OverHashMap<K extends OverEquals,V> extends HashMap<K,V>{

  }

  public static class OverEquals {
    public int value;

    public OverEquals(int value) {
      this.value = value;
    }

    public boolean equals(OverEquals o) {
      LLogger.d("override method different parameter equals invoke");
      return false;
    }

    @Override
    public boolean equals(Object obj) {
      LLogger.d("over method equals invoke:"+obj);
      return true;
    }

    @Override
    public int hashCode() {
      return 10;
    }
  }
}
