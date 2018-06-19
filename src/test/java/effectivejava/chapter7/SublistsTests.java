package effectivejava.chapter7;


import static org.junit.Assert.*;

import java.util.*;
import java.util.stream.Collectors;

import org.junit.Test;

public class SublistsTests {
	@Test
	public void testSublists() {
		List<Integer> iList = new ArrayList<>();
		iList.add(1);
		iList.add(3);
		
		List<List<Integer>> iSublists = Sublists.of(iList).collect(Collectors.toList());
		
		assertEquals(4, iSublists.size());
		
//		assertTrue(powerSet.get(5).contains(2L));
	}
}
