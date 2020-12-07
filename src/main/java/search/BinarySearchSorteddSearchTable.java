package search;

import org.junit.Test;

import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class BinarySearchSorteddSearchTable<Key extends Comparable<Key>, Value> implements SortedSearchTable<Key, Value> {

    Comparable<Key>[] keys;
    Object[] values;
    int N = 0;

    public BinarySearchSorteddSearchTable() {
        keys = new Comparable[10];
        values = new Object[10];
    }

    public BinarySearchSorteddSearchTable(int capacity) {
        keys = new Comparable[capacity];
        values = new Object[capacity];
    }

    @Override
    public void delete(Key key) {
        int rank = rank(key);
        if (keys[rank] != null && key.compareTo((Key) keys[rank]) == 0) {
            for (int i = rank; i < N; i++) {
                keys[i] = keys[i + 1];
                values[i] = values[i + 1];
            }
        }
    }

    @Override
    public void put(Key key, Value value) {
        int rank = rank(key);
        if (keys[rank] != null && key.compareTo((Key) keys[rank]) == 0) {
            values[rank] = value;
        } else {
            for (int i = N; i > rank; i--) {
                keys[i] = keys[i - 1];
                values[i] = values[i - 1];
            }
            keys[rank] = key;
            values[rank] = value;
            N++;
        }
    }

    @Override
    public Value get(Key key) {
        int rank = rank(key);
        if (key.compareTo((Key) keys[rank]) == 0) {
            return (Value) values[rank];
        } else {
            return null;
        }

    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public Key min() {
        if (N > 0) {
            return (Key) keys[0];
        }
        return null;
    }

    @Override
    public Key max() {
        if (N > 0) {
            return (Key) keys[N - 1];
        }
        return null;
    }

    /**
     * key less than or equals
     * @param key
     * @return
     */
    @Override
    public Key floor(Key key) {
        int rank = rank(key);
        if (keys[rank] != null && key.compareTo((Key) keys[rank]) == 0) {
            return (Key) keys[rank];
        } else {

        }
        return (Key) keys[rank - 1];

    }

    /**
     * greater than or equals than given
     * @param key
     * @return
     */
    @Override
    public Key ceiling(Key key) {
        return (Key) keys[rank(key)];
    }

    @Override
    public Key select(int k) {
        return (Key) keys[k];
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        return null;
    }

    @Override
    public int rank(Key key) {
        return rank(key, 0, N - 1);
    }

    int rank(Key key, int lo, int hi) {
        if (hi < lo) return lo;
        int mid = (lo + hi) / 2;
        int cmp = key.compareTo((Key) keys[mid]);
        if (cmp < 0) {
            return rank(key, lo, mid - 1);
        } else if (cmp > 0) {
            return rank(key, mid + 1, hi);
        } else {
            return mid;
        }
    }

    public static class TestContainer {
        @Test
        public void p1() {
            BinarySearchSorteddSearchTable<Integer, Integer> st = new BinarySearchSorteddSearchTable<>(20);
            for (int i = 20; i > 0; i--) {
                st.put(i, i);
            }
            for (int i = 20; i > 0; i--) {
                assertEquals(i - 1, st.rank(i));
                assertEquals((Integer) i, st.get(i));
            }
            System.out.println(st);
        }

        @Test
        public void f1() {
            BinarySearchSorteddSearchTable<Integer, Integer> st = new BinarySearchSorteddSearchTable<>(20);
            st.put(2, 2);
            assertEquals((Integer) 2, st.ceiling(1));
            assertEquals((Integer) 2, st.ceiling(2));
            st.put(4, 4);
            assertEquals((Integer) 4, st.ceiling(3));
            assertEquals((Integer) 4, st.ceiling(4));
        }

        @Test
        public void c1() {
            BinarySearchSorteddSearchTable<Integer, Integer> st = new BinarySearchSorteddSearchTable<>(20);
            st.put(2, 2);
            assertEquals((Integer) 2, st.floor(2));
            assertEquals((Integer) 2, st.floor(3));
            st.put(4, 4);
            assertEquals((Integer) 4, st.floor(4));
            assertEquals((Integer) 4, st.floor(5));

        }
    }
}
