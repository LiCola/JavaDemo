package compiler;

/**
 * Created by 李可乐 on 2017/5/24.
 */
public class MethodArgsTest {

  public int intArg;
  public String strArg;

  @Override
  public String toString() {
    return "compiler.MethodArgsTest{" +Integer.toHexString(hashCode())+
        ":intArg=" + intArg +
        ", strArg='" + strArg + '\'' +
        '}';
  }

  public static void main(String[] args) {
    MethodArgsTest methodArgsTest = new MethodArgsTest();
    int data2 = methodArgsTest.whenToEvaluateZing(12);

    argsMethod(1,10);

    //java传值调用 先计算再传入函数
    int arg=10;
    methodArgsTest.count(arg+1);

    int dataArg=10;
    System.out.println("base arg old:"+dataArg);
    methodArgsTest.changeBaseArgs(dataArg);
    System.out.println("base call change after:"+dataArg);

    MethodArgsTest methodArg=new MethodArgsTest();
    System.out.println("reference arg old:"+methodArg);
    methodArgsTest.changeReferenceArgs(methodArg);
    System.out.println("reference call change after:"+methodArg);

    String immStrArg="empty";
    System.out.println("immutable object arg old:"+immStrArg);
    methodArgsTest.changeImmReferArg(immStrArg);
    System.out.println("immutable object call change after:"+immStrArg);


  }

  private static void argsMethod(int d1, Integer... args) {
    System.out.println(args.getClass());
  }

  int whenToEvaluateZing(int y) {
    return daysLeft(y) + daysLeft(0) + daysLeft(y + 1);
  }

  int daysLeft(int x) {
    if (x == 0) {
      return 0;
    } else {
      return x - 1;
    }
  }


  int count(int data) {
    System.out.println("call count args method data:"+ data);
    return data * 2;
  }

  //基本类型 传递拷贝 修改不会
  void changeBaseArgs(int arg){
    arg=arg+10;
  }

  void changeReferenceArgs(MethodArgsTest arg){
    arg.intArg=10;
    arg.strArg="data";
  }

  void changeImmReferArg(String arg){
    arg="data";
  }

}
