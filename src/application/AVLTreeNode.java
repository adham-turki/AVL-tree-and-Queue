package application;

// Class for AVL tree node
 public class AVLTreeNode {
    Days element;
    AVLTreeNode left;
    AVLTreeNode right;
    int height; // Height

    AVLTreeNode(Days element) {
        this(element, null, null);
    }

    AVLTreeNode(Days element, AVLTreeNode left, AVLTreeNode right) {
        this.element = element;
        this.left = left;
        this.right = right;
        this.height = 0;
    }
}