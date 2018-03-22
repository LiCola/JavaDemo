package collections;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.TreeMap;

/**
 * Created by LiCola on 2017/8/9.
 */
public class MapTest {

  public static final void main(String[] args) {
//    testMap();
//    testTree();
//    testSet();
    testLinked();
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
      System.out.println("key:"+stringIntegerEntry.getKey()+" value:"+stringIntegerEntry.getValue());
    }

    LinkedHashMap<String,String> cacheMap=new LRUCache<>(3);
    cacheMap.put("a","abstract");
    cacheMap.put("b","basic");
    cacheMap.put("c","call");
    cacheMap.get("a");

    cacheMap.put("d","call");
    System.out.println(cacheMap);
  }

  private static void testSet() {
    HashSet<String> stringSet=new HashSet<>();
    for (int i = 0; i < 10; i++) {
      stringSet.add("set"+i);
    }
    boolean contains = stringSet.contains("");
    for (String s : stringSet) {
      System.out.println("traverse："+s);
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
    System.out.println(firstEntry.getKey());
    for (Entry<String, Integer> integerStringEntry : map.entrySet()) {
      System.out.println(
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
//    HashMap<SameHash,String> map=new HashMap<>();
//    for (int i = 0; i < 128; i++) {
//      map.put(new SameHash(i),"value:"+i);
//    }
//
//    System.out.println(map.entrySet());

    OverHashMap<OverEquals,String> overEqualsStringHashMap=new OverHashMap<>();

    OverEquals overEquals = new OverEquals(10);
    overEqualsStringHashMap.put(overEquals,"o10");
    overEqualsStringHashMap.put(new OverEquals(10),"o20");


  }

  public static class OverHashMap<K extends OverEquals,V> extends HashMap<K,V>{

  }

  public static class OverEquals {
    public int value;

    public OverEquals(int value) {
      this.value = value;
    }

    public boolean equals(OverEquals o) {
      System.out.println("override method different parameter equals invoke");
      return false;
    }

    @Override
    public boolean equals(Object obj) {
      System.out.println("over method equals invoke:"+obj);
      return true;
    }

    @Override
    public int hashCode() {
      return 10;
    }
  }
}
