package misc.stockmarket;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by tlanders on 7/26/2016.
 */
public class TradeAccumulator {
    public TradeAccumulator add(Double price) {
        System.out.println("accum: adding price=" + price);
        return this;
    }

    public TradeAccumulator addAll(TradeAccumulator accum) {

        System.out.println("accum: combining accumulators");
        // add all elements of accum to this one
        return this;
    }

    public Set<Trade> toSet() {
        // return set of all valid trades
        System.out.println("accum: outputting set");
        return new TreeSet<>();
    }
}
