import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ObjectTest {

  public HashMap<String,String> hashMap=new HashMap<>();
  public static HashMap<String,Boolean> hashMapStatic=new HashMap<>();
  private HashMap<String,Integer> integerHashMap=new HashMap<>();


  public static final void main(String[] args){
    System.out.println("name");
    List<String> stringList =new ArrayList<String>();
    stringList.add("we");

    String s = stringList.get(0);

    String string="12";
    Integer integer = Integer.valueOf(string);
    System.out.println(integer);
  }
}
