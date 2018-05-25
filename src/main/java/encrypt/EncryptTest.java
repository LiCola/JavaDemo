package encrypt;

import com.licola.llogger.LLogger;
import java.security.NoSuchAlgorithmException;

/**
 * Created by LiCola on 2018/4/17.
 */
public class EncryptTest {

  public static final void main(String[] args) throws NoSuchAlgorithmException {

    String input="123";
    String md5Encode = MD5Utils.MD5Encode(input);
    LLogger.d("input:"+input+" encode:"+md5Encode);
  }


}
