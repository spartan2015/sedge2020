package search;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

/**
 * Purpose of BinaryTreeSortedSearchTable is
 */
public class BinaryTreeSortedSearchTable<Key extends Comparable<Key>, Value> implements SortedSearchTable<Key, Value> {

    private Node start;

    @Override
    public void put(Key key, Value value) {
        //start = put(start, new Node(key, value));
        putIterative(new Node(key, value));
    }

    @Override
    public Value get(Key key) {
        return getIterative(start, key);
    }

    @Override
    public int size() {
        return start == null ? 0 : start.size();
    }

    @Override
    public Key min() {
        Node current = start;
        while (current != null && current.left != null) {
            current = current.left;
        }
        return current != null ? current.key : null;
    }

    @Override
    public Key max() {
        Node current = start;
        while (current != null && current.right != null) {
            current = current.right;
        }
        return current != null ? current.key : null;
    }

    @Override
    public Key floor(Key key) {
        return null;
    }

    @Override
    public Key ceiling(Key key) {
        return null;
    }

    @Override
    public int rank(Key key) {
        int parentSize = (start.left != null ? start.left.size() : 0) ;
        for (Node current = start; current != null; ) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                current = current.left;
                parentSize -= 1;
            } else if (cmp > 0) {
                parentSize += 1;
                current = current.right;
            } else {
                return parentSize + current.size() - (current.right != null ? current.right.size() : 0);
            }
        }
        return 0;
    }


    @Override
    public Key select(int k) {
        return null;
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        return null;
    }

    @Test
    public void t1() {
        BinaryTreeSortedSearchTable<Integer, Integer> bt = new BinaryTreeSortedSearchTable<>();
        int MAX = 4;
        for (int i = 0; i < MAX; i++) {
            bt.put(i, i);
        }
        for (int i = 0; i < MAX; i++) {
            assertEquals((Integer) i, bt.get(i));
        }

        for (int i = 0; i < MAX; i++) {
            assertEquals((Integer) i + 1, bt.rank(i));
        }


        System.out.println(bt.size());
    }

    @Test
    public void t2() {
        BinaryTreeSortedSearchTable<Integer, Integer> bt = new BinaryTreeSortedSearchTable<>();
        int MAX = 4;

        for (int i = 0; i < MAX; i++) {
            bt.put(MAX - i, MAX - i);
        }
        for (int i = 0; i < MAX; i++) {
            assertEquals((Integer) (MAX - i), bt.get(MAX - i));
        }

        System.out.println(bt.size());
    }

    Value get(Node node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return get(node.left, key);
        } else if (cmp > 0) {
            return get(node.right, key);
        } else {
            return node.value;
        }
    }

    Value getIterative(Node node, Key key) {
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                return node.value;
            }
        }
        return null;

    }

    Node put(Node current, Node newNode) {
        if (current == null) return newNode;
        int cmp = newNode.key.compareTo(current.key);
        if (cmp > 0) {
            current.right = put(current.right, newNode);
        } else if (cmp < 0) {
            current.left = put(current.left, newNode);
        } else {
            current.key = (Key) newNode.value;
        }
        return current;
    }

    void putIterative(Node newNode) {
        if (start == null) {
            start = newNode;
            return;
        }

        Node current = start;
        while (current != null) {
            int cmp = newNode.key.compareTo(current.key);
            if (cmp > 0) {
                if (current.right == null) {
                    current.right = newNode;
                    return;
                } else {
                    current = current.right;
                }
            } else if (cmp < 0) {
                if (current.left == null) {
                    current.left = newNode;
                    return;
                } else {
                    current = current.left;
                }
            } else {
                current.key = (Key) newNode.value;
                return;
            }
        }

    }

    public class Node {
        Key key;
        Value value;

        Node left;
        Node right;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }

        int size() {
            int size = 0;
            LinkedList<Node> list = new LinkedList<>();
            list.add(this);
            while (!list.isEmpty()) {
                Node node = list.removeFirst();
                size++;
                addNode(list, node.left);
                addNode(list, node.right);
            }
            return size;
        }

        private void addNode(LinkedList<Node> list, Node node) {
            if (node != null) {
                list.add(node);
            }
        }
    }
}
