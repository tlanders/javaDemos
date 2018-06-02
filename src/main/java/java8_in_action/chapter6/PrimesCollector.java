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
			System.out.println("candidate=" + candidate);
			int candidateRoot = (int) Math.sqrt(candidate);
			List<Integer> checkList = takeWhile(primeList, i -> i <= candidateRoot);
			if(isPrime(checkList, candidate)) {
				primeList.add(candidate);
			}
		};
	}

	/**
	 * Continues taking list items as long as the predicate returns true. When the predicate returns
	 * false then the search stops and the list up to the last index is returned. 
	 * @param theList
	 * @param thePredicate
	 * @return
	 */
	protected static List<Integer> takeWhile(List<Integer> theList, Predicate<Integer> thePredicate) {
		for(int i = 0; i < theList.size(); i++) {
			Integer currentInt = theList.get(i);
			if(!thePredicate.test(currentInt)) {
				return theList.subList(0, i);
			}
		}
		return theList;
	}
	
	protected boolean isPrime(List<Integer> primeList, Integer candidate) {
		return primeList.stream()
				.peek(i -> System.out.println("  checking i=" + i))
				.noneMatch(x -> candidate % x == 0);
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
