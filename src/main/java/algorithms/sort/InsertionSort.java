package algorithms.sort;

/**
 * Start with unsorted array. On ith iteration, exchange a[i] to left until
 * no items with lesser indexes are less than it.
 * @param <T>
 */
public class InsertionSort<T> extends AbstractSort<T> {
    @Override
    public void sort(Comparable<T>[] items) {
        for(int i = 1; i < items.length; i++) {
            int it = i - 1;
            int j = i;
            while(it >= 0 && less(items[j], items[it])) {
                exchange(items, it--, j--);
            }
        }
        assert isSorted(items);
    }
}
