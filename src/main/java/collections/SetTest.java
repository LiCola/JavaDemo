package collections;

import java.nio.channels.Channel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

/**
 * Created by LiCola on 2018/2/28.
 */
public class SetTest {

  public static final void main(String[] args){
    HashMap<Integer,String> hashMap=new HashMap<>();
    hashMap.put(null,null);

    HashSet<Integer> hashSet=new HashSet<>();
    hashSet.add(100);
    hashSet.add(200);
    hashSet.add(300);
//    hashSet.addAll(Arrays.asList(10,20));
    System.out.println(hashSet);

    TreeSet<Integer> treeSet=new TreeSet<>();
    treeSet.add(300);
    treeSet.add(200);
    treeSet.add(100);
    System.out.println(treeSet);

    LinkedHashSet<Integer> linkedHashSet=new LinkedHashSet<>();
    linkedHashSet.add(100);
    linkedHashSet.add(200);
    linkedHashSet.add(300);
    System.out.println(linkedHashSet);

    
  }
}
