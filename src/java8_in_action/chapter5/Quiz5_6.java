package java8_in_action.chapter5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by tlanders on 4/6/2016.
 */
public class Quiz5_6 {
    public static void main(String [] args) {
        System.out.println("Quiz 5_6");

        int a = 6;

        List<int[]> tuples = IntStream.rangeClosed(1, 100)
                .boxed()
                .filter(b -> Math.sqrt(a*a + b*b) % 1.0 == 0)
                .map(b -> new int[] {a, b, (int) Math.sqrt(a*a + b*b)})
                .collect(Collectors.toList());

        tuples.stream().forEach(t -> System.out.println("[" + t[0] + "," + t[1] + "," + t[2] + "]"));

        System.out.println("Quiz 5_6 done.");
    }
}
