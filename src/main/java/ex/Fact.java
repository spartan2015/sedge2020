package ex;

import org.junit.Test;

/**
 * Fact
 */
public class Fact {

    @Test
    public void t(){
        System.out.println(fact(3));
        System.out.println(fact(4));
        System.out.println(fact(5));
        System.out.println(fact(6));
    }

    int fact(int N){
        if (N == 1) return 1;
        return N * fact(N-1);
    }

}
