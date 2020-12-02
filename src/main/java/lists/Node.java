package lists;

/**
 * Purpose of Node is
 */
public class Node<T> {
    T item;
    Node<T> next;

    public Node(T item, Node<T> next) {
        this.item = item;
        this.next = next;
    }

    public static Node<Integer> toNodes(int[] array) {
        Node<Integer> first = null;
        for (int i = array.length - 1; i >= 0; i--) {
            first = new Node(array[i], first);
        }
        return first;
    }

    public static void printNodes(Node<?> start) {
        for (Node node = start; node != null; node = node.next) {
            System.out.println(node);
        }
        System.out.println();
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "item=" + item +
                '}';
    }

}
