package effectivejava.chapter5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.List;

import org.junit.Test;

public class StackTest {

	@Test
	public void testStackWithoutType() {
		Stack s = new Stack();

		List items = Arrays.asList(1,"a",2,"b",3,"c",4,"d",5,"e",6,"f");

		for(Object o : items) {
			s.push(o);
		}
		
		for(int i = items.size() - 1; i > 0; i--) {
			assertEquals(items.get(i), s.pop());
		}
		
		try {
			s.pop();
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

		List<Number> items = Arrays.asList(1,2.3,2,3.4,3,BigDecimal.valueOf(1.23),4,Float.valueOf("-4.321"),5);

		for(Number n : items) {
			s.push(n);
		}
		
		for(int i = items.size() - 1; i > 0; i--) {
			assertEquals(items.get(i), s.pop());
		}
		
		try {
			s.pop();
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

		List<Integer> items = Arrays.asList(1, 3, 5, 7, 9, 8, 6, 4, 2);

		s.pushAll(items);
		
		for(int i = items.size() - 1; i > 0; i--) {
			assertEquals(items.get(i), s.pop());
		}
		
		try {
			s.pop();
		} catch(Exception e) {
			assertTrue(e instanceof EmptyStackException);
		}
	}

	@Test
	public void testStackPushAllSubclass() {
		Stack<Number> s = new Stack<>();

		List<Integer> items = Arrays.asList(1, 3, 5, 7, 9, 8, 6, 4, 2);

		s.pushAll(items);
		
		for(int i = items.size() - 1; i > 0; i--) {
			assertEquals(items.get(i), s.pop());
		}
		
		try {
			s.pop();
		} catch(Exception e) {
			assertTrue(e instanceof EmptyStackException);
		}
	}
}
