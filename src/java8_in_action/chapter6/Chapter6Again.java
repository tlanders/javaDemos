package java8_in_action.chapter6;

import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.List;
import java.util.Map;

public class Chapter6Again {
	public static void main(String [] args) {
		System.out.println("Chapter6Again starting...");

		Predicate<Integer> isPrime = i -> IntStream.rangeClosed(2, (int) Math.sqrt(i)).boxed()
				.noneMatch(x -> i % x == 0);
		
		assert(isPrime.test(2));
		assert(isPrime.test(3));
		assert(!isPrime.test(4));
		assert(isPrime.test(5));
		assert(!isPrime.test(6));
		assert(isPrime.test(7));
		assert(!isPrime.test(8));
		assert(isPrime.test(89));
		assert(!isPrime.test(90));

		Map<Boolean, List<Integer>> intMap = IntStream.rangeClosed(2, 30).boxed().collect(Collectors.partitioningBy(isPrime));
		
		System.out.println("Chapter6Again done.");
	}
}
