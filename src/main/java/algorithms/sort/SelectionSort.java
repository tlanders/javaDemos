package algorithms.sort;

/**
 * Start with unsorted array. On ith iteration, find next smallest item below i in the array
 * and move it to end of sorted portion of the array.
 * @param <T>
 */
public class SelectionSort<T> extends AbstractSort<T> {
    @Override
    public void sort(Comparable<T>[] items) {
        for(int i = 0; i < items.length - 1; i++) {
            int low = i;
            for(int j = i + 1; j < items.length - 1; j++) {
                if(less(items[j], items[low])) {
                    low = j;
                }
            }
            exchange(items, i, low);
        }
        assert isSorted(items);
    }
}
