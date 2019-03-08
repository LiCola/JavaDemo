package demo;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import org.openjdk.jmh.annotations.Benchmark;

/**
 * 运行结论：LongAdder有着10倍的效率高于Atomic 而atomic只synchronized高于2倍
 *
 * @author LiCola
 * @date 2019-03-08
 */
public class IntegerAddTest {

  private static AtomicLong atomicLong = new AtomicLong();
  private static LongAdder longAdder = new LongAdder();
  private static SynchronizeAdder synchronizeAdder = new SynchronizeAdder();

  @Benchmark()
  public void testAtomicLong() {
    atomicLong.getAndIncrement();
  }

  @Benchmark()
  public void testLongAdder() {
    longAdder.increment();
  }

  @Benchmark()
  public void testSynAdder() {
    synchronizeAdder.increment();
  }

  public static class SynchronizeAdder {

    volatile long value = 0;

    public void increment() {
      synchronized (this) {
        value++;
      }
    }
  }

}
