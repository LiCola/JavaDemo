import com.licola.llogger.LLogger;

/**
 * Created by LiCola on 2018/7/2.
 */
public class CharTest {

  public static final void main(String[] args){
    String input="java";
    char[] chars = input.toCharArray();
    boolean javaIdentifierStart = Character.isJavaIdentifierStart(chars[0]);
    LLogger.d(javaIdentifierStart);
  }

}
