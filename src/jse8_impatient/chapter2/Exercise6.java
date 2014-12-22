package jse8_impatient.chapter2;

import java.util.stream.Stream;

/**
 * Created by tlanders.
 */
public class Exercise6 {
    public static void main(String [] args) throws Exception {
        System.out.println("Chapter2 - Exercise5");

        randomGenerator(25214903917L, 11L, (long) Math.pow(2,48), System.currentTimeMillis()).limit(10).forEach(System.out::println);
    }

    public static Stream<Long> randomGenerator(long a, long c, long m, long seed) {
        return Stream.iterate(seed, x -> (a * x + c) % m);
    }
}
