package demo;

import org.openjdk.jmh.annotations.Benchmark;

/**
 * @author LiCola
 * @date 2019-03-18
 */
public class EqualTest {


  @Benchmark()
  public void testInteger() {
    new Integer(Integer.MAX_VALUE).equals(Integer.MAX_VALUE);
  }

  @Benchmark()
  public void testString() {
    new String("0x7fffffff").equals("0x7fffffff");
  }
}
