package collections;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by LiCola on 2017/9/5.
 */
public class QueueTest {

  public static final void main(String[] args) {
    testQueueSort();
//    testPriorityQueueTopK();
//    testArrayDeque();
  }

  private static void testArrayDeque() {


    ArrayDeque<Integer> deque = new ArrayDeque<>();
    deque.add(1);
  }

  private static void testPriorityQueueTopK() {
    TopK<Integer> topK = new TopK<>(5);

    topK.addAll(Arrays.asList(100, 1, 2, 5, 6, 7, 34, 9, 3, 4, 5, 8, 23, 21, 90, 1, 0));
    System.out.println("top :" + Arrays.toString(topK.toArray(new Integer[0])));
    System.out.println("getKth:" + topK.getKth());
  }

  private static void testQueueSort() {
    PriorityQueue<Integer> queue = new PriorityQueue<>();

    queue.offer(5);
    queue.add(4);
    queue.addAll(Arrays.asList(3, 2, 1));
    while (queue.peek() != null) {
      System.out.println("item:" + queue.poll());
    }

    queue.remove(4);
  }
}
