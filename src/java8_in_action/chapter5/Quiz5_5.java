package java8_in_action.chapter5;

import java.util.*;
import java.util.stream.Collectors;

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

        // 1.  Find all transactions in the year 2011 and sort them by value (small to high).
        List<Transaction> y2011TxnList = transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());

        System.out.println("q1 - 2011 transactions by value");
        y2011TxnList.forEach(System.out::println);

        // 2.  What are all the unique cities where the traders work?
        List<String> uniqueCities = Arrays.asList(raoul, mario, alan, brian).stream()
                .map(Trader::getCity)
                .distinct()
                .collect(Collectors.toList());

        System.out.println("q2 - unique cities");
        uniqueCities.forEach(System.out::println);

        // 3.  Find all traders from Cambridge and sort them by name.
        List<Trader> traders = Arrays.asList(raoul, mario, alan, brian).stream()
                .filter(t -> t.getCity().equalsIgnoreCase("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());

        System.out.println("q3 - traders from Cambridge");
        traders.forEach(t -> System.out.println(t.getName()));

        // 4.  Return a string of all traders’ names sorted alphabetically.
        String allNames = Arrays.asList(raoul, mario, alan, brian).stream()
                .sorted(Comparator.comparing(Trader::getName))
                .map(Trader::getName)
                .reduce("", (a,b) -> a + " " + b);

        System.out.println("q4 - combined trader names");
        System.out.println("allNames=" + allNames);

        // 5.  Are any traders based in Milan?
        boolean isMilan = Arrays.asList(raoul, mario, alan, brian).stream()
                .filter(t -> t.getCity().equalsIgnoreCase("Milan"))
                .findAny()
                .isPresent();

        System.out.println("q5 - any traders in Milan");
        System.out.println("answer=" + isMilan);

        // 6.  Print all transactions’ values from the traders living in Cambridge.
        System.out.println("q6 - Cambridge transactions");
        transactions.stream()
                .filter(txn -> txn.getTrader().getCity().equalsIgnoreCase("Cambridge"))
                .mapToInt(Transaction::getValue)
                .forEach(System.out::println);

        // 7.  What’s the highest value of all the transactions?
        int maxVal = transactions.stream()
                .mapToInt(Transaction::getValue)
                .reduce(Integer.MIN_VALUE, Integer::max);
        System.out.println("q7 - highest txn value=" + maxVal);

        // 8.  Find the transaction with the smallest value.
        int minVal = transactions.stream()
                .mapToInt(Transaction::getValue)
                .reduce(Integer.MAX_VALUE, Integer::min);
        System.out.println("q7 - smallest txn value=" + minVal);

        System.out.println("Quiz 5_5 done.");
    }
}
