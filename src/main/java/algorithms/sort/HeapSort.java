package algorithms.sort;

/**
 * Start with unsorted array. First build a max-heap, then swap max element with end of array
 * and siftdown the element moved to the top.
 *
 * @param <T>
 */
public class HeapSort<T extends Comparable> {
    public T[] sort(T[] items) {
        heapify(items);

        // swap top of heap with bottom of unsorted heap. then sift down item moved to top.
        for(int i = 0; i < items.length - 1; i++) {
            exchange(items, 0, items.length - 1 - i);
            siftdown(items, 0, items.length - i);
        }
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

    protected T[] siftdown(T[] items) {
        return siftdown(items, 0, items.length - 1);
    }

    protected T[] siftdown(T[] items, int startIndex) {
        return siftdown(items, startIndex, items.length - 1);
    }

    protected T[] siftdown(T[] items, int startIndex, int lastIndex) {
        int currentIndex = startIndex;
        while(currentIndex < lastIndex) {
            int leftChildIndex = currentIndex * 2 + 1;
            int rightChildIndex = currentIndex * 2 + 2;
            if(rightChildIndex <= lastIndex) {
                if(items[currentIndex].compareTo(items[rightChildIndex]) < 0 && items[leftChildIndex].compareTo(items[rightChildIndex]) < 0) {
                    exchange(items, currentIndex, rightChildIndex);
                    currentIndex = rightChildIndex;
                } else if(items[currentIndex].compareTo(items[leftChildIndex]) < 0){
                    exchange(items, currentIndex, leftChildIndex);
                    currentIndex = leftChildIndex;
                } else {
                    break;
                }
            } else if(leftChildIndex <= lastIndex) {
                if(items[currentIndex].compareTo(items[leftChildIndex]) < 0) {
                    exchange(items, currentIndex, leftChildIndex);
                    currentIndex = leftChildIndex;
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        return items;
    }

    protected T[] heapify(T[] items) {
        return heapify(items, 0);
    }

    protected T[] heapify(T[] items, int startIndex) {
        for(int index = startIndex + 1; index < items.length; index++) {
            int heapifyIndex = index;
            while(heapifyIndex > startIndex) {
                int parentIndex = parentIndex(heapifyIndex - startIndex) + startIndex;
                if (items[heapifyIndex].compareTo(items[parentIndex]) > 0) {
                    exchange(items, heapifyIndex, parentIndex);
                    heapifyIndex = parentIndex;
                } else {
                    break;
                }
            }
        }
        return items;
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
