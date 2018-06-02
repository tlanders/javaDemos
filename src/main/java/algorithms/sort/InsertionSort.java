package algorithms.sort;

public class InsertionSort<T> extends AbstractSort<T> {
    @Override
    public void sort(Comparable<T>[] items) {
        assert !isSorted(items);

        for(int i = 0; i < items.length - 1; i++) {
            int it = i;
            int j = i + 1;
            while(less(items[j], items[it])) {
                exchange(items, it--, j--);
            }
        }
        assert isSorted(items);
    }
}
