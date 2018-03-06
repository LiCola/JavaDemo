import java.io.IOException;
import java.io.Serializable;

/**
 * Created by LiCola on 2017/6/7.
 */
public class FileTest {
  public static void main(String[] args) throws IOException {
    DataBean dataBean=new DataBean();
    System.out.println("hash = "+dataBean.hashCode());
  }

  public static class DataBean implements Serializable{
    public String name;

    private static final long serialVersionUID = 876323262645176354L;

    @Override
    public int hashCode() {
      return super.hashCode();
    }
  }
}
