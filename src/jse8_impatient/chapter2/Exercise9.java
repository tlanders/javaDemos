package jse8_impatient.chapter2;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by tlanders.
 */
public class Exercise9 {
    public static void main(String [] args) throws Exception {
        System.out.println("Chapter2 - Exercise9");

        ArrayList<Integer> a1 = new ArrayList<>();
        a1.add(1);
        a1.add(2);
        a1.add(3);

        ArrayList<Integer> a2 = new ArrayList<>();
        a2.add(99);
        a2.add(88);
        a2.add(77);
        a2.add(66);

        System.out.println("  a1 size=" + a1.size());
        System.out.println("  a2 size=" + a2.size());

        ArrayList<Integer> afinal = join1(Stream.of(a1, a2));

        System.out.println("Chapter2 - Exercise9, joined size=" + afinal.size());
        afinal.forEach(System.out::println);
    }

    public static <T> ArrayList<T> join1(Stream<ArrayList<T>> s1) {
        Optional<ArrayList<T>> r = s1.reduce((a1, a2) -> {
            ArrayList<T> a = new ArrayList<T>(a1);
            a.addAll(a2);
            return a;
        });
        return r.get();
    }
}
