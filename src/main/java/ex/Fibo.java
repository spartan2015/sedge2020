package ex;

import org.junit.Test;

/**
 * Fibo
 */
public class Fibo {

    @Test
    public void t(){
        for(int i = 0; i < 10; i++){
            System.out.println(fibo(i));
        }
    }

    int fibo(int N){
        int n1 = 0;
        int n2 = 1;

        if (N == 0) return 0;
        if (N == 1) return 1;

        int currentN = 1;
        while(currentN < N){
            int tmp = n2;
            n2 = n1 + n2;
            n1 = tmp;
            currentN++;
        }
        return n2;
    }
}
