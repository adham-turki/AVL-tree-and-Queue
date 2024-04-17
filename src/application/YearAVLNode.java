package application;

// Class for AVL tree node
 public class YearAVLNode {
    Years element;
    YearAVLNode left;
    YearAVLNode right;
    int height; // Height

    YearAVLNode(Years element) {
        this(element, null, null);
    }

    YearAVLNode(Years element, YearAVLNode left, YearAVLNode right) {
        this.element = element;
        this.left = left;
        this.right = right;
        this.height = 0;
    }
}