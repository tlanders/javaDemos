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
        Assert.assertArrayEquals(new Integer[]{1}, quickSortInt.sort(new Integer[]{1}));
        Assert.assertArrayEquals(new Integer[]{1,1}, quickSortInt.sort(new Integer[]{1,1}));
        Assert.assertArrayEquals(new Integer[]{1,2}, quickSortInt.sort(new Integer[]{1,2}));
        Assert.assertArrayEquals(new Integer[]{1,2}, quickSortInt.sort(new Integer[]{2,1}));
        Assert.assertArrayEquals(new Integer[]{1,2,3}, quickSortInt.sort(new Integer[]{1,2,3}));
        Assert.assertArrayEquals(new Integer[]{1,2,3}, quickSortInt.sort(new Integer[]{2,3,1}));
        Assert.assertArrayEquals(new Integer[]{1,2,3}, quickSortInt.sort(new Integer[]{3,2,1}));
        Assert.assertArrayEquals(new Integer[]{1,2,3,4}, quickSortInt.sort(new Integer[]{1,2,3,4}));
        Assert.assertArrayEquals(new Integer[]{1,2,3,4}, quickSortInt.sort(new Integer[]{4,3,2,1}));
        Assert.assertArrayEquals(new Integer[]{1,2,3,4}, quickSortInt.sort(new Integer[]{3,1,4,2}));
    }

    @Test
    public void testPartition() {
        assertPartition(new Integer[]{}, new Integer[]{});
        assertPartition(new Integer[]{1}, new Integer[]{1});
        assertPartition(new Integer[]{1,1}, new Integer[]{1,1});
        assertPartition(new Integer[]{1,2}, new Integer[]{1,2});
        assertPartition(new Integer[]{1,2}, new Integer[]{2,1});
        assertPartition(new Integer[]{1,2,3}, new Integer[]{1,2,3});
        assertPartition(new Integer[]{1,2,3}, new Integer[]{1,3,2});
        assertPartition(new Integer[]{1,2,3}, new Integer[]{3, 1, 2});
        assertPartition(new Integer[]{1,2,3}, new Integer[]{3, 2, 1});
        assertPartition(new Integer[]{1,2,3,4}, new Integer[]{1,2,3,4});
        assertPartition(new Integer[]{1,2,3,4}, new Integer[]{1,4,2,3});
        assertPartition(new Integer[]{1,2,3,4}, new Integer[]{1,4,3,2});
        assertPartition(new Integer[]{2,1,3,4}, new Integer[]{2,1,4,3});
        assertPartition(new Integer[]{1,3,2,4}, new Integer[]{4,3,2,1});
        assertPartition(new Integer[]{1,2,3,4,5}, new Integer[]{1,2,3,4,5});
        assertPartition(new Integer[]{2,1,3,5,4}, new Integer[]{2,4,1,5,3});
        assertPartition(new Integer[]{1,4,3,2,5}, new Integer[]{5,4,3,2,1});
    }

    protected <T extends Comparable> void assertPartition(T [] expected, T [] input) {
        QuickSort<T> qs = new QuickSort<>();
        qs.partition(input, 0, input.length - 1);
        Assert.assertArrayEquals(expected, input);
    }
}
