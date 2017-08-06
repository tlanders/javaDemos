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

public class Sort {
    public static void main(String [] args) {
        System.out.println("Sort starting.");

        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple("red", 101));
        apples.add(new Apple("pink", 112));
        apples.add(new Apple("yellow", 95));
        System.out.println("unsorted, apples=" + apples);

        apples.sort(new AppleComparator());
        System.out.println("sorted, apples=" + apples);

        System.out.println("Sort done.");
    }
}
