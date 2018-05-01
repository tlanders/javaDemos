package effectivejava.chapter5;

import static org.junit.Assert.*;

import org.junit.Test;

public class StackTest {

	@Test
	public void testStack() {
		Stack s = new Stack();

		s.push(1);
		s.push("a");
		s.push(3);
		s.push("b");
		
		assertEquals("b", s.pop());
		assertEquals(3, s.pop());
		assertEquals("a", s.pop());
		assertEquals(1, s.pop());
	}

}
