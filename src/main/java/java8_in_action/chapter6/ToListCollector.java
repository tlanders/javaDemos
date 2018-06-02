package java8_in_action.chapter6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {
	@Override
	public Supplier<List<T>> supplier() {
		//return () -> new ArrayList<>();
		return ArrayList::new;
	}
	
	@Override
	public BiConsumer<List<T>, T> accumulator() {
		//return (List<T> l, T item) -> l.add(item);
		return List::add;
	}

	@Override
	public Set<Characteristics> characteristics() {
		// This collector has an identity finish.
		// It can be executed concurrently but only if the data source is unordered.
		return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT));
	}

	@Override
	public BinaryOperator<List<T>> combiner() {
		return (List<T> l1, List<T> l2) -> {
			l1.addAll(l2);
			return l1;
		};
	}

	@Override
	public Function<List<T>, List<T>> finisher() {
		return Function.identity();
		//		return (List<T> acc) -> acc;
	}
}
