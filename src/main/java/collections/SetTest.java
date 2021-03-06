package collections;

import com.licola.llogger.LLogger;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

/**
 * Created by LiCola on 2018/2/28.
 */
public class SetTest {

  public static final void main(String[] args){

    LLogger.init();

//    testHashSet();
//    testTreeSet();
//    testLinkedHashSet();

  }

  private static void testLinkedHashSet() {
    LinkedHashSet<Integer> linkedHashSet=new LinkedHashSet<>();
    linkedHashSet.add(100);
    linkedHashSet.add(200);
    linkedHashSet.add(300);
    LLogger.d(linkedHashSet);
  }

  private static void testTreeSet() {
    TreeSet<Integer> treeSet=new TreeSet<>();
    treeSet.add(300);
    treeSet.add(200);
    treeSet.add(100);
    LLogger.d(treeSet);
  }

  private static void testHashSet() {
    HashSet<Integer> hashSet=new HashSet<>();
    hashSet.add(100);
    hashSet.add(200);
    hashSet.add(300);
//    hashSet.addAll(Arrays.asList(10,20));
    LLogger.d(hashSet);
  }
}
