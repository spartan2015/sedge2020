package p2021;

/**
 * RedBlackBST
 */
public class RedBlackBST<Key extends Comparable, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    public void deleteMin() {
        if (!isRed(root.left) && !isRed(root.right)) root.color = RED;
        root = deleteMin(root);
        if (root != null) root.color = BLACK;
    }

    public void deleteMax() {
        if (!isRed(root.left) && !isRed(root.right)) root.color = RED;
        root = deleteMax(root);
        if (root != null) root.color = BLACK;
    }

    public void delete(Key key) {
        if (!isRed(root.left) && !isRed(root.right)) root.color = RED;
        root = delete(root, key);
        if (!isEmpty()) root.color = BLACK;
    }

    private boolean isEmpty() {
        return root == null;
    }

    public void insert(Key key, Value value) {
        root = insert(root, key, value);
        root.color = BLACK;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    public Key min() {
        return min(root).key;
    }

    Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;

        x.color = h.color;
        h.color = RED;

        x.size = h.size;
        h.size = 1 + size(h.left) + size(h.right);

        return x;
    }

    Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;

        x.color = h.color;
        h.color = RED;

        x.size = h.size;
        h.size = 1 + size(h.left) + size(h.right);

        return x;
    }

    int size(Node x) {
        return x == null ? 0 : x.size;
    }

    Node insert(Node h, Key key, Value value) {
        if (h == null) return new Node(key, value, null, null, 1, RED);
        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            h.left = insert(h.left, key, value);
        } else if (cmp > 0) {
            h.right = insert(h.right, key, value);
        } else {
            h.value = value;
        }

        h = balance(h);

        h.size = 1 + size(h.left) + size(h.right);
        return h;
    }

    void flipColors(Node h) {
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color = RED;
    }

    private Node balance(Node h) {
        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);
        return h;
    }

    private Node moveRedLeft(Node h) { // Assuming that h is red and both h.left and h.left.left
// are black, make h.left or one of its children red.
        flipColors(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
        }
        return h;
    }

    private Node deleteMin(Node h) {
        if (h.left == null) return null;

        if (!isRed(h.left) && !isRed(h.left.left)) h = moveRedLeft(h);

        h.left = deleteMin(h.left);

        return balance(h);
    }

    private Node moveRedRight(Node h) { // Assuming that h is red and both h.right and h.right.left
// are black, make h.right or one of its children red.
        flipColors(h);
        if (!isRed(h.left.left)) h = rotateRight(h);
        return h;
    }

    private Node deleteMax(Node h) {
        if (isRed(h.left)) h = rotateRight(h);

        if (h.right == null) return null;

        if (!isRed(h.right) && !isRed(h.right.left)) h = moveRedRight(h);

        h.right = deleteMax(h.right);

        return balance(h);
    }

    private Node delete(Node h, Key key) {
        if (key.compareTo(h.key) < 0) {
            if (!isRed(h.left) && !isRed(h.left.left)) h = moveRedLeft(h);
            h.left = delete(h.left, key);
        } else {
            if (isRed(h.left)) h = rotateRight(h);
            if (key.compareTo(h.key) == 0 && (h.right == null)) return null;
            if (!isRed(h.right) && !isRed(h.right.left)) h = moveRedRight(h);
            if (key.compareTo(h.key) == 0) {
                h.value = get(h.right, min(h.right).key);
                h.key = min(h.right).key;
                h.right = deleteMin(h.right);
            } else h.right = delete(h.right, key);
        }
        return balance(h);
    }

    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.value;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    private class Node {
        Key key;
        Value value;
        Node left, right;
        int size;
        boolean color;

        public Node(Key key, Value value, Node left, Node right, int size, boolean color) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.size = size;
            this.color = color;
        }
    }
}
