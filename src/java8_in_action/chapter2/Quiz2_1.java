package java8_in_action.chapter2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tlanders on 3/27/2016.
 */
public class Quiz2_1 {
    public static void main(String [] args) {
        System.out.print("main, args len=" + args.length);

        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple("green", 150));
        apples.add(new Apple("green", 200));
        apples.add(new Apple("red", 120));
        apples.add(new Apple("red", 170));

        prettyPrintApple(apples);
    }

    public static void prettyPrintApple(List<Apple> inventory) {
        for(Apple a : inventory) {
            System.out.println("apple: " + a);
        }

    }
}
