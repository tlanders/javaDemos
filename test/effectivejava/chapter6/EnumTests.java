package effectivejava.chapter6;

import static org.junit.Assert.*;

import org.junit.Test;

public class EnumTests {
	@Test
	public void planetTest() {
		Planet e = Planet.EARTH;
		assertEquals(Planet.EARTH, e);
		assertNotEquals(Planet.MARS, e);
		
		Planet j = Planet.JUPITER;
		
		assertTrue(j.mass() > e.mass());
		assertTrue(j.surfaceWeight(100.0) > 0);
	}
}
