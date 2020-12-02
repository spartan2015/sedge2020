package sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * based on insertion sort - but moving elements far apart by choosing an initial distance (h sorted)
 * start with a h - 3 -> got to 2  and 1 - 1 sort is equals insert sort
 */
public class ShellSort {

    public static void sortMine(int[] arr) {
        int h = 1;
        while (h < (arr.length / 3)) h = 3 * h + 1;
        for (; h > 0; h/=3) {
            for (int i = h; i < arr.length; i ++) {
                for (int j = i; j >= h && arr[j] < arr[j - h]; j -= h) {
                        swap(j, j - h, arr);
                }
            }
        }
    }

    public static void sortSedge( int[] arr) {
        int h = 1;
        // 4 (13)...13 (40)..40 (121)
        //
        while (h < (arr.length / 3)) h = 3 * h + 1;
        while (h > 0) {
            for (int i = h; i < arr.length; i++) {
                for (int j = i; j >= h && arr[j] < arr[j - h]; j -= h) {
                        swap(j, j - h, arr);
                }
            }
            h /= 3;
        }
    }

    private static void swap(int i, int j, int[] arr) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    @Test
    public void t1() {
        int[] arr = {6, 5, 4, 3, 2, 1};
        sortSedge( arr);
        System.out.println(Arrays.toString(arr));
    }

}
