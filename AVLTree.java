import java.util.ArrayList;

public class AVLTree {
    public static int insertCounter=0;
    public static int maxInsertCounter=0;
    public static int minInsertCounter=0;
    public static int searchCounter=0;
    public static int maxSearchCounter=0;
    public static int minSearchCounter=0;

    public static int sumSearch = 0;
    public static int sumInsert = 0;
    public static ArrayList<Integer> counterList = new ArrayList<>();;
    public AVLNode root;

    public AVLTree() {
        root = null;
    }

    public void insert(String term, String statement, double confidence) {
        insertCounter++;
        root = insert(term,statement, confidence, root);
    }

    public int getSize() {
        return getSize(root);
    }

    private int getSize(AVLNode t) {
        if (t == null) {
            return 0;
        }
        return 1 + getSize(t.left) + getSize(t.right);
    }



    private AVLNode insert(String term, String statement, double confidence, AVLNode t) {
        if (t == null) {
            t = new AVLNode(term, statement, confidence);
        } else if (term.compareTo(t.term) < 0) {
            insertCounter++;
            t.left = insert(term, statement, confidence, t.left);
            if (height(t.left) - height(t.right) == 2) {
                if (term.compareTo(t.left.term) < 0) {
                    t = rotateWithLeftChild(t);
                } else {
                    t = doubleWithLeftChild(t);
                }
            }
        } else if (term.compareTo(t.term) > 0) {
            insertCounter++;
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
        sumInsert += insertCounter;
        maxInsertCounter = Math.max(insertCounter, maxInsertCounter);
        minInsertCounter = Math.min(insertCounter, minInsertCounter);
        insertCounter = 0;
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
            sumSearch+= searchCounter;
            maxSearchCounter = Math.max(searchCounter, maxSearchCounter);
            minSearchCounter = Math.min(searchCounter, minSearchCounter);
            counterList.add(searchCounter);
            searchCounter = 0;
            return null;
        }
        else if (term.compareTo(t.term) < 0) {
            searchCounter++;
            return search(t.left, term);
        } else if (term.compareTo(t.term) > 0) {
            searchCounter++;
            return search(t.right, term);
        } else {
            sumSearch += searchCounter;
            maxSearchCounter = Math.max(searchCounter, maxSearchCounter);
            minSearchCounter = Math.min(searchCounter, minSearchCounter);
            counterList.add(searchCounter);
            searchCounter = 0;
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
