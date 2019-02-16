package memory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiCola on 2017/12/15.
 * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 */
public class RuntimeConstantPoolOOM {

  static class OOMObject {

  }

  public static final void main(String[] args) {
    List<OOMObject> list = new ArrayList<>();
    while (true) {
      list.add(new OOMObject());
    }
  }
}
