package algorithms.trees;

public class LeftLeaningRedBlackTree<K extends Comparable, V> implements Tree<K,V> {
    protected enum NodeColor {RED, BLACK}
    protected Node root;
    protected int size;

    protected class Node {
        protected K key;
        protected V value;
        protected Node leftChild;
        protected Node rightChild;
        protected NodeColor color = NodeColor.RED;    // color of parent link

        public Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void insert(K key, V value) {
        // find node's place in the tree. add it there with a red color.
        // then rebalance tree.
        if(root == null) {
            root = new Node(key,value);
            size++;
            return;
        }

        Node n = root;
        while(n != null) {
            int compare = key.compareTo(n.key);
            if(compare == 0) {
                // not allowing dup keys so just update value
                n.value = value;
            } else if(compare < 0) {
                if(n.leftChild != null) {
                    n = n.leftChild;
                } else {
                    n.leftChild = new Node(key, value);
                    size++;
                    return;
                }
            } else {
                if(n.rightChild != null) {
                    n = n.rightChild;
                } else {
                    n.rightChild = new Node(key, value);
                    size++;
                    return;
                }
            }
        }

        // TODO - now need to rebalance
    }

    @Override
    public V search(K key) {
        return root != null ? search(root, key) : null;
    }

    protected V search(Node n, K key) {
        int compare = key.compareTo(n.key);
        if(compare == 0) {
            return n.value;
        } else {
            if(compare < 0) {
                if(n.leftChild != null) {

                }
            } else {

            }
        }
        return search(root, key);
    }

    protected boolean isRed(Node n) {
        return n != null && n.color == NodeColor.RED;
    }

    /**
     * Rotate a right-leaning red node to be a left-leaning red node
     * @param a
     * @return
     */
    protected Node rotateLeft(Node a) {
        assert isRed(a.rightChild);
        Node b = a.rightChild;
        a.rightChild = b.leftChild;
        b.leftChild = a;
        b.color = a.color;
        a.color = NodeColor.RED;
        return b;
    }

    /**
     * Rotate a left-leaning red node to be a right-leaning node. This is a temporary operation that
     * is sometimes needed when rebalancing the tree.
     * @param b
     * @return
     */
    protected Node rotateRight(Node b) {
        assert isRed(b.leftChild);
        Node a = b.leftChild;
        b.leftChild = a.rightChild;
        a.rightChild = b;
        a.color = b.color;
        b.color = NodeColor.RED;
        return a;
    }

    /**
     * Takes a 4-node and splits it into 2 2-nodes. Also pops the middle element up into
     * the node above it.
     * @param b
     * @return
     */
    protected Node colorFlip(Node b) {
        assert !isRed(b);
        assert isRed(b.leftChild);
        assert isRed(b.rightChild);
        b.color = NodeColor.RED;
        b.leftChild.color = NodeColor.BLACK;
        b.rightChild.color = NodeColor.BLACK;
        return b;
    }

}
