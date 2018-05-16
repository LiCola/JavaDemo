package encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by LiCola on 2018/4/17.
 */
public class MD5Utils {

  public static String MD5Encode(String origin)
      throws NoSuchAlgorithmException {
    String resultString;
    resultString = origin;
    MessageDigest md = MessageDigest.getInstance("MD5");
    resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
    return resultString;
  }

  public static String byteArrayToHexString(byte[] b) {
    StringBuilder resultSb = new StringBuilder();
    for (byte aB : b) {
      resultSb.append(byteToHexString(aB));
    }
    return resultSb.toString();
  }

  private static String byteToHexString(byte b) {
    int n = b;
    if (n < 0) {
      n = 256 + n;
    }
    int d1 = n / 16;
    int d2 = n % 16;
    return hexDigits[d1] + hexDigits[d2];
  }

  private final static String[] hexDigits = {
      "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"
  };
}
