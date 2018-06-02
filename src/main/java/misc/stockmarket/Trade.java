package misc.stockmarket;

/**
 * Created by tlanders on 7/26/2016.
 */
public class Trade {
    protected double buy;
    protected double sell;

    public double gain() {
        return sell - buy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trade trade = (Trade) o;

        if (Double.compare(trade.buy, buy) != 0) return false;
        return Double.compare(trade.sell, sell) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(buy);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(sell);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public Trade(double b, double s) {
        buy = b;
        sell = s;
    }

    public double getBuy() {
        return buy;
    }

    public double getSell() {
        return sell;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "buy=" + buy +
                ", sell=" + sell +
                '}';
    }
}
