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
//        Assert.assertArrayEquals(new Integer[]{1,2}, quickSortInt.sort(new Integer[]{2,1}));
    }

    @Test
    public void testPartition() {
        Assert.assertArrayEquals(new Integer[]{}, quickSortInt.partition(new Integer[]{}, 0, 0));
        Assert.assertArrayEquals(new Integer[]{1}, quickSortInt.partition(new Integer[]{1}, 0, 0));
        Assert.assertArrayEquals(new Integer[]{1,1}, quickSortInt.partition(new Integer[]{1,1}, 0, 1));
        Assert.assertArrayEquals(new Integer[]{1,2}, quickSortInt.partition(new Integer[]{1,2}, 0, 1));
        Assert.assertArrayEquals(new Integer[]{1,2,3}, quickSortInt.partition(new Integer[]{1,3,2}, 0, 2));
    }
}
