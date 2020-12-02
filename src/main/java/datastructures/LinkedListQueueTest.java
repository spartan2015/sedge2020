package datastructures;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
*
 */
public class LinkedListQueueTest {
    @Test
    public void t1() {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        for (int i : new int[]{1, 2, 3, 4, 5, 6}) queue.enqueue(i);

        for(int i : queue){
            System.out.println(i);
        }

        for (int i : new int[]{1, 2, 3, 4, 5, 6}) {
            assertEquals(i, queue.dequeue().intValue());
        }
    }
}
