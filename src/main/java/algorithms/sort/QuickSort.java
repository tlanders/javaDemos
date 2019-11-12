package algorithms.sort;

/**
 * Start with unsorted array. First build a max-heap, then swap max element with end of array
 * and siftdown the element moved to the top.
 *
 * @param <T>
 */
public class QuickSort<T extends Comparable> {
    public T[] sort(T[] items, int startIndex, int endIndex) {
        int len = endIndex - startIndex + 1;
        if(len > 2) {
            int partitionIndex = partition(items, startIndex, endIndex);
            sort(items, startIndex, partitionIndex);
            sort(items, partitionIndex + 1, endIndex);
        } else if(len == 2) {
            partition(items, startIndex, endIndex);
        }
//        assert isSorted(items);
        return items;
    }

    protected int partition(T[] items, int startIndex, int endIndex) {
        if(endIndex - startIndex <= 0) {
            return endIndex;
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
                return bigIndex;
            } else {
                return  endIndex;
            }
        }
    }

    protected T[] exchange(T[] items, int index1, int index2) {
        T temp = items[index1];
        items[index1] = items[index2];
        items[index2] = temp;
        return items;
    }
}
