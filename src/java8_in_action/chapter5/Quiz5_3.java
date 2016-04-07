package java8_in_action.chapter5;

import java.util.Arrays;

/**
 * Created by tlanders on 4/6/2016.
 */
public class Quiz5_3 {
    public static void main(String [] args) {
        System.out.println("Quiz 5_3");

        question1();

        System.out.println("Quiz 5_3 done.");
    }

    /**
     * Calcs number of dishes using map and reduce.
     */
    public static void question1() {
        String [] menu = {"hot dog", "green salad", "steak", "key lime pie"};

        // this is the hard way
        int sum = Arrays.stream(menu).map(m -> new Integer(1)).reduce(0, Integer::sum);
        System.out.println("sum1=" + sum);

        // slightly easier way
        sum = Arrays.stream(menu).map(m -> 1).reduce(0, Integer::sum);
        System.out.println("sum2=" + sum);

        // easiest way
        sum = (int) Arrays.stream(menu).count();
        System.out.println("sum3=" + sum);
    }
}
