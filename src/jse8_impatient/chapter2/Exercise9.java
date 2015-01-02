package jse8_impatient.chapter2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * Created by tlanders.
 */
public class Exercise9 {
    public static void main(String [] args) throws Exception {
        System.out.println("Chapter2 - Exercise9");


    }

    public static <T> Stream<T> join(Stream<ArrayList<T>> s1) {
        return Stream.empty();
    }
}
