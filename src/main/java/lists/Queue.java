package lists;

/**
 * Purpose of Queue is
 */
public interface Queue<E> extends Iterable<E> {
    void enqueue(E e);
    E dequeue();
    int size();
    boolean isEmpty();
}
