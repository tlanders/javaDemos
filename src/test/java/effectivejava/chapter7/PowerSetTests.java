package effectivejava.chapter7;


import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

public class PowerSetTests {
	@Test
	public void testPowerSet() {
		Set<Long> longSet = new TreeSet<>();
		longSet.add(2L);
		longSet.add(4L);
		longSet.add(9L);
		
		List<Set<Long>> powerSet = PowerSet.of(longSet);
		
		assertEquals(8, powerSet.size());
		
		assertTrue(powerSet.get(5).contains(2L));
		assertTrue(powerSet.get(5).contains(9L));
		assertFalse(powerSet.get(5).contains(4L));
		
		assertTrue(powerSet.get(0).size() == 0);
	}
}
