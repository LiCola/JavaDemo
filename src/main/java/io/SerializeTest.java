package io;

import com.licola.llogger.LLogger;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/**
 * Created by LiCola on 2018/3/1.
 */
public class SerializeTest {

  public static final void main(String[] args) throws IOException, ClassNotFoundException {
    ArrayList<TargetClass> targetClasses = new ArrayList<>();
    targetClasses.add(new TargetClass("base", 1));
    targetClasses.add(new TargetClass("child", 2));

    writeTargetList(targetClasses);
    List<TargetClass> readObjects = readTargetList();
    LLogger.d(readObjects);

  }

  public static void writeTargetList(List<TargetClass> list) throws IOException {
    ObjectOutputStream outputStream = null;
    outputStream = new ObjectOutputStream(
        new BufferedOutputStream(new FileOutputStream("./src/main/java/io/TargetObject.txt")));
    try {
      outputStream.writeInt(list.size());
      for (TargetClass targetClass : list) {
        outputStream.writeObject(targetClass);
      }
    } finally {
      outputStream.close();
    }

  }

  public static List<TargetClass> readTargetList() throws IOException, ClassNotFoundException {
    ObjectInputStream inputStream = new ObjectInputStream(
        new BufferedInputStream(new FileInputStream("./src/main/java/io/TargetObject.txt")));
    try {
      int size = inputStream.readInt();
      ArrayList<TargetClass> list = new ArrayList<>(size);
      for (int i = 0; i < size; i++) {
        list.add((TargetClass) inputStream.readObject());
      }
      return list;
    } finally {
      inputStream.close();
    }
  }
}
