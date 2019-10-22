package algorithms.sort;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
        Assert.assertArrayEquals(new Integer[]{1}, heapSortInt.exchange(new Integer[]{1}, 0, 0));
        Assert.assertArrayEquals(new Integer[]{2, 1}, heapSortInt.exchange(new Integer[]{1, 2}, 0, 1));
        Assert.assertArrayEquals(new Integer[]{2, 1, 3}, heapSortInt.exchange(new Integer[]{1, 2, 3}, 0, 1));
        Assert.assertArrayEquals(new Integer[]{1, 3, 2}, heapSortInt.exchange(new Integer[]{1, 2, 3}, 1, 2));
        Assert.assertArrayEquals(new Integer[]{1, 2, 3}, heapSortInt.exchange(new Integer[]{1, 2, 3}, 1, 1));
    }

    @Test
    public void testHeapify() {
        // these should stay in same order
        Assert.assertArrayEquals(new Integer[]{}, heapSortInt.heapify(new Integer[]{}));
        Assert.assertArrayEquals(new Integer[]{1}, heapSortInt.heapify(new Integer[]{1}));
        Assert.assertArrayEquals(new Integer[]{1, 1}, heapSortInt.heapify(new Integer[]{1, 1}));
        Assert.assertArrayEquals(new Integer[]{2, 1}, heapSortInt.heapify(new Integer[]{2, 1}));
        Assert.assertArrayEquals(new Integer[]{1, 1, 1}, heapSortInt.heapify(new Integer[]{1, 1, 1}));
        Assert.assertArrayEquals(new Integer[]{3, 2, 1}, heapSortInt.heapify(new Integer[]{3, 2, 1}));
        Assert.assertArrayEquals(new Integer[]{4, 3, 2, 1}, heapSortInt.heapify(new Integer[]{4, 3, 2, 1}));
        Assert.assertArrayEquals(new Integer[]{5, 4, 3, 2, 1}, heapSortInt.heapify(new Integer[]{5, 4, 3, 2, 1}));

        // these should be reordered
        Assert.assertArrayEquals(new Integer[]{2, 1}, heapSortInt.heapify(new Integer[]{1, 2}));
        Assert.assertArrayEquals(new Integer[]{3, 1, 2}, heapSortInt.heapify(new Integer[]{1, 2, 3}));
        Assert.assertArrayEquals(new Integer[]{2, 1, 2}, heapSortInt.heapify(new Integer[]{1, 2, 2}));
        Assert.assertArrayEquals(new Integer[]{2, 1, 1}, heapSortInt.heapify(new Integer[]{1, 2, 1}));
        Assert.assertArrayEquals(new Integer[]{4, 3, 2, 1}, heapSortInt.heapify(new Integer[]{1, 2, 3, 4}));
        Assert.assertArrayEquals(new Integer[]{5, 4, 2, 1, 3}, heapSortInt.heapify(new Integer[]{1, 2, 3, 4, 5}));
        Assert.assertArrayEquals(new Integer[]{5, 4, 1, 3, 2}, heapSortInt.heapify(new Integer[]{3, 5, 1, 4, 2}));
        Assert.assertArrayEquals(new Integer[]{99, 33, 63, 29, 21, 17, 37, 1, 16, 12}, heapSortInt.heapify(new Integer[]{63, 21, 37, 16, 29, 17, 99, 1, 33, 12}));

        // heapify with a start index
        Assert.assertArrayEquals(new Integer[]{}, heapSortInt.heapify(new Integer[]{}, 1));
        Assert.assertArrayEquals(new Integer[]{1}, heapSortInt.heapify(new Integer[]{1}, 1));
        Assert.assertArrayEquals(new Integer[]{1, 1}, heapSortInt.heapify(new Integer[]{1, 1}, 1));
        Assert.assertArrayEquals(new Integer[]{1, 2}, heapSortInt.heapify(new Integer[]{1, 2}, 1));
        Assert.assertArrayEquals(new Integer[]{1, 1, 1}, heapSortInt.heapify(new Integer[]{1, 1, 1}, 1));
        Assert.assertArrayEquals(new Integer[]{1, 2, 3}, heapSortInt.heapify(new Integer[]{1, 2, 3}, 2));
        Assert.assertArrayEquals(new Integer[]{1, 2, 4, 3}, heapSortInt.heapify(new Integer[]{1, 2, 3, 4}, 2));
        Assert.assertArrayEquals(new Integer[]{1, 2, 3, 5, 4}, heapSortInt.heapify(new Integer[]{1, 2, 3, 4, 5}, 3));
        Assert.assertArrayEquals(new Integer[]{2, 1, 3, 5, 4}, heapSortInt.heapify(new Integer[]{2, 1, 3, 4, 5}, 3));
        Assert.assertArrayEquals(new Integer[]{99, 98, 3, 5, 4}, heapSortInt.heapify(new Integer[]{99, 98, 3, 4, 5}, 3));
        Assert.assertArrayEquals(new Integer[]{99, 98, 5, 4, 1, 3, 2}, heapSortInt.heapify(new Integer[]{99, 98, 3, 5, 1, 4, 2}, 2));
        Assert.assertArrayEquals(new Integer[]{1003, 1002, 1001, 99, 33, 63, 29, 21, 17, 37, 1, 16, 12},
                heapSortInt.heapify(new Integer[]{1003, 1002, 1001, 63, 21, 37, 16, 29, 17, 99, 1, 33, 12}, 3));
    }

    @Test
    public void testSiftdown() {
        Assert.assertArrayEquals(new Integer[]{}, heapSortInt.siftdown(new Integer[]{}));
        Assert.assertArrayEquals(new Integer[]{1}, heapSortInt.siftdown(new Integer[]{1}));
        Assert.assertArrayEquals(new Integer[]{1, 1}, heapSortInt.siftdown(new Integer[]{1, 1}));
        Assert.assertArrayEquals(new Integer[]{2, 1}, heapSortInt.siftdown(new Integer[]{2, 1}));
        Assert.assertArrayEquals(new Integer[]{1, 1, 1}, heapSortInt.siftdown(new Integer[]{1, 1, 1}));
        Assert.assertArrayEquals(new Integer[]{3, 2, 1}, heapSortInt.siftdown(new Integer[]{3, 2, 1}));
        Assert.assertArrayEquals(new Integer[]{4, 3, 2, 1}, heapSortInt.siftdown(new Integer[]{4, 3, 2, 1}));

        Assert.assertArrayEquals(new Integer[]{2, 1}, heapSortInt.siftdown(new Integer[]{1, 2}));
        Assert.assertArrayEquals(new Integer[]{4, 3, 2}, heapSortInt.siftdown(new Integer[]{2, 3, 4}));
        Assert.assertArrayEquals(new Integer[]{4, 2, 3}, heapSortInt.siftdown(new Integer[]{2, 4, 3}));

        Assert.assertArrayEquals(new Integer[]{15, 7, 9, 2}, heapSortInt.siftdown(new Integer[]{2, 15, 9, 7}));
        Assert.assertArrayEquals(new Integer[]{19, 15, 8, 7, 11, 2}, heapSortInt.siftdown(new Integer[]{2, 15, 19, 7, 11, 8}));
        Assert.assertArrayEquals(new Integer[]{19, 15, 10, 7, 11, 8, 2}, heapSortInt.siftdown(new Integer[]{2, 15, 19, 7, 11, 8, 10}));
    }

    @Test
    public void testSort() {
        Assert.assertArrayEquals(new Integer[]{}, heapSortInt.sort(new Integer[]{}));
        Assert.assertArrayEquals(new Integer[]{1}, heapSortInt.sort(new Integer[]{1}));
    }
}
