import com.google.common.collect.Lists;
import com.licola.llogger.LLogger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author LiCola
 * @date 2018/7/25
 */
public class StringHandlerTest {

  public static final void main(String[] args){

    String result=camelToUnderline("MainActivity");
    LLogger.d(result);
    List<String> sortStrings = sortStrings(Arrays.asList("app/user", "app/account", "app/user/location","app/MAIN_ATY","app/ALL"));
    LLogger.d(sortStrings);
  }

  private static String camelToUnderline(String name) {
    StringBuilder stringBuilder=new StringBuilder();

    char[] chars = name.toCharArray();
    for (int i = 0; i < chars.length; i++) {
      char aChar=chars[i];
      if (i>0&&Character.isUpperCase(aChar)){
        stringBuilder.append('_');
      }
      stringBuilder.append(Character.toUpperCase(aChar));
    }

    return stringBuilder.toString();
  }

  public static List<String> sortStrings(List<String> inputs){
    ArrayList<String> list = new ArrayList<>(inputs.size());
    inputs.sort(new Comparator<String>() {
      @Override
      public int compare(String o1, String o2) {
        return o1.compareTo(o2);
      }
    });
    return inputs;
  }
}
