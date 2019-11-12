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
        Assert.assertArrayEquals(new Integer[]{1,2,3}, quickSortInt.partition(new Integer[]{1,2,3}, 0, 2));
        Assert.assertArrayEquals(new Integer[]{1,2,3}, quickSortInt.partition(new Integer[]{1,3,2}, 0, 2));
        Assert.assertArrayEquals(new Integer[]{1,2,3}, quickSortInt.partition(new Integer[]{3, 1, 2}, 0, 2));
        Assert.assertArrayEquals(new Integer[]{1,2,3}, quickSortInt.partition(new Integer[]{3, 2, 1}, 0, 2));
        Assert.assertArrayEquals(new Integer[]{1,2,3,4}, quickSortInt.partition(new Integer[]{1,2,3,4}, 0, 3));
        Assert.assertArrayEquals(new Integer[]{1,2,3,4}, quickSortInt.partition(new Integer[]{1,4,2,3}, 0, 3));
        Assert.assertArrayEquals(new Integer[]{1,2,3,4}, quickSortInt.partition(new Integer[]{1,4,3,2}, 0, 3));
        Assert.assertArrayEquals(new Integer[]{2,1,3,4}, quickSortInt.partition(new Integer[]{2,1,4,3}, 0, 3));
        Assert.assertArrayEquals(new Integer[]{1,3,2,4}, quickSortInt.partition(new Integer[]{4,3,2,1}, 0, 3));
        Assert.assertArrayEquals(new Integer[]{1,2,3,4,5}, quickSortInt.partition(new Integer[]{1,2,3,4,5}, 0, 4));
        Assert.assertArrayEquals(new Integer[]{2,1,3,5,4}, quickSortInt.partition(new Integer[]{2,4,1,5,3}, 0, 4));
        Assert.assertArrayEquals(new Integer[]{1,4,3,2,5}, quickSortInt.partition(new Integer[]{5,4,3,2,1}, 0, 4));
    }
}
