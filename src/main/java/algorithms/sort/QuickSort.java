package algorithms.sort;

/**
 * Start with unsorted array. First build a max-heap, then swap max element with end of array
 * and siftdown the element moved to the top.
 *
 * @param <T>
 */
public class QuickSort<T extends Comparable> {
    public T[] sort(T[] items) {
        return items;
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

    protected T[] partition(T[] items, int startIndex, int endIndex) {
        if(endIndex - startIndex <= 1) {
            return items;
        } else {
            int pivotIndex = endIndex;
            int bigIndex = -1;
            for(int index = startIndex; index < endIndex; index++) {
                if(items[index].compareTo(items[endIndex]) > 0) {
                    if(bigIndex < 0) {
                        bigIndex = index;
                    }
                } else {
                    if(bigIndex >= 0) {
                        exchange(items, bigIndex, index);
                        bigIndex++;
                    }
                }
            }
            if(bigIndex >= 0) {
                exchange(items, pivotIndex, bigIndex);
            }
            return items;
        }
    }

    protected T[] exchange(T[] items, int index1, int index2) {
        T temp = items[index1];
        items[index1] = items[index2];
        items[index2] = temp;
        return items;
    }
}
