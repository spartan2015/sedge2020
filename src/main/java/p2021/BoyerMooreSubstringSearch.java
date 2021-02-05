package p2021;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * M+N
 * average N/M
 * considered faster than any
 */
public class BoyerMooreSubstringSearch {

    public int search(String inString, String substring) {
        int[] slideRight = new int[256];
        for (int i = 0; i < substring.length(); i++) {
            slideRight[substring.charAt(i)] = substring.lastIndexOf(substring.charAt(i));
        }

        for (int i = substring.length() - 1; i < inString.length(); ) {
            int j = substring.length() - 1;
            for (; j >=0; j--) {
                if (inString.charAt(i - substring.length()  + 1 + j) != substring.charAt(j)) {
                    break;
                }
            }
            if (j == -1) {
                return i-substring.length()+1;
            }
            i = i + substring.length() - 1 - slideRight[inString.charAt(i)];
        }
        return -1;
    }

    @Test
    public void t() {
        assertEquals(15, search("findinahaystackneedle","needle"));
    }

}
