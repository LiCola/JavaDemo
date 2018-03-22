package collections;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * Created by LiCola on 2018/3/12.
 */
public class DequeTest {

  public static final void main(String[] args){
//    userAsQueue();
//    userAsStack();
    userDeque();
  }

  private static void userDeque() {
    Deque<String> deque=new ArrayDeque<>();
    deque.addFirst("a");
    deque.addLast("c");
    deque.size();
    System.out.println(deque.size());
    Integer.toBinaryString(-1);
  }

  private static void userAsStack() {
    System.out.println("用做 先进后出的LIFO栈");
    Deque<String> stack=new ArrayDeque<>(32);

    stack.push("a");
    stack.push("b");
    stack.push("c");

    while (stack.peek()!=null){
      System.out.println(stack.poll());
    }
  }

  private static void userAsQueue() {
    System.out.println("用作 先进先出的FIFO");
    Queue<String> queue=new ArrayDeque<>();
    queue.offer("a");
    queue.offer("b");
    queue.offer("c");

    while (queue.peek()!=null){
      System.out.println(queue.poll());
    }
  }
}