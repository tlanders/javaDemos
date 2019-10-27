package algorithms.sort;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QuickSortTests {
    QuickSort<Integer> quickSortInt;

    @Before
    public void testSetup() {
        quickSortInt = new QuickSort<>();
    }

    @Test
    public void testSort() {
        Assert.assertArrayEquals(new Integer[]{}, quickSortInt.sort(new Integer[]{}));
    }
}
