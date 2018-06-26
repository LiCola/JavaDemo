package collections;

import com.licola.llogger.LLogger;

import java.util.Collection;
import java.util.PriorityQueue;

/**
 * Created by LiCola on 2017/9/5. 求前K个最大元素，数据来源不固定且不停的加入新元素 思路：末尾淘汰，永远保留当前数据源的前K个元素。只在固定K长度队列上的末尾比较能否替代
 * 代码实现：优先队列默认是最小堆，根元素永远是当前最小元素， 新元素只需要和根比较，当大于根元素时加入队列， 优先队列会自动调整堆，让最小元素为根。否则不变。
 */
public class TopK<E> {

  private PriorityQueue<E> priorityQueue;

  private int k;

  public TopK(int k) {
    this.k = k;
    this.priorityQueue = new PriorityQueue<>(k);
  }

  public void addAll(Collection<? extends E> collections) {
    for (E e : collections) {
      add(e);
    }
  }

  public void add(E e) {
    if (priorityQueue.size() < k) {//还有空间直接存入
      priorityQueue.add(e);
      return;
    }

    Comparable<? super E> head = (Comparable<? super E>) priorityQueue.peek();
    if (head.compareTo(e) > 0) {
      //小于堆的的最小值(最小堆的根，即堆内的最小元素） 如果大于新增元素 说明新增元素并不是前K个元素 直返返回
      return;
    }

    //新元素替换掉 原来的最小值 成为堆的元素之一
    priorityQueue.poll();//踢出根
    priorityQueue.add(e);//新增元素
  }

  public <T> T[] toArray(T[] a) {
    return priorityQueue.toArray(a);
  }

  public E getKth() {
    return priorityQueue.peek();
  }
}
