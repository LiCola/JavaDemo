package bytecode;

/**
 * @author LiCola
 * @date 2019-06-19
 */
public class TargetClass {

  private String name;
  private int age;

  public TargetClass(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public void print() {
    System.out.printf("目标类输出内容\n");
  }

  public static class InnerClass {

    public void print() {
      System.out.printf("目标内部类输出内容\n");
    }
  }
}
