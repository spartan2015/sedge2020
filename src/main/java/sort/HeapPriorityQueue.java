package sort;

import org.junit.Test;

/**
 * Purpose of HeapPriorityQueue is
 */
public class HeapPriorityQueue<Key extends Comparable<Key>> implements PriorityQueue<Key> {

    Comparable[] pq;
    int size = 0;

    public HeapPriorityQueue() {
        pq = new Comparable[100];
    }

    @Override
    public void insert(Key key) {
        int currentIndex = size + 1;
        pq[currentIndex] = key;
        int parent = currentIndex / 2;
        while (parent >= 1 && less(currentIndex, parent)) {
            swap(currentIndex, parent);
            currentIndex = parent;
            parent = currentIndex / 2;
        }
        size++;
    }


    public void insertSedge(Key key) {
        int currentIndex = size + 1;
        pq[currentIndex] = key;
        swim(currentIndex);
        size++;
    }

    public Key removeSedge() {
        Key result = (Key) pq[1];
        pq[1] = null;
        pq[1] = pq[size];
        size--;
        sink(1);
        return result;
    }

    @Override
    public Key peek() {
        return (Key) pq[1];
    }

    @Override
    public Key remove() {
        Key result = (Key) pq[1];
        pq[1] = null;
        int parent = 1;
        int left = 2 * parent;
        int right = left + 1;

        while (parent < size + 1) {

            if (pq[left] != null && pq[right] != null && less(left, right)) {
                swap(parent, left);
                parent = left;
            } else if (pq[right] != null) {
                swap(parent, right);
                parent = right;
            } else if (pq[left] != null) {
                swap(parent, left);
                parent = left;
            } else {
                break;
            }

            left = 2 * parent;
            right = left + 1;
        }

        size--;
        return result;
    }

    @Test
    public void t1() {
        HeapPriorityQueue<Integer> h = new HeapPriorityQueue<>();
        h.insert(3);
        h.insert(2);
        h.insert(1);

        System.out.println(h.remove());
        System.out.println(h.remove());
        System.out.println(h.remove());
    }

    @Test
    public void t2() {
        HeapPriorityQueue<Integer> h = new HeapPriorityQueue<>();
        for (int i = 1; i < 20; i++) {
            h.insert(20 - i);
        }

        for (int i = 1; i < 20; i++) {
            System.out.println(h.remove());
        }
        System.out.println("done");
    }

    @Test
    public void tSedge() {
        HeapPriorityQueue<Integer> h = new HeapPriorityQueue<>();
        for (int i = 1; i < 20; i++) {
            h.insertSedge(20 - i);
        }

        for (int i = 1; i < 20; i++) {
            System.out.println(h.removeSedge());
        }
        System.out.println("done");
    }

    void swim(int k) {
        while (k > 1 && less(k, k / 2)) {
            swap(k, k / 2);
            k /= 2;
        }
    }

    void sink(int k) {
        while (k < size) {
            int j = k * 2;
            if (j < size-1 && less(j+1, j)) j++;
            if (!less(j, k)) break;
            swap(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void swap(int i, int j) {
        Comparable t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }
}
