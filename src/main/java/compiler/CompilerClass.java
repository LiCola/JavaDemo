package compiler;

/**
 * Created by LiCola on 2017/11/3.
 */
public class CompilerClass<T,V> {

  private final int name = 10;
  private final T data;
  private final V vData;

  public CompilerClass(T data, V vData) {
    this.data = data;
    this.vData = vData;
  }

  //  public int foo(int a, int b) {
//    return (a + b) * (a - b);
//  }

  private void sayHello() {
    System.out.println("hello world:" + data);
    StringBuilder stringBuilder=new StringBuilder();
    stringBuilder.append("123");

    StringBuffer stringBuffer=new StringBuffer();
    stringBuffer.append("123");
  }

  public int change() {
    return 0;
  }

  public static void main(String[] args) {
    CompilerClass<String,Integer> compilerClass = new CompilerClass<>("parameter",123);
//    System.out.print(t.foo(5, 3));

    compilerClass.sayHello();
    System.out.println("name:" + compilerClass.name);
  }
}
