package effectivejava.chapter5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.EmptyStackException;

import org.junit.Test;

public class StackTest {

	@Test
	public void testStack() {
		Stack s = new Stack();

//		List<Object> items = Arrays.asList(1,"a",2,"b",3,"c",4,"d",5,"e",6,"f");
		
		s.push(1);
		s.push("a");
		s.push(3);
		s.push("b");
		s.push(4);
		s.push("c");
		s.push(5);
		s.push("d");
		
		assertEquals("d", s.pop());
		assertEquals(5, s.pop());
		assertEquals("c", s.pop());
		assertEquals(4, s.pop());
		assertEquals("b", s.pop());
		assertEquals(3, s.pop());
		assertEquals("a", s.pop());
		assertEquals(1, s.pop());
		
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

}
