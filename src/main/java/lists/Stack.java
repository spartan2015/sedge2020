package lists;

/**
 * Purpose of Stack is
 */
public interface Stack<E> extends Iterable<E>{
        void push(E e);
        E pop();
        int size();
        boolean isEmpty();
}
