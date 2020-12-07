package search;

/**
 * search table - dictionary or indices
 */
public interface SearchTable<Key, Value> {
    void put(Key key, Value value);

    Value get(Key key);

    int size();

    Iterable<Key> keys();

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
