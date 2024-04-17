package application;

// Class for AVL tree node
 public class MonthAVLNode {
    Months element;
    MonthAVLNode left;
    MonthAVLNode right;
    int height; // Height

    MonthAVLNode(Months element) {
        this(element, null, null);
    }

    MonthAVLNode(Months element, MonthAVLNode left, MonthAVLNode right) {
        this.element = element;
        this.left = left;
        this.right = right;
        this.height = 0;
    }
}