package sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.Assert.assertTrue;
import static sort.MergeSort.isSorted;

/**
 * Purpose of Quick3Way is
 */
public class Quick3Way {


    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public void sortIterative(int[] arr) {
        sortIterative(arr, 0, arr.length - 1);
    }

    public void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    public void sort(int[] arr, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo;
        int gt = hi;
        int i = lo + 1;
        int pivot = arr[lo];

        while (i <= gt) {
            if (arr[i] < pivot) {
                swap(arr, lt++, i++);
            } else if (arr[i] > pivot) {
                swap(arr, gt--, i);
            } else {
                i++;
            }
        }

        sort(arr, lo, lt - 1);
        sort(arr, gt + 1, hi);
    }

    public void sortIterative(int[] arr, int lo, int hi) {

        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(new int[]{lo, hi});

        while (!queue.isEmpty()) {
            int[] bounds = queue.removeFirst();
            lo = bounds[0];
            hi = bounds[1];
            if (hi <= lo) continue;
            int lt = lo;
            int gt = hi;
            int i = lo + 1;
            int pivot = arr[lo];

            while (i <= gt) {
                if (arr[i] < pivot) {
                    swap(arr, lt++, i++);
                } else if (arr[i] > pivot) {
                    swap(arr, gt--, i);
                } else {
                    i++;
                }
            }

            queue.add(new int[]{lo, lt - 1});
            queue.add(new int[]{gt + 1, hi});
        }
    }

    @Test
    public void sort2() {
        int SIZE = 10;
        int[] arr = new int[SIZE];
        Arrays.setAll(arr, i -> SIZE - i);

        sort(arr);

        System.out.println(Arrays.toString(arr));
        assertTrue(isSorted(arr));

    }

    @Test
    public void sortIterativeTest() {
        int SIZE = 10;
        int[] arr = new int[SIZE];
        Arrays.setAll(arr, i -> SIZE - i);

        sortIterative(arr, 0, arr.length - 1);

        System.out.println(Arrays.toString(arr));
        assertTrue(isSorted(arr));

    }

}
