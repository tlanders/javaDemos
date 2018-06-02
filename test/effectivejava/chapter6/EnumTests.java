package effectivejava.chapter6;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class EnumTests {
	@Test
	public void planetTest() {
		Planet p = Planet.EARTH;
		assertEquals(Planet.EARTH, p);
		assertNotEquals(Planet.MARS, p);
	}
}
