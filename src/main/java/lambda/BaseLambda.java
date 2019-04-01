package lambda;

import com.licola.llogger.LLogger;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author LiCola
 * @date 2018/12/11
 */
public class BaseLambda {

  public static final void main(String[] args) {
    File[] files = new File(".").listFiles(new FileFilter() {
      @Override
      public boolean accept(File pathname) {
        return pathname.isHidden();
      }
    });
    LLogger.d((Object[]) files);

    //java8的方法引用::语法，把这个方法作为值 传递给方法
    File[] hiddenFiles = new File(".").listFiles(File::isHidden);

    LLogger.d((Object[]) hiddenFiles);

    List<File> filter = filter(new File(".").listFiles(), BaseLambda::isHidden);
    List<File> filter2 = filter(new File(".").listFiles(), (File file) -> file.isHidden());
    LLogger.d(filter, filter2);
  }

  public static boolean isHidden(File file) {
    return file.isHidden();
  }

  public static List<File> filter(File[] files, Predicate<File> predicate) {
    List<File> result = new ArrayList<>();
    for (File file : files) {
      if (predicate.test(file)) {
        result.add(file);
      }
    }
    return result;
  }
}
