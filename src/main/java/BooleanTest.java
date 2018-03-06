/**
 * Created by 李可乐 on 2017/5/17.
 */
public class BooleanTest {
    public static void main(String[] args) {
        Boolean value1 = true;
        Boolean value2 = true;
        Boolean value3 = new Boolean(true);
        System.out.println(value1 == value2);//输出 true
        System.out.println(value1 == value3);//输出 false

        System.out.println(value1 == (boolean) value3);//输出 true
        System.out.println(value1 && value2);//输出 true


    }
}
