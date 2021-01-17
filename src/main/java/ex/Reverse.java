package ex;

import org.junit.Test;

/**
 * Reverse
 */
public class Reverse<Item> {

    @Test
    public void t(){
        System.out.println(rev("1234"));
    }

    String rev(String s){
        if (s.length() <=1) return s;
        String a = s.substring(0, s.length()/2);
        String b = s.substring(s.length()/2);
        return rev(b) + rev(a);
    }

}
