package datastructures;

import lists.Node;
import lists.Queue;

import java.util.Iterator;

/**
 * Purpose of LinkedListQueue is
 */
public class LinkedListQueue<T> implements Queue<T> {
    Node<T> first;
    Node<T> last;
    int count = 0;

    @Override
    public void enqueue(T t) {
        Node<T> tmp = new Node<>(t,null);
        if (last!=null) last.setNext(tmp);
        last = tmp;
        if (first == null) {
            first = last;
        }
        count++;
    }

    @Override
    public T dequeue() {
        Node<T> tmp = first;
        if (first != null) first = first.getNext();
        count--;
        if (isEmpty()) last = null;
        return tmp != null ? tmp.getItem() : null;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> start = first;
            @Override
            public boolean hasNext() {
                return start!=null;
            }

            @Override
            public T next() {
                Node<T> tmp = start;
                start = start.getNext();
                return tmp.getItem();
            }
        };
    }
}
