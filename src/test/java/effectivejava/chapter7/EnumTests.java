package effectivejava.chapter7;


import static org.junit.Assert.*;

import org.junit.Test;

public class EnumTests {
	@Test
	public void testOperation() {
		Operation add = Operation.ADD;
		
		assertEquals(5, add.apply(2, 3), .0001);
		assertEquals(-1, Operation.SUBTRACT.apply(2, 3), .0001);
		assertEquals(6, Operation.MULTIPLY.apply(2, 3), .0001);
		assertEquals(.6666666, Operation.DIVIDE.apply(2, 3), .0001);
		
		assertEquals(Operation.ADD, Operation.valueOf("ADD"));
		
		double a = -31.23;
		double b = -22.34;
		for(Operation op : Operation.values()) {
			System.out.printf("%f %s %f = %f\n", a, op, b, op.apply(a, b));
		}
		
		for(Operation op : Operation.values()) {
			assertEquals(op, Operation.fromSymbol(op.toString()).get());
			assertEquals(op, Operation.valueOf(op.name()));
			
			// fromSymbol only looks up by the symbol and not the operation name
			assertFalse(Operation.fromSymbol(op.name()).isPresent());
		}
	}
}
