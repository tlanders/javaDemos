package java8_in_action.chapter4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Chapter4 {
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
        System.out.println("main starting...");

        List<String> names = menu.stream()
                .filter(d -> {
                    System.out.println("filter dish=" + d);
                    return d.getCalories() > 300;
                })
                .map(d -> {
                    System.out.println("mapping d=" + d);
                    return d.getName();
                })
                .limit(3)
                .collect(Collectors.toList());

        System.out.println("names=" + names);

        System.out.println("main exiting.");
    }
}
