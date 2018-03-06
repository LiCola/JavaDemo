package advance;

/**
 * Created by LiCola on 2017/11/23.
 */
public class TestObject {
  int valueInt;
  String valueStr;

  public TestObject(int valueInt, String valueStr) {
    this.valueInt = valueInt;
    this.valueStr = valueStr;
  }

  public void changeValue(int valueInt, String valueStr){
    this.valueInt=valueInt;
    this.valueStr=valueStr;
  }
}
