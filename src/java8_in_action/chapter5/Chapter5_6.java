package java8_in_action.chapter5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by tlanders on 4/6/2016.
 */
public class Chapter5_6 {
    public static void main(String [] args) {
        System.out.println("Chapter 5_6");

        // calculates Pythagorean triples
        List<int[]> tuples = IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                    .boxed()
                    .filter(b -> Math.sqrt(a*a + b*b) % 1.0 == 0)
                    .map(b -> new int[] {a, b, (int) Math.sqrt(a*a + b*b)}))
                .collect(Collectors.toList());

        tuples.stream().forEach(t -> System.out.println("[" + t[0] + "," + t[1] + "," + t[2] + "]"));

        System.out.println("Chapter 5_6 - method 2");
        // calculates Pythagorean triples
        List<double[]> tuples2 = IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .boxed()
                        .map(b -> new double[] {a , b, Math.sqrt(a*a + b*b)})
                        .filter(x -> x[2] % 1.0 == 0))
                .collect(Collectors.toList());

        tuples2.stream().forEach(t -> System.out.println("[" + (int) t[0] + "," + (int) t[1] + "," + (int) t[2] + "]"));

        System.out.println("Chapter 5_6 done.");
    }
}
