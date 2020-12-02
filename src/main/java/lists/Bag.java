package lists;

/**
 * Purpose of Bag is
 */
public interface Bag<E> extends Iterable<E>{
    void add(E e);
    int size();
    boolean isEmpty();
}
