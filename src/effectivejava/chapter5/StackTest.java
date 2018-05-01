package effectivejava.chapter5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.List;

import org.junit.Test;

public class StackTest {

	@Test
	public void testStack() {
		Stack s = new Stack();

		List<Object> items = Arrays.asList(1,"a",2,"b",3,"c",4,"d",5,"e",6,"f");

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

}
