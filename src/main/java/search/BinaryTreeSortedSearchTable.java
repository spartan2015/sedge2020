package search;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
        Node candidate = null;
        for (Node current = start; current != null; ) {
            int lookedForIs = key.compareTo(current.key);
            if (lookedForIs < 0) {
                current = current.left;
            } else if (lookedForIs > 0) {
                candidate = current;
                current = current.right;
            } else {
                return current.key;
            }
        }
        return candidate != null ? candidate.key : null;
    }

    @Override
    public Key ceiling(Key key) {
        return null;
    }

    @Override
    public int rank(Key key) {
        int parentSize = (start.left != null ? start.left.size() : 0);
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

    @Test
    public void floorTest() {
        BinaryTreeSortedSearchTable<Integer, Integer> bt = new BinaryTreeSortedSearchTable<>();

        bt.put(8, 8);
        bt.put(4, 4);
        bt.put(6, 6);
        bt.put(2, 2);
        bt.put(1, 1);
        bt.put(3, 3);

        bt.put(12, 12);
        bt.put(10, 10);
        bt.put(9, 9);
        //bt.put(11, 11);
        bt.put(14, 14);

        assertEquals(Integer.valueOf(8), bt.floor(8));
        assertEquals(Integer.valueOf(4), bt.floor(4));
        assertEquals(Integer.valueOf(6), bt.floor(7));
        assertEquals(Integer.valueOf(4), bt.floor(5));
        assertEquals(Integer.valueOf(1), bt.floor(1));
        assertEquals(Integer.valueOf(2), bt.floor(2));
        assertEquals(Integer.valueOf(3), bt.floor(3));

        assertEquals(Integer.valueOf(12), bt.floor(12));
        assertEquals(Integer.valueOf(14), bt.floor(15));
        assertEquals(Integer.valueOf(10), bt.floor(10));
        assertEquals(Integer.valueOf(10), bt.floor(11));
        assertNull(bt.floor(0));
    }

    @Override
    public void delete(Key key) {
        // first find the the key
        boolean isLeft = false;
        for (Node current = start, parent = null; current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp == 0) {
                Node delNode = current;

                Node predecessor = delNode.left;
                Node predecessorParent = null;
                while (predecessor.right != null) {
                    predecessorParent = predecessor;
                    predecessor = predecessor.right;
                }
                if (predecessor != null) {
                    if (isLeft) {
                        if (parent == null) {
                            start = delNode.right;
                        } else {
                            parent.left = predecessor;
                        }
                        predecessorParent.right = predecessor.left;
                        predecessor.right = delNode.right;
                        predecessor.left = predecessorParent;
                    } else {
                        if (parent == null) {
                            start = delNode.right;
                        } else {
                            parent.right = predecessor;
                        }
                        predecessorParent.left = predecessor.right;
                        predecessor.left = delNode.left;
                        predecessor.right = predecessorParent;
                    }
                } else {
                    if (parent == null) {
                        start = delNode.right;
                    } else {
                        if (isLeft) {
                            parent.left = current.right;
                        } else {
                            parent.right = current.left;
                        }
                    }
                }
            } else if (cmp < 0) {
                isLeft = true;
                current = current.left;

            } else if (cmp > 0) {
                isLeft = false;
                current = current.right;
            }

            parent = current;
        }
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
