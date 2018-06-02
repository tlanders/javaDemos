package java8_in_action.chapter5;

/**
 * Created by tlanders on 4/6/2016.
 */
public class Transaction {
    protected final Trader trader;
    protected final int year;
    protected final int value;

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader() {
        return trader;
    }

    public int getYear() {
        return year;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "trader='" + trader + '\'' +
                ", year=" + year +
                ", value=" + value +
                '}';
    }
}
