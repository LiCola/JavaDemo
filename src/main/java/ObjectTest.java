import com.licola.llogger.LLogger;
import java.util.ArrayList;
import java.util.List;

public class ObjectTest {


  public static final void main(String[] args){
    Void v=null;
    List<Void> voids=new ArrayList<>();
    voids.add(null);

    LLogger.d(voids);
  }


}
