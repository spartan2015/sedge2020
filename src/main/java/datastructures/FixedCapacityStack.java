package datastructures;

import lists.Stack;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;

/**
 * Purpose of FixedCapacityStack is
 */
public class FixedCapacityStack<T> implements Stack<T> {

    private T[] array;
    private int currentIndex;

    public FixedCapacityStack(int capacity) {
        array = (T[])new Object[capacity];
    }

    @Override
    public void push(T t) {
        if (currentIndex < array.length) {
            array[currentIndex] = t;
            if (currentIndex < array.length) {
                currentIndex++;
            }
        }
    }

    @Override
    public T pop() {
        return isEmpty() ? null : (T)array[--currentIndex];
    }

    @Override
    public int size() {
        return currentIndex;
    }

    @Override
    public boolean isEmpty() {
        return currentIndex == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int N = currentIndex;
            @Override
            public boolean hasNext() {
                return N > 0;
            }

            @Override
            public T next() {
                return (T)array[--N];
            }
        };
    }

}
