package java8_in_action.chapter5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
                .collect(Collectors.joining(" "));
        System.out.println("trader names=" + traders);

        //5.  Are any traders based in Milan?
        boolean anyMilanTraders = transactions.stream().anyMatch(t -> t.getTrader().getCity().equalsIgnoreCase("milan"));

        System.out.println("any milan traders=" + anyMilanTraders);

        //6.  Print all transactions’ values from the traders living in Cambridge.
        List<Integer> txnVals = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equalsIgnoreCase("cambridge"))
                .map(Transaction::getValue)
                .collect(Collectors.toList());
        System.out.println("cambridge txn vals=" + txnVals);

        //7.  What’s the highest value of all the transactions?
        transactions.stream().map(Transaction::getValue).max(Integer::compareTo)
                .ifPresent(val -> System.out.println("max txn val=" + val));

        // TODO - how to handle no max case?

        //8.  Find the transaction with the smallest value.
        transactions.stream()
                .reduce((t1,t2) -> t1.getValue() <= t2.getValue() ? t1 : t2)
                .ifPresent(t -> System.out.println("min val txn=" + t));

        // same solution using min
        transactions.stream()
                .min(Comparator.comparing(Transaction::getValue))
                .ifPresent(t -> System.out.println("#2 min val txn=" + t));

        System.out.println("exercise 5.5.1 done.");

        // exercise from 5.6 to generate pythagorean triples
        int max = 100;
        IntStream.rangeClosed(1, max)
                .boxed()
                .flatMap(a ->
                    IntStream.rangeClosed(a, max)
                            .mapToObj( b -> new double[] {a, b, Math.sqrt(a*a + b*b)})
                )
                .filter(i -> i[2] % 1.0 == 0)
                .forEach(i -> System.out.println("[" + (int) i[0] + "," + (int) i[1] + "," + (int) i[2] + "]"));

        // 5.7 generate fibonacci tuples (0,1), (1,1), (1,2), (2,3), (3,5), (5,8)
        Stream.iterate(new int[]{0,1}, i -> new int[]{i[1], i[0] + i[1]})
                .limit(20)
                .forEach(i -> System.out.println("(" + i[0] + "," + i[1] + ")"));

        // same as above but only print Fibonacci series
        Stream.iterate(new int[]{0,1}, i -> new int[]{i[1], i[0] + i[1]})
                .limit(20)
                .mapToInt(i -> i[0])
                .forEach(System.out::println);
    }
}
