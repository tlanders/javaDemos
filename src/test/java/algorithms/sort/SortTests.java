package algorithms.sort;

import org.junit.Assert;
import org.junit.Test;

public class SortTests {
    @Test
    public void insertionSortTest() {
        Integer [] nums = {6, 8, 3, 12, 5, 34, 2, 6, 1, 23, 7, 2, 99, 6};

        printArray(nums, "unsorted");

        (new InsertionSort<Integer>()).sort(nums);

        printArray(nums, "after insertion sort");
        Assert.assertTrue(InsertionSort.isSorted(nums));

        String[] sArray = {"abc", "def", "zxy", "bed", "yur", "rxy", "fre"};

        (new InsertionSort<String>()).sort(sArray);
        printArray(sArray, "after insertion sort");
        Assert.assertTrue(InsertionSort.isSorted(sArray));
    }

    protected static void printArray(Object[] items) {
        for(Object i : items) {
            System.out.println(i);
        }
    }

    protected static void printArray(Object[] items, String label) {
        System.out.println(label);
        printArray(items);
    }
}
