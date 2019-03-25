package collections;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author LiCola
 * @date 2019-03-15
 */
public class StreamTest {

  public static final void main(String[] args) {
    List<String> strings = Arrays
        .asList("Hollis", "HollisChuang", "hollis", "Hello", "HelloWorld", "Hollis", "");

    Stream<String> stream = strings.stream();

    stream.filter(s -> !s.isEmpty()).forEach(s -> System.out.println(s));

  }
}
