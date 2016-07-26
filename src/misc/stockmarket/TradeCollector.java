package misc.stockmarket;

import java.util.EnumSet;
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
        System.out.println("new accumulator");
        return TradeAccumulator::new;
    }

    @Override
    public BiConsumer<TradeAccumulator, Double> accumulator() {
        return (accum, price) -> {accum.add(price);};
    }

    @Override
    public BinaryOperator<TradeAccumulator> combiner() {
        // combine two TradeAccumulators into one
        return (accum1, accum2) -> {return accum1.addAll(accum2);};
    }

    @Override
    public Function<TradeAccumulator, Set<Trade>> finisher() {
        // reduce TradeAccumulator to a set of trades
        return (accum) -> {return accum.toSet();};
    }

    @Override
    public Set<Characteristics> characteristics() {
        // can combine concurrently but incoming trades must be handled sequentially
        return EnumSet.of(Characteristics.CONCURRENT);
    }
}
