package demo;

import org.openjdk.jmh.annotations.Benchmark;

/**
 * @author LiCola
 * @date 2019-03-05
 */
public class LockRunTest {

  private void print(String a) {
  }

  @Benchmark
  public void testStringAdd() {
    String a = "";
    for (int i = 0; i < 10; i++) {
      a += i;
    }
    print(a);

  }

  @Benchmark
  public void testStringBufferAdd() {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < 10; i++) {
      sb.append(i);
    }
    print(sb.toString());
  }

  @Benchmark
  public void testStringBuilderAdd() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 10; i++) {
      sb.append(i);
    }
    print(sb.toString());
  }


}
