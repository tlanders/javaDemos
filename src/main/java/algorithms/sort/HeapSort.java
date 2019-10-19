package algorithms.sort;

/**
 * Start with unsorted array. First build a min-heap, then pop from heap to create sorted array.
 * @param <T>
 */
public class HeapSort<T extends Comparable> {
    public void sort(T[] items) {
        heapify(items);
//        for(int i = 0; i < items.length - 1; i++) {
//            int low = i;
//            for(int j = i + 1; j < items.length - 1; j++) {
//                if(less(items[j], items[low])) {
//                    low = j;
//                }
//            }
//            exchange(items, i, low);
//        }
//        assert isSorted(items);
    }

    protected void heapify(T[] items) {
        for(int index = 1; index < items.length; index++) {
            if(items[index].compareTo(items[parentIndex(index)]) < 0) {
                exchange(items, index, parentIndex(index));
            }
        }
    }

    protected T[] exchange(T[] items, int index1, int index2) {
        T temp = items[index1];
        items[index1] = items[index2];
        items[index2] = temp;
        return items;
    }

    protected int parentIndex(int index) {
        return (index - 1) / 2;
    }
}
