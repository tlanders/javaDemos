package algorithms.sort;

public abstract class AbstractSort<T> implements Sort<T> {
    protected void exchange(Comparable<T>[] items, int a, int b) {
        System.out.println("exchanging " + items[a] + " and " + items[b]);
        Comparable<T> temp = items[a];
        items[a] = items[b];
        items[b] = temp;
    }

    protected static boolean isSorted(Comparable[] items) {
        for (int i = 0; i < items.length - 1; i++) {
            if(!lessOrEqual(items[i], items[i+1])) {
                System.out.println("isSorted failed at i=" + i + ", a=" + items[i] + ", b=" + items[i+1]);
                return false;
            }
        }
        return true;
    }

    protected static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    protected static boolean lessOrEqual(Comparable a, Comparable b) {
        return a.compareTo(b) <= 0;
    }
}
