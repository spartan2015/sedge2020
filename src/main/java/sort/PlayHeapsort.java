package sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * PlayHeapsort
 * 2 N lg N
 */
public class PlayHeapsort {

    @Test
    public void t(){
        int[] arr = {0, 1,2,3,4,5};

        int N = arr.length-1;

        for(int k = N/2; k >=1; k--){
                System.out.println("Sink  " + k);
            sink(arr, k, N);
                System.out.println(Arrays.toString(arr));
        }

        while(N > 1) {
                System.out.println("Swap 1 with " + N);
            swap(arr, 1, N);
                System.out.println(Arrays.toString(arr));
            sink(arr, 1, N - 1);

            N--;
                System.out.println("sink 1 to " + N);
                System.out.println(Arrays.toString(arr));
        }

    }

    void sink(int[] arr, int k, int end){
        int j = 2 * k;
        if (j > end){
            return;
        }
        if (j+1 <= end && arr[j+1] > arr[j]) j++;
        if (arr[k] < arr[j]){
            swap(arr, k, j);
            sink(arr, j ,end);
        }
    }

    void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
