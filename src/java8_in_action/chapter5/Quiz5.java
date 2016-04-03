package java8_in_action.chapter5;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tlanders on 4/2/2016.
 */
public class Quiz5 {
    public static void main(String [] args) {
        System.out.println("Quiz 5");

        question1();
        question2();

        System.out.println("Quiz 5 done.");
    }

    public static void question1() {
        System.out.println("question 1");
        List<Integer> squares = Arrays.asList(1, 2, 3, 4, 5).stream().map(n -> n * n).collect(Collectors.toList());

        squares.forEach(System.out::println);
    }

    /**
     * Takes two arrays and outputs the pairs of all numbers in the two lists.
     * Example: (1,2) + (3,4) -> (1,3), (1,4), (2,3), (2,4)
     */
    public static void question2() {
        System.out.println("question 2");
        //List<Integer> squares = Arrays.asList(1, 2, 3, 4, 5).stream().map(n -> n * n).collect(Collectors.toList());

        List<Integer> first = Arrays.asList(1,2,3);
        List<Integer> second = Arrays.asList(4,5);

        List<int []> pairs = first.stream()
                .flatMap(i -> second.stream().map(j -> new int[] {i, j}))
                .collect(Collectors.toList());

        pairs.stream().forEach(pair -> System.out.println(pair[0] + ":" + pair[1]));
    }
}
