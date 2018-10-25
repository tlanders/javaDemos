package misc.misc.intellij;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class LambdaTest {
    public static void main(String [] args) {
        System.out.println("Lambda test starting...");

        List<String> strings = Stream.of("C", "A", "B")
                .sorted()
                .collect(toList());

        System.out.println("strings=" + strings);

        System.out.println("Lambda test done.");
    }
}
