package jse8_impatient.chapter2;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by tlanders.
 */
public class Exercise6 {
    public static void main(String [] args) throws Exception {
        System.out.println("Chapter2 - Exercise6");

        characterStream("blah chapter 2 exercise 6").forEach(System.out::println);
    }

    public static Stream<Character> characterStream(String s) {
        return IntStream.range(0, s.length()).mapToObj(s::charAt);
    }
}
