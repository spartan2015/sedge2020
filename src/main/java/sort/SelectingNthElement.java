package sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * Problem like top (smalless or highest) kth elements
 *
 * based on quicksort - but not doing full sort - partial sorting - highly efficient and effective
 */
public class SelectingNthElement {

    int select(int[] arr, int k){
        int lo = 0, hi = arr.length-1;
        while(hi>lo){
            int j = partition(arr, lo, hi);
            if (j==k) return arr[k];
            else if (j > k) hi = j-1;
            else if (j < k ) lo = j+1;

        }
        return arr[k];
    }

    private int partition(int[] arr, int lo, int hi) {
        int pivot = hi;
        int i = lo;
        int j = hi - 1;
        while (j > i){
            if (arr[i] > arr[pivot]){
                swap(arr, i,j--);
            }else{
                i++;
            }
        }
        swap(arr, pivot, i);
        return i;
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    @Test
    public void t(){
        int SIZE = 20;
        int[] arr = new int[SIZE];
        Arrays.setAll(arr, i -> SIZE - i);

        System.out.println(select(arr, 2));
        System.out.println(Arrays.toString(arr));
    }
}
