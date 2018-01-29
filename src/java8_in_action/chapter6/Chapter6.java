package java8_in_action.chapter6;

import java.util.*;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

        // partition #'s into prime and non-prime
        Predicate<Integer> isPrime = (num) -> IntStream.range(2, (int) Math.sqrt(num)).noneMatch(n -> num % n == 0);

        System.out.println("30 prime? " + isPrime.test(30));
        System.out.println("31 prime? " + isPrime.test(31));

        Map<Boolean, List<Integer>> primeOrNot = IntStream.rangeClosed(2, 100).boxed().collect(partitioningBy(isPrime));

        System.out.println("prime count: " + primeOrNot.get(true).size());
        System.out.println("non-prime count: " + primeOrNot.get(false).size());

        // use my collector instead of toList
        List<Dish> dishList = Stream.of(menu.get(0), menu.get(2), menu.get(4), menu.get(6)).collect(new ToListCollector<>());
        dumpList(dishList);
        
        System.out.println("custom collector finding primes...");
        List<Integer> primes = IntStream.rangeClosed(2, 30).boxed().collect(new PrimesCollector());
        dumpList(primes);
        
        System.out.println("main exiting.");
    }
    
    protected static void dumpList(List l) {
    	System.out.println("dumping list, size=" + l.size());
    	for(Object o : l) {
    		System.out.println(o);
    	}
    }
    
}
