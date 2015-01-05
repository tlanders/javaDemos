package jse8_impatient.chapter2;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Created by tlanders.
 */
public class Exercise10 {
    public static void main(String [] args) throws Exception {
        System.out.println("Chapter2 - Exercise10");

        Stream<Double> sd = Stream.of(1.2, 2.3, 3.4, 4.5, 5.6);

        System.out.println("  stream size=" + sd.count());
    }

    /**
     * Joins using reduce operation that takes identity, accumulator, and combiner parameters.
     * In this case the identity is an empty ArrayList<T>.
     * The accumulator and combiner functions are the same for this method because the return type is the
     * same as the input parameters.
     * @param s1
     * @param <T>
     * @return
     */
    public static <T> ArrayList<T> join3(Stream<ArrayList<T>> s1) {
        return s1.reduce(new ArrayList<T>(),
                (a1, a2) -> {
                    ArrayList<T> a = new ArrayList<T>(a1);
                    a.addAll(a2);
                    return a; },
                (a1, a2) -> {
                    ArrayList<T> a = new ArrayList<T>(a1);
                    a.addAll(a2);
                    return a; }
                );
    }
}
