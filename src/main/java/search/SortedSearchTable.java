package search;

/**
 * search table - dictionary or indices
 */
public interface SortedSearchTable<Key extends Comparable<Key>, Value> {
    void put(Key key, Value value);

    Value get(Key key);

    int size();

    Key min();

    default void deleteMin() {
        delete(min());
    }

    Key max();

    default void deleteMax() {
        delete(max());
    }

    Key floor(Key key);

    Key ceiling(Key key);

    int rank(Key key);

    Key select(int k);

    default int size(Key lo, Key hi) {
        if (hi.compareTo(lo) < 0) {
            return 0;
        } else if (contains(hi)) {
            return rank(hi) - rank(lo) + 1;
        } else {
            return rank(hi) - rank(lo);
        }
    }

    Iterable<Key> keys(Key lo, Key hi);

    default Iterable<Key> keys() {
        return keys(min(), max());
    }

    default void delete(Key key) {
        put(key, null);
    }

    default boolean contains(Key key) {
        return get(key) != null;
    }

    default boolean isEmpty(Key key) {
        return size() != 0;
    }
}
