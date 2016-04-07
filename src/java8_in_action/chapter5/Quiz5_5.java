package java8_in_action.chapter5;

import java.util.Arrays;
import java.util.List;

/**
 * Created by tlanders on 4/6/2016.
 */
public class Quiz5_5 {
    public static void main(String [] args) {
        System.out.println("Quiz 5_5");

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        question1();

        System.out.println("Quiz 5_5 done.");
    }

    /**
     */
    public static void question1() {
    }
}
