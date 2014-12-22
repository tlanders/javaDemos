package jse8_impatient.chapter2;

import java.util.stream.Stream;

/**
 * Created by tlanders.
 */
public class Exercise8 {
    public static void main(String [] args) throws Exception {
        System.out.println("Chapter2 - Exercise7");

        boolean result = isFinite(Stream.of(1, 2, 3, 4, 5));
        System.out.println("Chapter2 - Exercise7, isFinite=" + result);

    }

    /**
     * This method is a bad idea because you don't know that a stream is finite until it ends. An infinite
     * stream will cause this method to never exit.
     * @param stream
     * @param <T>
     * @return
     */
    public static <T> boolean isFinite(Stream<T> stream) {
        stream.count();
        return true;
    }
}
