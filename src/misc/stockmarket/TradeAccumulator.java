package misc.stockmarket;

import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/**
 * Created by tlanders on 7/26/2016.
 */
public class TradeAccumulator implements BiConsumer<TradeAccumulator, Trade> {
    @Override
    public void accept(TradeAccumulator tradeAccumulator, Trade trade) {
        System.out.println("accum.accept called");
    }
}
