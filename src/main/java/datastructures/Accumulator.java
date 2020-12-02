package datastructures;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Purpose of Accumulator is
 */
public class Accumulator {
    private int count;
    private double sum;

    void addDataValue(double value) {
        count++;
        sum += value;
    }

    double mean() {
        return sum / count;
    }

    public static void main(String[] args) {
        Accumulator a = new Accumulator();
        SecureRandom secureRandom = new SecureRandom();
        for(int i = 0; i < 100; i++){
            a.addDataValue(secureRandom.nextInt(100));
        }

        System.out.println(a.mean());
    }
}
