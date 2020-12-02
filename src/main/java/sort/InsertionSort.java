package sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * insert into position of a ever growing starting subset (lots of movement)
 * looks a lot like buble sort
 */
public class InsertionSort {

    public static void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
                swap(j, j - 1, arr);
            }
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
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
