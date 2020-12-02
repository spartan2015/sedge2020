package datastructures;

import lists.Stack;

import java.util.Iterator;

/**
 * Purpose of FixedCapacityStack is
 */
public class ResizingCapacityStack<T> implements Stack<T> {

    private T[] array;
    private int currentIndex;

    public ResizingCapacityStack(int capacity) {
        array = (T[]) new Object[capacity];
    }

    @Override
    public void push(T t) {
        if (currentIndex == array.length) {
            resize(array.length * 2);
        }
        array[currentIndex++] = t;
    }

    @Override
    public T pop() {
        if (currentIndex > 0 && currentIndex == array.length / 4) resize(array.length / 2);
        if (isEmpty()) {
            return null;
        } else {
            T tmp = array[--currentIndex];
            array[currentIndex] = null;
            return tmp;
        }
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
                return (T) array[--N];
            }
        };
    }

    public void resize(int max) {
        T[] tmp = (T[]) new Object[max];
        for (int i = 0; i < currentIndex; i++)
            tmp[i] = array[i];
        this.array = tmp;
    }
}
