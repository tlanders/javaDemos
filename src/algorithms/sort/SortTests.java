package algorithms.sort;

public class SortTests {
    public static void main(String [] args) {
        Integer [] nums = {1, 6, 3, 8, 12, 5, 34, 2, 6, 23, 7, 2, 99, 6};

        printArray(nums, "unsorted");

        (new InsertionSort<Integer>()).sort(nums);

        printArray(nums, "after insertion sort");

        String[] sArray = {"abc", "def", "zxy", "bed", "yur", "rxy", "fre"};

        (new InsertionSort<String>()).sort(sArray);
        printArray(sArray, "after insertion sort");
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
