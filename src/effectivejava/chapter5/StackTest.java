package effectivejava.chapter5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.List;

import org.junit.Test;

public class StackTest {

	@Test
	public void testStackWithoutType() {
		Stack s = new Stack();
		assertTrue(s.isEmpty());

		List items = Arrays.asList(1,"a",2,"b",3,"c",4,"d",5,"e",6,"f");

		for(Object o : items) {
			s.push(o);
		}
		
		for(int i = items.size() - 1; i >= 0; i--) {
			assertFalse(s.isEmpty());
			assertEquals(items.get(i), s.pop());
		}
		
		assertTrue(s.isEmpty());
		
		try {
			s.pop();
			fail("Pop should have thrown an exception");
		} catch(Exception e) {
			assertTrue(e instanceof EmptyStackException);
		}
		
		try {
			s.push(null);
		} catch(Exception e) {
			assertTrue(e instanceof NullPointerException);
		}
		
	}

	@Test
	public void testStackWithType() {
		Stack<Number> s = new Stack<>();
		assertTrue(s.isEmpty());

		List<Number> items = Arrays.asList(1,2.3,2,3.4,3,BigDecimal.valueOf(1.23),4,Float.valueOf("-4.321"),5);

		for(Number n : items) {
			s.push(n);
		}
		
		for(int i = items.size() - 1; i >= 0; i--) {
			assertFalse(s.isEmpty());
			assertEquals(items.get(i), s.pop());
		}
		
		assertTrue(s.isEmpty());

		try {
			s.pop();
			fail("Pop should have thrown an exception");
		} catch(Exception e) {
			assertTrue(e instanceof EmptyStackException);
		}
		
		try {
			s.push(null);
		} catch(Exception e) {
			assertTrue(e instanceof NullPointerException);
		}
	}

	@Test
	public void testStackPushAllSimple() {
		Stack<Integer> s = new Stack<>();
		assertTrue(s.isEmpty());

		List<Integer> items = Arrays.asList(1, 3, 5, 7, 9, 8, 6, 4, 2);

		s.pushAll(items);
		
		for(int i = items.size() - 1; i >= 0; i--) {
			assertFalse(s.isEmpty());
			assertEquals(items.get(i), s.pop());
		}
		
		assertTrue(s.isEmpty());
		
		try {
			s.pop();
			fail("Pop should have thrown an exception");
		} catch(Exception e) {
			assertTrue(e instanceof EmptyStackException);
		}
	}

	@Test
	public void testStackPushAllSubclass() {
		Stack<Number> s = new Stack<>();

		List<Integer> items = Arrays.asList(1, 3, 5, 7, 9, 8, 6, 4, 2);

		s.pushAll(items);
		
		for(int i = items.size() - 1; i >= 0; i--) {
			assertFalse(s.isEmpty());
			assertEquals(items.get(i), s.pop());
		}
	
		assertTrue(s.isEmpty());
		
		try {
			s.pop();
			fail("Pop should have thrown an exception");
		} catch(Exception e) {
			assertTrue(e instanceof EmptyStackException);
		}
	}
	
	@Test
	public void testStackPopAllSimple() {
		Stack<Integer> s = new Stack<>();
		assertTrue(s.isEmpty());

		List<Integer> items = Arrays.asList(1, 3, 5, 7, 9, 8, 6, 4, 2);

		s.pushAll(items);
		
		List<Integer> destList = new ArrayList<>();

		s.popAll(destList);
		
		assertTrue(s.isEmpty());
		
		Collections.reverse(items);
		assertEquals(destList, items);
	}

}
