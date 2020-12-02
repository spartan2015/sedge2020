package sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * ~ O (N^2)
 * find the element with the lowest key and put it in the first index
 */
public class SelectionSort {

    @Test
    public void t1(){
        int[] arr = {6,5,4,3,2,1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr){
        for(int i = 0; i< arr.length; i++) {
            int min = i;
            for(int j = i+1; j < arr.length; j++){ // (n(n-1)/2) -> n^2 / 2
                if (arr[j] < arr[min]){
                    min = j;
                }
            }if (i!=min) {
                swap(i, min, arr);
            }
        }
    }

    private static void swap(int i, int j, int[] arr) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
