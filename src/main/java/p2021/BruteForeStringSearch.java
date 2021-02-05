package p2021;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * java impl
 * worst case MN
 * average M+N
 */
public class BruteForeStringSearch {

    public int search(String inString, String substring){
        for(int i = 0 ; i < inString.length() - substring.length(); i++){

            int j = 0;
            for(; j < substring.length(); j++){
                if (inString.charAt(i+j)!=substring.charAt(j)){
                    break;
                }
            }
            if (j == substring.length()){
                return i;
            }

        }
        return -1;
    }

    public int searchAlternative(String inString, String substring){
        int i =0, j =0;
        for(; i < inString.length() && j < substring.length(); i++){
            if (inString.charAt(i) == substring.charAt(j)) j++;
            else{
                i-=j;
                j=0;
            }
        }
        if (j == substring.length()) return i-j;
        else return -1;
    }

    @Test
    public void t(){
        assertEquals(3, search("pe cand eram", "cand"));
        assertEquals(-1, search("pe cand eram", "cand1"));
    }

    @Test
    public void t1(){
        assertEquals(3, searchAlternative("pe cand eram", "cand"));
        assertEquals(-1, searchAlternative("pe cand eram", "cand1"));
    }
}
