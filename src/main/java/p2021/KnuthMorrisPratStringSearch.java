package p2021;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * worst case: M+N
 */
public class KnuthMorrisPratStringSearch {

    public int search(String inString, String substring){
        int[][] dfa = new int[256][substring.length()];

        dfa[substring.charAt(0)][0] = 1;

        for(int previousPosition = 0, j = 1; j < substring.length(); j++){

            for(int c = 0; c< 256; c++){
                dfa[c][j] = dfa[c][previousPosition]; // previous for same char
            }

            char currentSubstringChar = substring.charAt(j);
            dfa[currentSubstringChar][j]=j+1; // same char same pos - advance

            previousPosition = dfa[currentSubstringChar][previousPosition]; // set previous to new values based on current subtring po
        }


        int i =0; int j =0;
        for(; i< inString.length() && j<substring.length(); i++){
            j = dfa[inString.charAt(i)][j];
        }
        if (j == substring.length()) return i-j;
        else return -1;
    }

    @Test
    public void t(){
        assertEquals(3, search("pe cand eram", "cand"));
        assertEquals(8, search("pe cand eram", "eram"));
        assertEquals(-1, search("pe cand eram", "cand1"));
    }

    @Test
    public void t1(){
        assertEquals(0, search("ababac", "ababac"));

    }
}
