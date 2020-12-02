package datastructures;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Purpose of FixedCapacityStackTest is
 */
public class ResizingCapacityStackTest {

    @Test
    public void t1(){
        ResizingCapacityStack<Integer> stack = new ResizingCapacityStack<>(10);
        for(int i =0; i < 20; i++){
            stack.push(i);
        }

        int x = 19;
        for(int i : stack){
            assertEquals(x--, i);
        }

        for(int i =0; i < 20; i++){
            assertEquals(19-i, (int)stack.pop());
        }
    }
}
