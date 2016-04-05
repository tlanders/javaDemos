package java8_in_action.chapter5;

import java.util.ArrayList;
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
        question3();

        System.out.println("Quiz 5 done.");
    }

    public static void question1() {
        System.out.println("question 1");
        List<Integer> squares = Arrays.asList(1, 2, 3, 4, 5).stream().map(n -> n * n).collect(Collectors.toList());

        squares.forEach(System.out::println);

        // starts with an IntStream instead of a Stream so have to pass more args to collect
        int [] nums = {1,3,5,7,9};
        List<Integer> sq2 = Arrays.stream(nums).map(n -> n * n)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        sq2.stream().forEach(System.out::println);
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

    /**
     * Takes two arrays and outputs the pairs that add up to a multiple of 3 in the two lists.
     * Example: (1,2) + (3,4) -> (2,4)
     */
    public static void question3() {
        System.out.println("question 3");

        List<Integer> first = Arrays.asList(1,2,3);
        List<Integer> second = Arrays.asList(4,5);

//        List<int []> pairs = first.stream()
//                .flatMap(i -> second.stream().map(j -> new int[] {i, j}))
//                .filter(pair -> (pair[0] + pair[1]) % 3 == 0)
//                .collect(Collectors.toList());
        List<int []> pairs = first.stream()
                .flatMap(i -> second.stream().filter(j -> (i+j) % 3 == 0).map(j -> new int[] {i, j}))
                .collect(Collectors.toList());

        pairs.stream().forEach(pair -> System.out.println(pair[0] + ":" + pair[1]));
    }
}
