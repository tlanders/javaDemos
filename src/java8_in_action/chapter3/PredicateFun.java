package java8_in_action.chapter3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateFun {
    public static void main(String[] args) {
        System.out.println("PredicateFun starting.");

        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple("green", 121));
        apples.add(new Apple("red", 101));
        apples.add(new Apple("green", 101));
        apples.add(new Apple("pink", 112));
        apples.add(new Apple("blue", 112));
        apples.add(new Apple("yellow", 95));
        System.out.println("unfiltered apples=" + apples);

        Predicate<Apple> heavyPredicate = a -> a.getWeight() > 100;
        Predicate<Apple> heavyRedPredicate = heavyPredicate.and(a -> a.getColor().equalsIgnoreCase("red"));

        Predicate<Apple> lightPredicate = heavyPredicate.negate();

        List<Apple> heavyApples = apples.stream().filter(heavyPredicate).collect(Collectors.toList());
        System.out.println("heavy apples=" + heavyApples);

        List<Apple> lightApples = apples.stream().filter(lightPredicate).collect(Collectors.toList());
        System.out.println("light apples=" + lightApples);

        List<Apple> heavyRedApples = apples.stream().filter(heavyRedPredicate).collect(Collectors.toList());
        System.out.println("heavy red apples=" + heavyRedApples);

        System.out.println("PredicateFun done.");
    }
}