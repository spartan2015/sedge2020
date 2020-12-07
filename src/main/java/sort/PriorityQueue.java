package sort;

/**
 * Purpose of PriorityQueue is
 */
public interface PriorityQueue<Key extends Comparable<Key>> {

    void insert(Key key);

    Key peek();

    Key remove();

}
