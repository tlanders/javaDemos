package misc.stockmarket;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Created by tlanders on 7/26/2016.
 */
public class TradeCollector implements Collector<Double, TradeAccumulator, Set<Trade>> {
    @Override
    public Supplier<TradeAccumulator> supplier() {
        return null;
    }

    @Override
    public BiConsumer<TradeAccumulator, Double> accumulator() {
        return null;
    }

    @Override
    public BinaryOperator<TradeAccumulator> combiner() {
        return null;
    }

    @Override
    public Function<TradeAccumulator, Set<Trade>> finisher() {
        return null;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return null;
    }
}
