package datastructures;

import java.util.Arrays;

/**
 * Purpose of Whitelist is
 */
public class Whitelist {
    int[] whitelist;

    public Whitelist(int[] whitelist) {
        this.whitelist = Arrays.copyOf(whitelist, whitelist.length);
        Arrays.sort(this.whitelist);
    }

    public static void main(String[] args) {

        System.out.println(true && false || true && false);
        Whitelist w = new Whitelist(new int[]{2, 4, 6});
        System.out.println(w.rank(2));
        System.out.println(w.rank(4));
        System.out.println(w.rank(6));
        System.out.println(w.rank(1));
        System.out.println(w.rank(3));
        System.out.println(w.rank(5));
        System.out.println(w.rank(7));
    }

    int rank(int key) { // binary search
        int lo = 0;
        int hi = whitelist.length - 1;
        while (lo <= hi) {
            int middle = lo + (hi - lo) / 2;
            if (whitelist[middle] == key) {
                return middle;
            }
            if (key < whitelist[middle]) {
                hi = middle - 1;
            } else {
                lo = middle + 1;
            }
        }
        return -1;
    }
}
