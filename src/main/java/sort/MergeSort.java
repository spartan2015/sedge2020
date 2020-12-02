package sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Purpose of MergeSort is
 */
public class MergeSort {

    public static void sort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    public static void mergeSort(int[] arr, int start, int end) {
        if (end - start <= 0) return;
        int mid = (end + start) / 2;
        mergeSort(arr, start, mid);
        mergeSort(arr, mid + 1, end);

        mergeArrays(arr, start, mid, mid + 1, end);
    }

    public static void mergeSortIterative(int[] arr) {
        ArrayList<List<int[]>> toSort = new ArrayList<>();
        toSort.add(Arrays.asList(new int[]{0, arr.length - 1}));
        int size = arr.length / 2;

        while (size > 1) {
            List<int[]> levelSort = new ArrayList<>();
            List<int[]> previousLevel = toSort.get(toSort.size() - 1);

            for (int i = 0; i < previousLevel.size(); i++) {
                int[] param = previousLevel.get(i);
                int mid = (param[0] + param[1]) / 2;
                levelSort.add(new int[]{param[0], mid});
                levelSort.add(new int[]{mid + 1, param[1]});
            }
            toSort.add(levelSort);
            int mod = size % 2;
            size /= 2;
            size += mod;
        }

        for (int i = toSort.size() - 1; i >= 0; i--) {
            for (int j = 0; j < toSort.get(i).size(); j++) {
                int[] toMergeRange = toSort.get(i).get(j);
                int mid = (toMergeRange[0] + toMergeRange[1]) / 2;
                mergeArrays(arr, toMergeRange[0], mid, mid + 1, toMergeRange[1]);
            }
        }
    }

    public static void mergeBUSedge(int[] arr) {
        for (int sz = 1; sz < arr.length; sz *= 2) {
            for (int lo = 0; lo < arr.length - sz; lo += 2 * sz) {
                mergeArrays(arr, lo, lo + sz - 1, lo + sz, Math.min(lo + 2 * sz - 1, arr.length - 1));
            }
        }
    }

    private static void swap(int i, int j, int[] arr) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                System.out.println((i - 1) + " prec: " + arr[i - 1]);
                System.out.println(i + " " + arr[i]);

                return false;
            }
        }
        return true;
    }

    public static void mergeArrays(int[] arr, int s1, int e1, int s2, int e2) {
        //String before =  Arrays.toString(arr);
        for (int i = s1, j = s2; i < e2; i++) {
            if (arr[j] < arr[i]) {
                swap(i, j, arr);
                if (j < e2) {
                    j++; // but when does j move ?
                } else {

                    if (i < e1) {
                        j = s2;
                    } else {
                        // break here - algo jsut works for my iterative and my recursive -
                        // but breaks BU Sedge - so my merge works in particular case of: recursive and iterativeMine - not BU Sedge - is possible
                        // merge does not work for BU Sedge ...
                        //if () - would break if i reached e1 but obviously we must go on
                    }
                }
            }
        }
        /*boolean isSorted = true;
        for(int i = s1+1; i <=e2; i++){
            if (arr[i-i] > arr[i]){
                isSorted = false;
                break;
            }
        }
        if (!isSorted){
            System.out.println(before);
            System.out.println(Arrays.toString(arr));
        }*/
    }

    @Test
    public void t1() {
        int[] arr = {6, 5, 4, 3, 2, 1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void t11() {
        int[] arr = {8, 9, 10, 6, 7};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void mergeTest() {
        int[] arr = {8, 9, 10, 6, 7};
        mergeArrays(arr, 0, 2, 3, 4);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void mergeTest2() {
        int[] arr = {6, 7, 8, 9, 10, 1, 2, 3, 4, 5};
        mergeArrays(arr, 0, 4, 5, 9);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testSortRecursive() {
        int SIZE = 100000;
        int[] arr = new int[SIZE];
        Arrays.setAll(arr, i -> SIZE - i);
        MergeSort.sort(arr);
        System.out.println(Arrays.toString(arr));
        assertTrue(isSorted(arr));
    }


    @Test
    public void mergeSortIterative() {
        int SIZE = 100000;
        int[] arr = new int[SIZE];
        Arrays.setAll(arr, i -> SIZE - i);
        MergeSort.mergeSortIterative(arr);
        System.out.println(Arrays.toString(arr));
        assertTrue(isSorted(arr));
    }


    @Test
    public void testMergeSortBUSedge() {
        int SIZE = 1000;
        int[] arr = new int[SIZE];
        Arrays.setAll(arr, i -> SIZE - i);
        MergeSort.mergeBUSedge(arr);
        System.out.println(Arrays.toString(arr));
        assertTrue(isSorted(arr));
    }

    @Test
    public void testMergeArrays() {
        int[] arr = new int[]{4, 5, 6, 7, 8, 9, 10, 11, 1, 2, 3};
        mergeArrays(arr, 0, 7, 8, 10);
        System.out.println(Arrays.toString(arr));
    }

}
