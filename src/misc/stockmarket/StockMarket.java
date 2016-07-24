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

         String in1 = "19.35 19.30 18.88 18.93 18.95 19.03 19.00 18.97 18.97 18.98";

         playMarket(in1);

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
//                    System.out.println("new trade: " + trades.get(trades.size() - 1));
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

//        Arrays.stream(trades.split(" ")).mapToDouble(Double::parseDouble)
//                .collect(Collectors.groupingBy(Double::doubleValue));
    }

    protected static class Trade {
        protected double buy;
        protected double sell;

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
