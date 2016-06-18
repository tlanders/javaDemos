package misc.nodes;

/**
 * Created by tlanders on 6/18/2016.
 */
public class Node<U,V> {
    protected U x;
    protected V y;

    public U getX() {
        return x;
    }

    public V getY() {
        return y;
    }

    public Node(U x, V y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Node{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
