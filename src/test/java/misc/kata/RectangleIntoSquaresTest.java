package misc.kata;

import org.junit.Test;

import java.util.List;

import static misc.kata.RectangleIntoSquares.sqInRect;
import static org.junit.Assert.assertEquals;

public class RectangleIntoSquaresTest {
    @Test
    public void testSqInRect() {
        assertEquals(null, sqInRect(0, 0));
        assertEquals(null, sqInRect(1, 1));
        assertEquals(null, sqInRect(2, 2));

        assertSqInRectangleResults(new int[]{1,1}, sqInRect(1, 2));
        assertSqInRectangleResults(new int[]{1,1}, sqInRect(2, 1));
        assertSqInRectangleResults(new int[]{1,1,1}, sqInRect(3, 1));
        assertSqInRectangleResults(new int[]{2,1,1}, sqInRect(2, 3));
        assertSqInRectangleResults(new int[]{3,2,1,1}, sqInRect(3, 5));
        assertSqInRectangleResults(new int[]{3,2,1,1}, sqInRect(5, 3));
    }

    private void assertSqInRectangleResults(int[] expected, List<Integer> actual) {
        for(int i = 0; i < expected.length; i++) {
            assertEquals((Integer) expected[i], actual.get(i));
        }
    }
}
