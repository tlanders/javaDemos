package algorithms.trees;

public interface Tree<K extends Comparable, V> {
    V search(K key);
    void insert(K key, V value);
    int size();
}
