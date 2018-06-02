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
		
		System.out.println("surface weight of 100 kg");
		for(Planet x : Planet.values()) {
			final double w = x.surfaceWeight(100.0);
			assertTrue(w > 0);
			System.out.println(x.name() + ": weight (N)=" + w 
				+ ", weight(lbs)=" + (w * .22));
		}
	}
}
