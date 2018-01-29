package java8_in_action.chapter6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class PrimesCollector implements Collector<Integer, List<Integer>, List<Integer>> {

	@Override
	public BiConsumer<List<Integer>, Integer> accumulator() {
		return (primeList, candidate) -> {
			if(isPrime(primeList, candidate)) {
				primeList.add(candidate);
			}
//			if(primeList.stream().noneMatch(i -> candidate % i == 0 )) {
//				primeList.add(candidate);
//			};
		};
	}

	protected boolean isPrime(List<Integer> primeList, Integer candidate) {
		return primeList.stream().noneMatch(x -> candidate % x == 0);
	}
	
	@Override
	public Set<Characteristics> characteristics() {
		return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
	}

	@Override
	public BinaryOperator<List<Integer>> combiner() {
		return (l1, l2) -> {
			l1.addAll(l2);
			return l1;
		};
	}

	@Override
	public Function<List<Integer>, List<Integer>> finisher() {
		return Function.identity();
	}

	@Override
	public Supplier<List<Integer>> supplier() {
		return ArrayList::new;
	}
	

}
