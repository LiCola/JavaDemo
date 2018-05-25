import com.licola.llogger.LLogger;

/**
 * Created by LiCola on 2018/4/13.
 */
public class SyntacticSugarTest {

  public static final void main(String[] args) {

    String target = "accountImpl";
    String suffix = "Impl";
    int suffixLength = suffix.length();
    int length = target.length();
    String targetSuffix = target.substring(length - suffixLength);
    String targetName=target.substring(0,length - suffixLength);
    LLogger.d("targetSuffix:"+targetSuffix+" targetName:"+targetName);
  }
}
