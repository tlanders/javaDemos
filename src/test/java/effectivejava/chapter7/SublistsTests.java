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
		iList.add(2);
		iList.add(3);
		
		List<List<Integer>> iSublists = Sublists.of(iList).collect(Collectors.toList());
		
		System.out.println("subs: " + iSublists);
		
		assertEquals(7, iSublists.size());
		
		for(int i = 1; i < iSublists.size(); i++) {
			assertTrue(iSublists.get(i).size() > 0);
		}
		
		assertTrue(iSublists.contains(Arrays.asList(1, 2)));
		assertTrue(iSublists.contains(Arrays.asList(1, 2, 3)));
		assertTrue(iSublists.contains(Arrays.asList(1)));
		assertTrue(iSublists.contains(Arrays.asList(2, 3)));
		assertTrue(iSublists.contains(Arrays.asList()));
	}
}
