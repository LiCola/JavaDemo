package encode;

import com.licola.llogger.LLogger;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import okio.ByteString;

/**
 * Created by LiCola on 2018/4/17.
 */
public class EncodeTest {

  public static final void main(String[] args) throws NoSuchAlgorithmException {

    String input = "123";
    String md5Encode = ByteString.encodeUtf8(input).md5().hex();
    LLogger.d(input, md5Encode);

    String base64Encode = Base64.getEncoder().encodeToString(input.getBytes());
    String base64Decode = new String(Base64.getDecoder().decode(base64Encode),
        Charset.forName("UTF-8"));
    LLogger.d(input, base64Encode, base64Decode);
  }


}
