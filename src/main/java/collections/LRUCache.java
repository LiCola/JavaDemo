package collections;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * Created by LiCola on 2017/9/4.
 */
public class LRUCache<K,V> extends LinkedHashMap<K,V>{
  private int maxEntries;

  public LRUCache( int maxEntries) {
    super(16,0.75f,true);
    this.maxEntries = maxEntries;
  }

  @Override
  protected boolean removeEldestEntry(Entry<K, V> eldest) {
    return size()>maxEntries;
  }
}
