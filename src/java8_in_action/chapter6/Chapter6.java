package java8_in_action.chapter6;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Chapter6 {
    protected static final List<Dish> menu;

    static {
        menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));
    }

    public static void main(String ... args) {
        System.out.println("chapter 6 main starting...");

        menu.stream()
            .collect(maxBy(Comparator.comparing(Dish::getCalories)))
            .ifPresent(d -> System.out.println("max cal dish=" + d));

        int totalCalories = menu.stream()
                .collect(summingInt(Dish::getCalories));

        System.out.println("total cals=" + totalCalories);

        double avgCalories = menu.stream()
                .collect(averagingInt(Dish::getCalories));

        System.out.println("avg cals=" + avgCalories);

        IntSummaryStatistics stats = menu.stream()
                .collect(summarizingInt(Dish::getCalories));

        System.out.println("stats=" + stats);

        System.out.println("main exiting.");
    }
}
