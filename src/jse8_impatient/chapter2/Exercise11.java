package jse8_impatient.chapter2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

/**
 * Created by tlanders.
 */
public class Exercise11 {
    public static void main(String [] args) throws Exception {
        System.out.println("Chapter2 - Exercise11");

        Stream<Double> sd1 = Stream.of(9.0, 1.2, 2.3, 3.4, 4.5, -2.0);
        Stream<Double> sd2 = Stream.of(4.03, 2.96, 5.61);

        int capacity = 20;
        ArrayList<Double> doubleArrayList = join(sd1, sd2, capacity);

        System.out.println("  result size=" + doubleArrayList.size());
        doubleArrayList.forEach(System.out::println);
    }

    public static <T> ArrayList<T> join(Stream<T> s1, Stream<T> s2, int capacity) {
        ArrayList<T> result = new ArrayList<>(Collections.nCopies(capacity, null));
        AtomicInteger indexCounter = new AtomicInteger(0);

        new Thread(() -> {
            s1.forEach(val -> {
                int nextIndex = indexCounter.getAndIncrement();
                result.set(nextIndex, val);
            });
        }).start();
        new Thread(() -> {
            s2.forEach(val -> {
                int nextIndex = indexCounter.getAndIncrement();
                result.set(nextIndex, val);
            });
        }).start();

        try {
            Thread.sleep(2000);
        } catch(Exception e) {

        }

        return result;
    }
}
