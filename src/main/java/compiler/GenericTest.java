package compiler; import com.licola.llogger.LLogger;

import java.util.Arrays;

/**
 * Created by LiCola on 2017/6/5.
 */
public class GenericTest {

  public static void main(String[] args) {
    Pair<String,Integer> pair=new Pair<>("普通泛型的定义",1);
    String first = pair.getFirst();
    LLogger.d("pair first:"+first);

    @SuppressWarnings("unchecked")
    Pair<Object,String>[] pairs=(Pair<Object, String>[]) new Pair[3];//不能直接创建泛型数组 只能先创建无泛型的数组 然后强制类型转换

    NumberPair<Integer,Double> numberPair= new NumberPair<>(1, 1.5);
    double sum = numberPair.sum();
    LLogger.d("NumberPair sum:"+sum);

    DynamicArray<Number> numbers=new DynamicArray<>();
    DynamicArray<Integer> ints=new DynamicArray<>();

    ints.add(100);
    numbers.addAll(ints);

    DynamicArray<? extends Number> numbersWild=ints;
    Integer a=200;
//    numbersWild.add(a);//无法编译 因为通配符无法保证类型安全
//    numbersWild.add((Number)a);//无法编译

    ints.copyTo(numbers);

    DynamicArray<Child> childs=new DynamicArray<>();
    childs.add(new Child(20));
    childs.add(new Child(30));
    Child maxChild=max(childs);
    LLogger.d(maxChild);

    Object array=new Integer[]{};
    LLogger.d(array.getClass());
  }

  /**
   * 典型的泛型定义，类型参数化，类中的数据定义不再固定，而是作为参数传入
   * 基本原理就是类型擦除，javac编译后U,V都不存在而是直接使用Object替代，然后由编译器加上强制类型转换
   * @param <U>
   * @param <V>
   */
  public static class Pair<U,V>{
    U first;
    V second;

    public Pair(U first, V second) {
      this.first = first;
      this.second = second;
    }

    public U getFirst() {
      return first;
    }

    public V getSecond() {
      return second;
    }
  }

  public static class DynamicArray<E> {
//    private E[] elementData;//泛型不能 直接new创建
    private Object[] elementData;//如果需要在内部初始化动态类型的数组 只能使用Object[]数组替代

    private static final int DEFAULT_CAPACITY=10;

    private int size;

    public DynamicArray() {
      this.elementData = new Object[DEFAULT_CAPACITY];
    }

    private void ensureCapacity(int minCapacity){
      int oldCapacity=elementData.length;
      if (oldCapacity>=minCapacity){
        return;
      }

      int newCapacity=oldCapacity<<1;
      if (newCapacity<minCapacity){
        newCapacity=minCapacity;
      }
      elementData= Arrays.copyOf(elementData,newCapacity);
    }

    public void add(E e){
      ensureCapacity(size+1);
      elementData[size++]=e;
    }

    public E get(int index){
      return (E) elementData[index];
    }

    public E set(int index,E element){
      E oldValue=get(index);
      elementData[index]=element;
      return oldValue;
    }

    /**
     * 上界为其他类型参数 关联入参的T类型和E
     * @param array
     * @param <T>
     */
    public <T extends E> void addAll(DynamicArray<T> array) {

    }

    public void addAllWild(DynamicArray<? extends E> array){
        for(int i=0;i<array.size;i++){
          add(array.get(i));
        }
    }

    /**
     * 通配符？表示E的某个父类型
     * @param des
     */
    public void copyTo(DynamicArray<? super E> des){
      for(int i=0;i<size;i++){
        des.add(get(i));
      }
    }

    public static <T> void swapInternal(DynamicArray<T> array,int i,int j){
      T temp = array.get(i);
      array.set(i,array.get(j));
      array.set(j,temp);
    }

    public static void swap(DynamicArray<?> array,int i,int j){
      swapInternal(array,i,j);
    }
  }

  /**
   * 指定类的泛型上界为某个类，编译后不再是擦除为Object，而使用边界类型替代
   * @param <U>
   * @param <V>
   */
  public static class NumberPair<U extends Number,V extends Number> extends Pair<U,V>{

    public NumberPair(U first, V second) {
      super(first, second);
    }

    public double sum(){
      return getFirst().doubleValue()+getSecond().doubleValue();
    }
  }


  /**
   * 上界为某个接口
   * 这里特殊的是接口也是有泛型，就需要递归类型限制
   * @param args
   * @param <T>
   * @return
   */
  public static <T extends Comparable<? super T>> T max(DynamicArray<T> args){
    T max=args.get(0);
    for (int i=0;i<args.size;i++) {
      T temp = args.get(i);
      if (temp.compareTo(max) > 0) {
        max = temp;
      }
    }
    return max;
  }

  public static class Base implements Comparable<Base>{
    private int order;

    public Base(int order) {
      this.order = order;
    }

    @Override
    public int compareTo(Base o) {
      if (order<o.order){
        return -1;
      }else if (order>o.order){
        return 1;
      }else{
        return 0;
      }
    }
  }

  public static class Child extends Base implements Comparable<Base> {

    public Child(int order) {
      super(order);
    }

    /**
     * Child不能继续实现Comparable，只能重写父类方法，判断类型
     * @param o
     * @return
     */
    @Override
    public int compareTo(Base o) {
      return super.compareTo(o);
    }
  }
}
