/**
 * Created by 李可乐 on 2017/5/25.
 */
public class SupperClassTest {

    public static class SupperClass {
        public String someVar="this var is supper";
    }


    public static class SubClass  extends SupperClass{
        public String someVar="this var is sub";
    }

    public static void main(String[] args){
        SupperClass supperClass1=new SupperClass();
        SupperClass supperClass2=new SubClass();

        System.out.println(supperClass1.someVar);
        System.out.println(supperClass2.someVar);
    }
}
