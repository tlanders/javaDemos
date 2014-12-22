package jse8_impatient.chapter2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * Created by tlanders.
 */
public class Exercise8 {
    public static void main(String [] args) throws Exception {
        System.out.println("Chapter2 - Exercise8");

        zip(Stream.of("a", "b", "c", "d", "e"), Stream.of("1", "2", "3", "4")).forEach(System.out::println);
    }

    public static <T> Stream<T> zip(Stream<T> s1, Stream<T> s2) {
        Iterator<T> s2iter = s2.iterator();
        return s1.flatMap(s1obj -> {
                    if(s2iter.hasNext()) {
                        return Arrays.asList(s1obj, s2iter.next()).stream();
                    } else {
                        s1.close();
                        return null;
                    }
                }
        );
    }
}
