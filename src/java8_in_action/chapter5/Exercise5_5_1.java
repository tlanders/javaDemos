package java8_in_action.chapter5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Exercise5_5_1 {
    public static void main(String... args) {
        System.out.println("exercise 5.5.1 starting...");

        // domain for this exercise
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950));

        //1.  Find all transactions in the year 2011 and sort them by value (small to high).
        List<Transaction> txn2011ByValue = transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());

        System.out.println("2011 txns by value, list=" + txn2011ByValue);

        //2.  What are all the unique cities where the traders work?
        List<String> uniqueCities = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("unique cities=" + uniqueCities);

        //3.  Find all traders from Cambridge and sort them by name.
        List<Trader> cambridgeTraders = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equalsIgnoreCase("cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println("Cambridge traders=" + cambridgeTraders);

        //4.  Return a string of all traders’ names sorted alphabetically.
        String traders = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .distinct()
                .sorted()
                .reduce((s1,s2) -> s1 + " " + s2)
                .toString();
        System.out.println("trader names=" + traders);

        //5.  Are any traders based in Milan?
        //6.  Print all transactions’ values from the traders living in Cambridge.
        //7.  What’s the highest value of all the transactions?
        //8.  Find the transaction with the smallest value.

        System.out.println("exercise 5.5.1 done.");
    }
}
