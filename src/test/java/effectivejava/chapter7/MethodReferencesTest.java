package effectivejava.chapter7;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import org.junit.Test;

public class MethodReferencesTest {
	@Test
	public void mergeWithMethodRef() {
		Map<Integer,Integer> keyCountMap = new HashMap<>();
		
		for(int i = 0; i < 10; i++) {
			keyCountMap.merge(i, 1, (count, incr) -> count + incr);
		}

		IntStream.range(2, 6).forEach(i -> keyCountMap.merge(i, 1, (count, incr) -> count + incr));
		IntStream.range(3, 7).forEach(i -> keyCountMap.merge(i, 1, (count, incr) -> count + incr));
		
		System.out.printf("map 1 size=%d, map=%s\n", keyCountMap.size(), keyCountMap);

		assertEquals((Integer) 3, (Integer) keyCountMap.get(5)); 
		assertEquals((Integer) 1, (Integer) keyCountMap.get(1)); 
		assertEquals((Integer) 1, (Integer) keyCountMap.get(9)); 

		// map 2 uses method reference instead of a lambda
		Map<Integer,Integer> keyCountMap2 = new HashMap<>();
		
		for(int i = 0; i < 10; i++) {
			keyCountMap2.merge(i, 1, Integer::sum);
		}

		IntStream.range(2, 6).forEach(i -> keyCountMap2.merge(i, 1, Integer::sum));
		IntStream.range(3, 7).forEach(i -> keyCountMap2.merge(i, 1, Integer::sum));
		
		System.out.printf("map 2 size=%d, map=%s\n", keyCountMap2.size(), keyCountMap2);

		// two maps should be the same
		for(Integer k : keyCountMap.keySet()) {
			assertEquals(keyCountMap.get(k), keyCountMap2.get(k));
		}
	}
}
