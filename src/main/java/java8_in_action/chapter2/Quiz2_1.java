package java8_in_action.chapter2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tlanders on 3/27/2016.
 * Demonstrates behavior parameterization using old-style interface method.
 */
public class Quiz2_1 {
    public static void main(String [] args) {
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple("green", 150));
        apples.add(new Apple("green", 200));
        apples.add(new Apple("red", 120));
        apples.add(new Apple("red", 170));

        prettyPrintApples(apples, new AppleColorPrinter());
        prettyPrintApples(apples, new AppleWeightPrinter());

        // lambda version of above
//        prettyPrintApples(apples, (Apple a) -> a.toString());
        prettyPrintApples(apples, Apple::toString);

        System.out.println("main exiting.");
    }

    public static void prettyPrintApples(List<Apple> inventory, ApplePrettyPrinter printer) {
        for(Apple a : inventory) {
            System.out.println(printer.print(a));
        }

    }

    @FunctionalInterface
    interface ApplePrettyPrinter {
        String print(Apple a);
    }

    static class AppleColorPrinter implements ApplePrettyPrinter {
        @Override
        public String print(Apple a) {
            return "Apple color: " + a.getColor();
        }
    }

    static class AppleWeightPrinter implements ApplePrettyPrinter {
        @Override
        public String print(Apple a) {
            return "Apple weight: " + a.getWeight();
        }
    }
}
