package collections;import com.licola.llogger.LLogger;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * Created by LiCola on 2017/9/4.
 * 继承LinkedHashMap实现LruCache。
 * 只需要确定大小，然后重写删除最旧节点的返回值
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {

  private int max;

  public LRUCache(int max) {
    super(16, 0.75f, true);
    this.max = max;
  }

  @Override
  protected boolean removeEldestEntry(Entry<K, V> eldest) {
    return size() > max;
  }

  public static final void main(String[] args){
    LinkedHashMap<String,String> cacheMap=new LRUCache<>(3);
    cacheMap.put("a","abstract");
    cacheMap.put("b","basic");
    cacheMap.put("c","call");
    cacheMap.get("a");

    cacheMap.put("d","call");
    LLogger.d(cacheMap);
  }
}
