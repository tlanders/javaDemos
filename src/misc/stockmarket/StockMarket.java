package misc.stockmarket;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Solution to stock market (buy low - sell high) problem.
 * https://dzone.com/articles/java-code-challenge-play-the-stock-market
 */
public class StockMarket {
     public static void main(String [] args) throws Exception {
         System.out.println("Stock market starting...");

         playMarket("19.35 19.30 18.88 18.93 18.95 19.03 19.00 18.97 18.97 18.98");

         playMarket("9.20 8.03 10.02 8.08 8.14 8.10 8.31 8.28 8.35 8.34 8.39 8.45 8.38 8.38 8.32 8.36 8.28 8.28 8.38 8.48 8.49 8.54 8.73 8.72 8.76 8.74 8.87 8.82 8.81 8.82 8.85 8.85 8.86 8.63 8.70 8.68 8.72 8.77 8.69 8.65 8.70 8.98 8.98 8.87 8.71 9.17 9.34 9.28 8.98 9.02 9.16 9.15 9.07 9.14 9.13 9.10 9.16 9.06 9.10 9.15 9.11 8.72 8.86 8.83 8.70 8.69 8.73 8.73 8.67 8.70 8.69 8.81 8.82 8.83 8.91 8.80 8.97 8.86 8.81 8.87 8.82 8.78 8.82 8.77 8.54 8.32 8.33 8.32 8.51 8.53 8.52 8.41 8.55 8.31 8.38 8.34 8.34 8.19 8.17 8.16");

         System.out.println("Stock market done.");
    }

    protected static Set<Trade> findTrades(List<Double> tradeList) {
        Set<Trade> trades = new LinkedHashSet<>();

        if(tradeList.size() >= 3) {
            Double buy = tradeList.get(0);
            for (int i = 2; i < tradeList.size(); i++) {
                Double sell = tradeList.get(i);
                if(sell.compareTo(buy) > 0) {
                    trades.add(new Trade(buy, tradeList.get(i)));
                }
            }
            trades.addAll(findTrades(tradeList.subList(1, tradeList.size())));
        }

        return trades;
    }

    protected static void playMarket(String trades) {
        System.out.println("Market trades: " + trades);
        List<Double> tradeList = Arrays.stream(trades.split(" ")).map(Double::parseDouble).collect(Collectors.toList());

        Set<Trade> tradePairs = findTrades(tradeList);

        dumpTrades(tradePairs);

        Optional<Trade> bestTrade = tradePairs.parallelStream().collect(Collectors.maxBy((t1,t2) -> {return Double.compare(t1.spread(), t2.spread());}));

        if(bestTrade.isPresent()) {
            System.out.println("best trade: " + bestTrade.get());
        } else {
            System.out.println("no good trades found");
        }
    }

    protected static void dumpTrades(Set<Trade> trades)  {
        System.out.println("dumping trades (size=" + trades.size() + ")...");
        trades.stream().forEach(System.out::println);
    }

    protected static class Trade {
        protected double buy;
        protected double sell;

        public double spread() {
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
}
