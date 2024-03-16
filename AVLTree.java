public class AVLTree {
    public AVLNode root;

    public AVLTree() {
        root = null;
    }

    public void insert(String term, String statement, double confidence) {
        root = insert(term,statement, confidence, root);
    }

    private AVLNode insert(String term, String statement, double confidence, AVLNode t) {
        if (t == null) {
            t = new AVLNode(term, statement, confidence);
        } else if (term.compareTo(t.term) < 0) {
            t.left = insert(term, statement, confidence, t.left);
            if (height(t.left) - height(t.right) == 2) {
                if (term.compareTo(t.left.term) < 0) {
                    t = rotateWithLeftChild(t);
                } else {
                    t = doubleWithLeftChild(t);
                }
            }
        } else if (term.compareTo(t.term) > 0) {
            t.right = insert(term, statement, confidence, t.right);
            if (height(t.right) - height(t.left) == 2) {
                if (term.compareTo(t.right.term) > 0) {
                    t = rotateWithRightChild(t);
                } else {
                    t = doubleWithRightChild(t);
                }
            }
        }
        t.height = Math.max(height(t.left), height(t.right)) + 1;
        return t;
    }

    private int height(AVLNode t) {
        return t == null ? -1 : t.height;
    }

    private AVLNode rotateWithLeftChild(AVLNode k2) {
        AVLNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;
        return k1;
    }

    private AVLNode rotateWithRightChild(AVLNode k1) {
        AVLNode k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
        k2.height = Math.max(height(k2.right), k1.height) + 1;
        return k2;
    }

    private AVLNode doubleWithLeftChild(AVLNode k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    private AVLNode doubleWithRightChild(AVLNode k1) {
        k1.right = rotateWithLeftChild(k1.right);
        return rotateWithRightChild(k1);
    }

    public AVLNode search(AVLNode t, String term) {
        if (t == null) {
            return null;
        }
        if (term.compareTo(t.term) < 0) {
            return search(t.left, term);
        } else if (term.compareTo(t.term) > 0) {
            return search(t.right, term);
        } else {
            return t;
        }
    }


}

class AVLNode {
    String term;
    String statement;
    double confidence;
    AVLNode left;
    AVLNode right;
    int height;

    public AVLNode(String term, String statement, double confidence) {
        this(term, statement, confidence, null, null);
    }

    public AVLNode(String term,String statement, double confidence, AVLNode left, AVLNode right) {
        this.term = term;
        this.statement = statement;
        this.confidence = confidence;
        this.left = left;
        this.right = right;
        height = 0;
    }
}
