package misc.fizzbuzz;

import java.util.Optional;
import java.util.Random;

/**
 * From http://javarevisited.blogspot.com/2015/04/fizzbuzz-solution-in-java-8.html.
 *
 * Problem : For a given natural number greater than zero return:
 * "Fizz" if the number is dividable by 3
 * "Buzz" if the number is dividable by 5
 * "FizzBuzz" if the number is dividable by 15
 * the same number if number is neither divisible by 3 nor 5.
 *
 * Created by tlanders on 1/3/2016.
 */
public class FizzBuzz {
    public static void main(String [] args) throws Exception {
        System.out.println("FizzBuzz starting...");

        Random rand = new Random();
        for(int i = 0; i < 30; i++) {
            int aNum = rand.nextInt(30);
            System.out.println(preJava8Solution(aNum));
            System.out.println(java8Solution(aNum));
        }

        System.out.println("FizzBuzz done.");
    }

    private static String java8Solution(int theNum) {
        String result = Optional.of(theNum).map(n -> (n % 3 == 0 ? "Fizz" : "") + (n % 5 == 0 ? "Buzz" : "")).get();

        return result.isEmpty() ? Integer.toString(theNum) : result;
    }

    private static String preJava8Solution(int theNum) {
        System.out.println("pre called, num=" + theNum);
        if(theNum % 15 == 0) {
            return "FizzBuzz";
        } else if(theNum % 3 == 0) {
            return "Fizz";
        } else if(theNum % 5 == 0) {
            return "Buzz";
        } else {
            return Integer.toString(theNum);
        }
    }
}
