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

        ArrayList<Integer> a3 = new ArrayList<>();
        a3.add(-2);
        a3.add(-4);
        a3.add(-6);
        a3.add(-8);

        ArrayList<Integer> a4 = new ArrayList<>();
        a4.add(5);
        a4.add(7);
        a4.add(11);

        System.out.println("  a1 size=" + a1.size());
        System.out.println("  a2 size=" + a2.size());

        // call join1 which takes binary function form of reduce
        ArrayList<Integer> afinal = join1(Stream.of(a1, a2, a3, a4));

        System.out.println("Chapter2 - Exercise9, joined size=" + afinal.size());
        afinal.forEach(System.out::println);

        // call join1 with an empty stream
        afinal = join1(Stream.empty());

        System.out.println("Chapter2 - Exercise9, empty joined size=" + afinal.size());
        afinal.forEach(System.out::println);

        // call join2 which takes identity form of reduce
        ArrayList<Integer> afinal2 = join2(Stream.of(a1, a2, a3, a4));

        System.out.println("Chapter2 - Exercise9, joined #2 size=" + afinal2.size());
        afinal2.forEach(System.out::println);

        // try join2 with an empty stream. in this case the identity value should be returned.
        ArrayList<Integer> afinal3 = join2(Stream.empty());

        System.out.println("Chapter2 - Exercise9, empty joined #2 size=" + afinal3.size());
        afinal3.forEach(System.out::println);

        // call join3 which takes identity form of reduce
        ArrayList<Integer> afinal4 = join3(Stream.of(a1, a2, a3, a4));

        System.out.println("Chapter2 - Exercise9, joined #3 size=" + afinal4.size());
        afinal4.forEach(System.out::println);

        // try join3 with an empty stream. in this case the identity value should be returned.
        afinal4 = join3(Stream.empty());

        System.out.println("Chapter2 - Exercise9, empty joined #3 size=" + afinal4.size());
        afinal4.forEach(System.out::println);
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


    /**
     * Joins using reduce operation that takes an identity parameter.
     * In this case the identity is an empty ArrayList<T>.
     * @param s1
     * @param <T>
     * @return
     */
    public static <T> ArrayList<T> join2(Stream<ArrayList<T>> s1) {
        return s1.reduce(new ArrayList<T>(), (a1, a2) -> {
            ArrayList<T> a = new ArrayList<T>(a1);
            a.addAll(a2);
            return a;
        });
    }

    /**
     * Joins using binary function version of reduce.
     * @param s1
     * @param <T>
     * @return
     */
    public static <T> ArrayList<T> join1(Stream<ArrayList<T>> s1) {
        return s1.reduce((a1, a2) -> {
            ArrayList<T> a = new ArrayList<T>(a1);
            a.addAll(a2);
            return a;
        }).orElse(new ArrayList<T>());
    }
}
