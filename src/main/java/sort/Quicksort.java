package sort;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;
import static sort.MergeSort.isSorted;

/**
 *
 */
public class Quicksort {

    public static void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    public static void sort(int[] arr, int start, int end) {
        if (end <= start) return;
        int mid = partition(arr, start, end);
        sort(arr, start, mid - 1);
        sort(arr, mid + 1, end);
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static int partitionMine(int[] arr, int start, int end) {
        int pivot = end;
        int i = start;
        int j = pivot - 1;
        while (arr[i] < arr[pivot] && i < j) i++;
        while (arr[j] > arr[pivot] && j > i) j--;
        while (i < j && j > start) {
            if (arr[i] > arr[pivot]) {
                swap(arr, i, j);
                j--;
            } else {
                i++;
            }
        }
        swap(arr, pivot, i);
        return i;
    }

    public static int partition(int[] arr, int start, int end) {
        int pivot = end;
        int i = start - 1;
        int j = pivot;

        while (true) {
            while (arr[++i] < arr[pivot]) if (i == end) break;
            while (arr[--j] > arr[pivot]) if (j == start) break;
            if (i >= j) break;
            swap(arr, i, j);
        }
        swap(arr, pivot, i);
        return i;
    }

    @Test
    public void sort1() {
        int[] arr = {5, 4, 3, 2, 1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void sort2() {
        int SIZE = 15000;
        int[] arr = new int[SIZE];
        Arrays.setAll(arr, i -> SIZE - i);
        sort(arr);
        System.out.println(Arrays.toString(arr));
        assertTrue(isSorted(arr));

    }

}
