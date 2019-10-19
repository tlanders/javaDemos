package algorithms.sort;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class HeapSortTests {
    HeapSort<Integer> heapSortInt;

    @Before
    public void testSetup() {
        heapSortInt = new HeapSort<>();
    }

    @Test
    public void testParentIndex() {
        assertEquals(0, heapSortInt.parentIndex(1));
        assertEquals(0, heapSortInt.parentIndex(2));
        assertEquals(1, heapSortInt.parentIndex(3));
        assertEquals(1, heapSortInt.parentIndex(4));
        assertEquals(2, heapSortInt.parentIndex(5));
        assertEquals(2, heapSortInt.parentIndex(6));
        assertEquals(3, heapSortInt.parentIndex(7));
        assertEquals(3, heapSortInt.parentIndex(8));
        assertEquals(4, heapSortInt.parentIndex(9));
        assertEquals(4, heapSortInt.parentIndex(10));
    }

    @Test
    public void testExchange() {
        assertEquals(true, Arrays.deepEquals(new Integer[]{1}, heapSortInt.exchange(new Integer[]{1}, 0, 0)));
        assertEquals(true, Arrays.deepEquals(new Integer[]{2, 1}, heapSortInt.exchange(new Integer[]{1, 2}, 0, 1)));
        assertEquals(true, Arrays.deepEquals(new Integer[]{2, 1, 3}, heapSortInt.exchange(new Integer[]{1, 2, 3}, 0, 1)));
        assertEquals(true, Arrays.deepEquals(new Integer[]{1, 3, 2}, heapSortInt.exchange(new Integer[]{1, 2, 3}, 1, 2)));
        assertEquals(true, Arrays.deepEquals(new Integer[]{1, 2, 3}, heapSortInt.exchange(new Integer[]{1, 2, 3}, 1, 1)));
    }

    @Test
    public void testBasic() {
        Integer[] empty = {};


        new HeapSort<Integer>().sort(empty);
    }
}
