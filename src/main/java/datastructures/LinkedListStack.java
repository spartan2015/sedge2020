package datastructures;

import lists.Node;
import lists.Stack;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;

/**
 * Purpose of LinkedList is
 */
public class LinkedListStack<T> implements Stack<T> {

    Node<T> first;
    int count;

    @Test
    public void t1() {
        Node<Integer> n3 = new Node<>(3, null);
        Node<Integer> n2 = new Node<>(2, n3);
        Node<Integer> n1 = new Node<>(1, n2);

        Node.printNodes(n1);

        Node.printNodes(Node.toNodes(new int[]{1,2,3,4,5,6,7,8}));
    }

    @Test
    public void t2(){
        LinkedListStack<Integer> stack = new LinkedListStack<>();
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

    @Override
    public void push(T t) {
        if (first == null) {
            first = new Node(t,null);
        }else{
            Node n = new Node(t,first);
            first = n;
        }
        count++;
    }

    @Override
    public T pop() {
        if (first == null) return null;
        Node<T> result = first;
        first = first.getNext();
        count--;
        return result.getItem();
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count==0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> next = first;
            @Override
            public boolean hasNext() {
                return next!=null;
            }

            @Override
            public T next() {
                Node<T> tmp = next;
                next = next.getNext();
                return tmp.getItem();
            }
        };
    }
}
