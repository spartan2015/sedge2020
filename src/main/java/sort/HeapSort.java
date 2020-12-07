package sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * O(2 N lgN)
 */
public class HeapSort {

    static void sort(Comparable<?>[] a) {
        int N = a.length;

        for (int k = N / 2; k >= 1; k--) {
            sink(a, k, N);
        }

        while (N > 1) { // extraction
            swap(a, 1, --N);
            sink(a, 1 , N);
        }
    }

    static void sink(Comparable<?>[] a, int k, int N) {
        while (k * 2 < N) {
            int j = k * 2;
            if (j + 1 < N && less(a, j + 1, j)) j++;
            if (!less(a, j, k)) break;
            swap(a, k, j);
            k = j;
        }
    }

    private static boolean less(Comparable[] a, int i, int j) {
        return a[i].compareTo(a[j]) > 0;
    }

    private static void swap(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    @Test
    public void t() {
        Integer[] arr = {-1, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
