package classload;

/**
 * Created by LiCola on 2018/3/28.
 */
public class PassiveReferenceTest {

  public static class SuperClass {

    static {
      System.out.println("SuperClass.static initializer");
    }

    public static int value = 123;

    public static final int final_value=100;
  }


  public static class SubClass extends SuperClass {

    static {
      System.out.println("SubClass.static initializer");
    }
  }

  public static final void main(String[] args) {
    /**
     * 非主动的使用 下面的子类通过父类字段访问引用只会导致 SuperClass类的加载 而不会引起子类的加载
     */
//    System.out.println(SubClass.value);

    /**
     * 非主动的使用 下面的数组类不会导致 元素类的初始化 而是引起一个"[L..SuperClass"类的初始化
     * 类似的还有"[L..String"等，它们是由虚拟机自动生成的，直接继承于Object的子类，创建动作由字节码"newarray"触发
     * 这个"["代表一维数组 数组中有属性和方法，这些方法封装了数组的访问正确（错误访问，抛出异常）
     */
//    SubClass[] classes = new SubClass[10];
//    System.out.println(classes.getClass());

    /**
     * final静态字段 在编译阶段会存入类的常量池中（甚至传播优化出去），在编译阶段和类断开联系
     */
    System.out.println(SuperClass.final_value);
  }
}
