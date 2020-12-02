package sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * Purpose of CompareSortTimes is
 */
public class CompareSortTimes {

    public static final int SIZE = 10000;

    public static void executedTimed(String name, Runnable r) {
        long start = System.currentTimeMillis();
        r.run();
        System.out.print(name + ": ");
        System.out.println(System.currentTimeMillis() - start);
    }

    @Test
    public void t() {
        executedTimed("SelectionSort", () -> {
            int[] arr = new int[SIZE];
            Arrays.setAll(arr, i -> SIZE - i);
            SelectionSort.sort(arr);
            //System.out.println(Arrays.toString(arr));
        });
        executedTimed("InsertionSort", () -> {
            int[] arr = new int[SIZE];
            Arrays.setAll(arr, i -> SIZE - i);
            InsertionSort.sort(arr);
            //System.out.println(Arrays.toString(arr));
        });
        executedTimed("ShellSort.Sedge", () -> {
            int[] arr = new int[SIZE];
            Arrays.setAll(arr, i -> SIZE - i);
            ShellSort.sortSedge( arr);
            //System.out.println(Arrays.toString(arr));
        });
        executedTimed("MergeSort", () -> {
            int[] arr = new int[SIZE];
            Arrays.setAll(arr, i -> SIZE - i);
            MergeSort.sort( arr);
            //System.out.println(Arrays.toString(arr));
        });
        executedTimed("Quicksort", () -> {
            int[] arr = new int[SIZE];
            Arrays.setAll(arr, i -> SIZE - i);
            Quicksort.sort( arr);
            //System.out.println(Arrays.toString(arr));
        });
        executedTimed("ShellSortMine", () -> {
            int[] arr = new int[SIZE];
            Arrays.setAll(arr, i -> SIZE - i);
            ShellSort.sortMine( arr);
            //System.out.println(Arrays.toString(arr));
        });

    }
}
