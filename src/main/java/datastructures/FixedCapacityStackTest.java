package datastructures;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Purpose of FixedCapacityStackTest is
 */
public class FixedCapacityStackTest {

    @Test
    public void t1(){
        FixedCapacityStack<Integer> stack = new FixedCapacityStack<>(10);
        for(int i =0; i < 10; i++){
            stack.push(i);
        }

        int x = 9;
        for(int i : stack){
            assertEquals(x--, i);
        }

        for(int i =0; i < 10; i++){
            assertEquals(9-i, (int)stack.pop());
        }
    }
}
