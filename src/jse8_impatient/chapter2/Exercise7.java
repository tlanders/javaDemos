package jse8_impatient.chapter2;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by tlanders.
 */
public class Exercise7 {
    public static void main(String [] args) throws Exception {
        System.out.println("Chapter2 - Exercise7");

        boolean result = isFinite(Stream.of(1, 2, 3, 4, 5));
        System.out.println("Chapter2 - Exercise7, isFinite=" + result);

    }

    public static <T> boolean isFinite(Stream<T> stream) {
        stream.count();
        return true;
    }
}
