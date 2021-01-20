package p2021;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * BinaryTreeST
 */
public class BinaryTreeST<Key extends Comparable> {

    static class Node<Key extends Comparable>{
        Key key;
        Object value;
        int size;
        Node<Key> left;
        Node<Key> right;

        public Node(Key key, Object value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    Node<Key> root;

    public void insert(Key key, Object value){
        root = insert(root, key, value);
    }

    private Node<Key> insert(Node<Key> node, Key key, Object value) {
        if (node == null){
            return new Node<>(key, value, 1);
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0){
            node.left = insert(node.left, key, value);
        }else if (cmp > 0){
            node.right = insert(node.right, key,value);
        }else{
            node.value = value;
        }
        node.size = size(node.left) + 1 + size(node.right);

        return node;
    }

    public Key floor(Key given){
        Node<Key> floor = floor(root, given);
        if (floor == null) return null;
        return floor.key;
    }

    public Node<Key> floor(Node<Key> start, Key given){
        for(Node<Key> current = start; current!=null;){
            int cmp = given.compareTo(current);
            if (cmp == 0){
                return current;
            }else if (cmp < 0){
                current = current.left;
            } else { // first < given - candidate
                Node tryRight = floor(current.right, given);
                return tryRight!=null ? tryRight : current;
            }
        }
        return null;
    }

    public Key ceiling(Key given){
        Node<Key> ceiling = ceiling(root, given);
        return ceiling!=null ? ceiling.key : null;
    }

    Node<Key> ceiling(Node<Key> start, Key given){
        for(Node<Key> current = start; current!=null;){
            int cmp = given.compareTo(current);
            if (cmp == 0){
                return current;
            }else if (cmp > 0){
                current = current.right;
            }else { // current is greater - is a possibily
                Node<Key> tryLeft = ceiling(current.left, given);
                return tryLeft!=null ? tryLeft : current;
            }
        }
        return null;
    }

    Node<Key> select(int k) {
        return select(root, k, 0);
    }

    Node<Key> select(Node<Key> start, int k, int add){
        for(Node<Key> current = start; current!=null; ){
            int currentK = current.size - size(current.right) + add;
            if (currentK == k){
                return current;
            }else if (currentK > k){
                add = 0;
                current = current.left;
            }else{
                add = currentK;
                current = current.right;
            }
        }
        return null;
    }

    int size(Node<Key> node){
        return node!=null ? node.size: 0;
    }

    @Test
    public void t1(){
        BinaryTreeST<Integer> st = new BinaryTreeST<>();

        st.insert(10,10);;

        assertEquals(Integer.valueOf(10), st.select(1).key);

        st.insert(6,6);
        assertEquals(Integer.valueOf(6), st.select(1).key);

        st.insert(2,2);
        assertEquals(Integer.valueOf(2), st.select(1).key);

        st.insert(16,16);
        assertEquals(Integer.valueOf(16), st.select(4).key);
        assertEquals(Integer.valueOf(10), st.select(3).key);
        assertEquals(Integer.valueOf(6), st.select(2).key);
        assertEquals(Integer.valueOf(2), st.select(1).key);
    }

}
