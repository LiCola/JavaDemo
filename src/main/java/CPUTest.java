import com.licola.llogger.LLogger;

/**
 * @author LiCola
 * @date 2018/9/10
 */
public class CPUTest {

  static long[][] arr;

  /**
   * @param args
   */
  public static void main(String[] args) {
//    longCacheLine();
    longArrayCacheLine();
  }

  /**
   * 53879
   * 54204
   */
  private static void longCacheLine() {
    int size=16;
    long[] arrays=new long[size];
    int sum=0;
    long startTime=System.nanoTime();
    for (int i = 0; i < size; i=i+8) {
      sum+=arrays[i];
    }
    LLogger.d("user time:"+(System.nanoTime()-startTime));

  }

  /**
   *  当二维数组结构为：[1024*1024][8]
   *  横向遍历（2维先增长）：15ms
   *  纵向遍历（1维先增长）：50ms
   *  相差2倍以上，这是缓存行的作用（64位的机器缓存行64字节）,
   *  Java中的二维数组也是按照一维数组的方式组装，a[0]=b[]{}，相比一维数组，不过是每个数组槽装的是另一个数组。
   *  所以在横向遍历中，每次载入的缓存行后续还有用（因为数组头而载入的其他数据，a[0][0]~a[0][7]），而刚好后续就是访问a[0][1]
   *  而在纵向遍历中，缓存同样载入但是无效，后续另外需要载入其他数组,每次都需要缓存，而下次访问中缓存又没用。
   */
  private static void longArrayCacheLine() {
    int firstSize = 1024 * 1024;
    int secondSize = 8;
    int sum = 0;
    arr = new long[firstSize][secondSize];

    // 横向遍历
    long marked0 = System.currentTimeMillis();
    for (int i = 0; i < firstSize; i += 1) {
      for (int j = 0; j < secondSize; j++) {
        sum += arr[i][j];
      }
    }
    LLogger.d("横向:" + (System.currentTimeMillis() - marked0) + "ms");

    long marked1 = System.currentTimeMillis();
    // 纵向遍历
    for (int i = 0; i < secondSize; i += 1) {
      for (int j = 0; j < firstSize; j++) {
        sum += arr[j][i];
      }
    }
    LLogger.d("纵向:" + (System.currentTimeMillis() - marked1) + "ms");

  }
}
