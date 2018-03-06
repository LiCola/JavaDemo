/**
 * Created by 李可乐 on 2017/5/25.
 */
public class ExceptionTest {

    public String find(String input) throws IllegalAccessException {
        if (input != null) {
            System.out.println("find this");
            return "this";
        } else {
            throw new IllegalAccessException("can't find this");
        }
    }

    public static void main(String[] args) {
        ExceptionTest exceptionTest = new ExceptionTest();
        long timeBegin = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            try {
                exceptionTest.find(null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        long timeEnd = System.currentTimeMillis();
        System.out.println("time user = " + (timeEnd - timeBegin));
    }
}
