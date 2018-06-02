package java8_in_action.chapter3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class AppleComparator implements Comparator<Apple> {
    @Override
    public int compare(Apple o1, Apple o2) {
        return o1.getWeight().compareTo(o2.getWeight());
    }
}

public class SortFun {
    public static void main(String [] args) {
        System.out.println("SortFun starting.");

        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple("green", 121));
        apples.add(new Apple("red", 101));
        apples.add(new Apple("green", 101));
        apples.add(new Apple("pink", 112));
        apples.add(new Apple("blue", 112));
        apples.add(new Apple("yellow", 95));
        System.out.println("unsorted, apples=" + apples);

        // first sort with Comparator class
        apples.sort(new AppleComparator());
        System.out.println("sorted by comparator, apples=" + apples);

        // second reverse sort with anonymous class
        apples.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return -o1.getWeight().compareTo(o2.getWeight());
            }
        });
        System.out.println("reversed sorted by anonymous class, apples=" + apples);

        // sort with lambda
        apples.sort((a, b) -> a.getWeight().compareTo(b.getWeight()));
        System.out.println("sorted by lambda, apples=" + apples);

        // reverse sort with comparing and lambda
        apples.sort(Comparator.comparing((Apple a) -> a.getWeight()).reversed());
        System.out.println("reverse sorted by comparing and lambda, apples=" + apples);

        // sort with comparing and method reference
        apples.sort(Comparator.comparing(Apple::getWeight));
        System.out.println("sorted by comparing and method reference, apples=" + apples);

        // sort with comparing, reversed, and thenComparing using method references
        // reverses first sort order but not second
        apples.sort(Comparator.comparing(Apple::getWeight)
                .reversed()
                .thenComparing(Apple::getColor));
        System.out.println("sorted by comparing and thenComparing and method reference, apples=" + apples);

        // sort with comparing, thenComparing, and reversed using method references
        // reverses both sort orders
        apples.sort(Comparator.comparing(Apple::getWeight)
                .thenComparing(Apple::getColor)
                .reversed());
        System.out.println("sorted by comparing and thenComparing and method reference, apples=" + apples);

        System.out.println("SortFun done.");
    }
}
