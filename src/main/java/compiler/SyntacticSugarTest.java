package compiler;

/**
 * Created by LiCola on 2017/12/25.
 */
public class SyntacticSugarTest {

  public static final void main(String[] args) {
    SyntacticSugarTest test = new SyntacticSugarTest();
    test.addFirst();
    test.addLast();
    test.ifOnly('1');
    test.ifelse('2');

    test.fori(10000);

    while (true){

    }
  }

  public CharSequence fori(int times) {
    StringBuilder result = new StringBuilder("123");
    long start = System.currentTimeMillis();
    for (int i = 0; i < times; i++) {
      result.append(i);
    }
    System.out.println("user time:" + (System.currentTimeMillis() - start));
    return result.toString();
  }

  public int addFirst() {
    int b = 0;
    return ++b;
  }

  public int addLast() {
    int a = 0;
    return a++;
  }

  public int ifOnly(int b) {
    if (b > 10) {
      return 1000;
    }
    return 1;
  }

  public int ifelse(int b) {
    if (b > 10) {
      return 1000;
    } else {
      return 1;
    }
  }
}
