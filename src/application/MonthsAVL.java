package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;

public class MonthsAVL {

	private MonthAVLNode root;

	public MonthsAVL() {
		root = null;
	}

	public MonthAVLNode getRoot() {
		return root;
	}

	// Public method to check if an element is in the AVL tree
	public boolean contains(Months element) {
		// Start the search from the root
		return contains(element, root);
	}

	private boolean contains(Months e, MonthAVLNode current) {
		if (current == null)
			return false; // Not found, empty tree.
		else if (e.getMonth() < current.element.getMonth()) // if smaller than root.
			return contains(e, current.left); // Search left subtree
		else if (e.getMonth() > current.element.getMonth()) // if larger than root.
			return contains(e, current.right); // Search right subtree
		return true; // found .
	}

	// Public method to find a specific element in the AVL tree
	public MonthAVLNode find(Months element) {
		// Start the search from the root
		return find(element, root);
	}

	private MonthAVLNode find(Months element, MonthAVLNode current) {
		if (current == null)
			return null;
		if (element.getMonth() < current.element.getMonth())
			return find(element, current.left);
		else if (element.getMonth() > current.element.getMonth())
			return find(element, current.right);
		else
			return current;
	}

	// Public method to insert an element into the AVL tree
	public void insert(Months element) {
		// Start the insertion from the root
		root = insert(element, root);
	}

	private MonthAVLNode insert(Months element, MonthAVLNode current) {
		if (current == null)
			return new MonthAVLNode(element); // create one node tree
		else {
			if (element.getMonth() < current.element.getMonth())
				current.left = insert(element, current.left);
			else
				current.right = insert(element, current.right);
		}
		updateHeight(current);
		return balance(current);
	}

	// Public method to remove an element from the AVL tree
	public void remove(Months element) {
		// Start the removal from the root
		root = remove(element, root);
	}

	private MonthAVLNode remove(Months e, MonthAVLNode current) {
		if (current == null)
			return null; // Item not found, Empty tree

		if (e.getMonth() < current.element.getMonth())
			current.left = remove(e, current.left);
		else if (e.getMonth() > current.element.getMonth())
			current.right = remove(e, current.right);
		else { // found element to be deleted
			if (current.left != null && current.right != null) {// two children
				/* Replace with smallest in right subtree */
				current.element = findMin(current.right).element;
				current.right = remove(current.element, current.right);
			} else {// one or zero child
				current = (current.left != null) ? current.left : current.right;
			}
		}
		updateHeight(current);
		return balance(current);
	}

	// Public method to find the minimum element in the AVL tree
	public MonthAVLNode findMin() {
		// Start the search from the root
		return findMin(root);
	}

	private MonthAVLNode findMin(MonthAVLNode current) {
		if (current == null)
			return null;
		else if (current.left == null)
			return current;
		else
			return findMin(current.left); // keep going to the left
	}

	// Public method to find the maximum element in the AVL tree
	public MonthAVLNode findMax() {
		// Start the search from the root
		return findMax(root);
	}

	private MonthAVLNode findMax(MonthAVLNode current) {
		if (current == null)
			return null;
		else if (current.right == null)
			return current;
		else
			return findMax(current.right); // keep going to the right
	}

	// Public method to rotate a binary tree node with the left child (single rotate
	// to the right)
	public void rotateWithLeftChild() {
		root = rotateWithLeftChild(root);
	}

	// Public method to rotate a binary tree node with the right child (single
	// rotate to the left)
	public void rotateWithRightChild() {
		root = rotateWithRightChild(root);
	}

	// Public method to perform double rotation with left child
	public void doubleWithLeftChild() {
		root = doubleWithLeftChild(root);
	}

	// Public method to perform double rotation with right child
	public void doubleWithRightChild() {
		root = doubleWithRightChild(root);
	}

	// Private method to rotate binary tree node with left child (single rotate to
	// right)
	private MonthAVLNode rotateWithLeftChild(MonthAVLNode k2) {
		if (k2 == null || k2.left == null) {
			return k2; // Return k2 if it or its left child is null
		}
		MonthAVLNode k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
		k1.height = Math.max(height(k1.left), k2.height) + 1;
		return k1;
	}

	// Private method to rotate binary tree node with right child (single rotate to
	// left)
	private MonthAVLNode rotateWithRightChild(MonthAVLNode k1) {
		if (k1 == null || k1.right == null) {
			return k1; // Return k1 if it or its right child is null
		}

		MonthAVLNode k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;
		k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
		k2.height = Math.max(height(k2.right), k1.height) + 1;
		return k2;
	}

	// Private method to perform double rotation with left child
	private MonthAVLNode doubleWithLeftChild(MonthAVLNode k3) {
		if (k3 == null || k3.left == null) {
			return k3; // Return k3 if it or its left child is null
		}
		k3.left = rotateWithRightChild(k3.left);
		return rotateWithLeftChild(k3);
	}

	// Private method to perform double rotation with right child
	private MonthAVLNode doubleWithRightChild(MonthAVLNode k1) {
		if (k1 == null || k1.right == null) {
			return k1; // Return k1 if it or its right child is null
		}
		k1.right = rotateWithLeftChild(k1.right);
		return rotateWithRightChild(k1);
	}

	// Private method to update the height of a node
	private void updateHeight(MonthAVLNode node) {
		if (node != null) {
			node.height = Math.max(height(node.left), height(node.right)) + 1;
		}
	}

	// Private method to calculate the height of a node
	private int height(MonthAVLNode node) {
		return (node == null) ? -1 : node.height;
	}

	// Private method to calculate the balance factor of a node
	private int balanceFactor(MonthAVLNode node) {
		return (node == null) ? 0 : height(node.left) - height(node.right);
	}

	// Private method to balance a node
	private MonthAVLNode balance(MonthAVLNode node) {
		if (node == null) {
			return null;
		}

		// Left heavy
		if (balanceFactor(node) > 1) {
			if (balanceFactor(node.left) >= 0) {
				return rotateWithLeftChild(node);
			} else {
				return rotateWithRightChild(node);
			}
		}
		// Right heavy
		else if (balanceFactor(node) < -1) {
			if (balanceFactor(node.right) <= 0) {
				return rotateWithRightChild(node);
			} else {
				// Right-Left case
				return doubleWithRightChild(node);
			}
		}

		return node;
	}
	//print method to print the tree in order
	public void printInOrder() {
		System.out.println("AVL Tree Elements (In-Order):");
		inOrderTraversal(root);
	}
	//print method to print the tree in order
	private void inOrderTraversal(MonthAVLNode node) {
		if (node != null) {
			inOrderTraversal(node.left);
			System.out.println(node.element);
			inOrderTraversal(node.right);
		}
	}
	//print method to print the tree in reverse order
	public void printReverseInOrder() {
		System.out.println("AVL Tree Elements (Reverse In-Order):");
		printReverseInOrder(root);
	}
	//print method to print the tree in reverse order
	private void printReverseInOrder(MonthAVLNode node) {
		if (node != null) {
			printReverseInOrder(node.right);
			System.out.println(node.element);
			printReverseInOrder(node.left);
		}
	}
	//in order traverse the tree in the table view
	public void inOrderTraversalDaysData(ObservableList<Days> daysData) {
		inOrderTraversalDaysData(root, daysData);
	}
	//in order traverse the tree in the table view
	private void inOrderTraversalDaysData(MonthAVLNode node, ObservableList<Days> daysData) {
		if (node != null) {
			if (node.left != null)
				inOrderTraversalDaysData(node.left, daysData);
			DaysAVL daysAVL = node.element.getDaysAVL();
			if (daysAVL != null) {
				daysAVL.inOrderTraversalDaysData(daysData);
			}
			if (node.right != null)
				inOrderTraversalDaysData(node.right, daysData);
		}
	}

	// .......................... InOrder
	//method to print a specific day in order
	public void inOrderAppendsDaysData(TextArea textArea, Days day, ArrayList<Days> list) {
		inOrderAppendsDaysData(root, textArea, day, list);
	}
	//method to print a specific day in order
	private void inOrderAppendsDaysData(MonthAVLNode node, TextArea textArea, Days day, ArrayList<Days> list) {
		if (node != null) {
			if (node.left != null)
				inOrderAppendsDaysData(node.left, textArea, day, list);
			DaysAVL daysAVL = node.element.getDaysAVL();
			if (daysAVL != null) {
				daysAVL.inOrderAppendsDaysData(textArea, day, list);
			}
			if (node.right != null)
				inOrderAppendsDaysData(node.right, textArea, day, list);
		}
	}
	//method to print a specific month in order
	public void inOrderAppendsMonthsData(TextArea textArea, Months month, ArrayList<Days> list) {
		inOrderAppendsMonthsData(root, textArea, month, list);
	}
	//method to print a specific month in order
	private void inOrderAppendsMonthsData(MonthAVLNode node, TextArea textArea, Months month, ArrayList<Days> list) {
		if (node != null) {
			if (node.left != null)
				inOrderAppendsMonthsData(node.left, textArea, month, list);
			if (node.element.getMonth() == month.getMonth()) {
				DaysAVL daysAVL = node.element.getDaysAVL();
				if (daysAVL != null) {
					daysAVL.inOrderAppendsMonthsData(textArea, list);
				}
			}
			if (node.right != null)
				inOrderAppendsMonthsData(node.right, textArea, month, list);
		}
	}
	//method to print a specific year in order
	public void inOrderAppendsYearsData(TextArea textArea, ArrayList<Days> list) {
		inOrderAppendsYearsData(root, textArea, list);
	}
	//method to print a specific year in order
	private void inOrderAppendsYearsData(MonthAVLNode node, TextArea textArea, ArrayList<Days> list) {
		if (node != null) {
			if (node.left != null)
				inOrderAppendsYearsData(node.left, textArea, list);
			DaysAVL daysAVL = node.element.getDaysAVL();
			if (daysAVL != null) {
				daysAVL.inOrderAppendsMonthsData(textArea, list);
			}
			if (node.right != null)
				inOrderAppendsYearsData(node.right, textArea, list);
		}
	}

	// .............................. PreOrder
	//method to print a specific day preOrder
	public void preOrderAppendsDaysData(TextArea textArea, Days day, ArrayList<Days> list) {
		preOrderAppendsDaysData(root, textArea, day, list);
	}
	//method to print a specific day preOrder
	private void preOrderAppendsDaysData(MonthAVLNode node, TextArea textArea, Days day, ArrayList<Days> list) {
		if (node != null) {
			DaysAVL daysAVL = node.element.getDaysAVL();
			if (daysAVL != null) {
				daysAVL.preOrderAppendsDaysData(textArea, day, list);
			}
			if (node.left != null)
				preOrderAppendsDaysData(node.left, textArea, day, list);
			if (node.right != null)
				preOrderAppendsDaysData(node.right, textArea, day, list);
		}
	}
	//method to print a specific month preOrder
	public void preOrderAppendsMonthsData(TextArea textArea, Months month, ArrayList<Days> list) {
		preOrderAppendsMonthsData(root, textArea, month, list);
	}
	//method to print a specific month preOrder
	private void preOrderAppendsMonthsData(MonthAVLNode node, TextArea textArea, Months month, ArrayList<Days> list) {
		if (node != null) {
			if (node.element.getMonth() == month.getMonth()) {
				DaysAVL daysAVL = node.element.getDaysAVL();
				if (daysAVL != null) {
					daysAVL.preOrderAppendsMonthsData(textArea, list);
				}
			}
			if (node.left != null)
				preOrderAppendsMonthsData(node.left, textArea, month, list);
			if (node.right != null)
				preOrderAppendsMonthsData(node.right, textArea, month, list);
		}
	}
	//method to print a specific year preOrder
	public void preOrderAppendsYearsData(TextArea textArea, ArrayList<Days> list) {
		preOrderAppendsYearsData(root, textArea, list);
	}
	//method to print a specific year preOrder
	private void preOrderAppendsYearsData(MonthAVLNode node, TextArea textArea, ArrayList<Days> list) {
		if (node != null) {
			DaysAVL daysAVL = node.element.getDaysAVL();
			if (daysAVL != null) {
				daysAVL.preOrderAppendsMonthsData(textArea, list);
			}
			if (node.left != null)
				preOrderAppendsYearsData(node.left, textArea, list);
			if (node.right != null)
				preOrderAppendsYearsData(node.right, textArea, list);
		}
	}

	// .............................. PostOrder
	
	//method to print a specific day in PostOrder
	public void postOrderAppendsDaysData(TextArea textArea, Days day, ArrayList<Days> list) {
		postOrderAppendsDaysData(root, textArea, day, list);
	}
	//method to print a specific day in PostOrder
	private void postOrderAppendsDaysData(MonthAVLNode node, TextArea textArea, Days day, ArrayList<Days> list) {
		if (node != null) {
			if (node.left != null)
				postOrderAppendsDaysData(node.left, textArea, day, list);
			if (node.right != null)
				postOrderAppendsDaysData(node.right, textArea, day, list);
			DaysAVL daysAVL = node.element.getDaysAVL();
			if (daysAVL != null) {
				daysAVL.postOrderAppendsDaysData(textArea, day, list);
			}
		}
	}
	//method to print a specific month in PostOrder
	public void postOrderAppendsMonthsData(TextArea textArea, Months month, ArrayList<Days> list) {
		postOrderAppendsMonthsData(root, textArea, month, list);
	}
	//method to print a specific month in PostOrder
	private void postOrderAppendsMonthsData(MonthAVLNode node, TextArea textArea, Months month, ArrayList<Days> list) {
		if (node != null) {
			if (node.left != null)
				postOrderAppendsMonthsData(node.left, textArea, month, list);
			if (node.right != null)
				postOrderAppendsMonthsData(node.right, textArea, month, list);
			if (node.element.getMonth() == month.getMonth()) {
				DaysAVL daysAVL = node.element.getDaysAVL();
				if (daysAVL != null) {
					daysAVL.postOrderAppendsMonthsData(textArea, list);
				}
			}
		}
	}
	//method to print a specific year in PostOrder
	public void postOrderAppendsYearsData(TextArea textArea, ArrayList<Days> list) {
		postOrderAppendsYearsData(root, textArea, list);
	}
	//method to print a specific year in PostOrder
	private void postOrderAppendsYearsData(MonthAVLNode node, TextArea textArea, ArrayList<Days> list) {
		if (node != null) {
			if (node.left != null)
				postOrderAppendsYearsData(node.left, textArea, list);
			if (node.right != null)
				postOrderAppendsYearsData(node.right, textArea, list);
			DaysAVL daysAVL = node.element.getDaysAVL();
			if (daysAVL != null) {
				daysAVL.postOrderAppendsMonthsData(textArea, list);
			}
		}
	}

	// ............................... LevelByLevel

	// print a specific day level by level Order
	public void levelOrderAppendsDaysData(TextArea textArea, Days day, ArrayList<Days> list) {
		if (root == null)
			return;

		MyQueue queue = new MyQueue();
		queue.enqueue(root);

		while (!queue.isEmpty()) {
			MonthAVLNode tempNode = (MonthAVLNode) queue.dequeue();
			DaysAVL daysAVL = tempNode.element.getDaysAVL();
			if (daysAVL != null) {
				daysAVL.levelOrderAppendsDaysData(textArea, day, list);

			}

			if (tempNode.left != null)
				queue.enqueue(tempNode.left);

			if (tempNode.right != null)
				queue.enqueue(tempNode.right);
		}
	}

	// print a specific month level by level Order
	public void levelOrderAppendsMonthsData(TextArea textArea, Months month, ArrayList<Days> list) {
		if (root == null)
			return;

		MyQueue queue = new MyQueue();
		queue.enqueue(root);

		while (!queue.isEmpty()) {
			MonthAVLNode tempNode = (MonthAVLNode) queue.dequeue();
			if (tempNode.element.getMonth() == month.getMonth()) {
				DaysAVL daysAVL = tempNode.element.getDaysAVL();
				if (daysAVL != null) {
					daysAVL.levelOrderAppendsAllData(textArea, list);

				}
			}

			if (tempNode.left != null)
				queue.enqueue(tempNode.left);

			if (tempNode.right != null)
				queue.enqueue(tempNode.right);
		}
	}

	// method to print all the data level by level
	public void levelOrderAppendsAllData(TextArea textArea, ArrayList<Days> list) {
		if (root == null)
			return;

		MyQueue queue = new MyQueue();
		queue.enqueue(root);

		while (!queue.isEmpty()) {
			MonthAVLNode tempNode = (MonthAVLNode) queue.dequeue();
			DaysAVL DayssAVL = tempNode.element.getDaysAVL();
			if (DayssAVL != null) {
				DayssAVL.levelOrderAppendsAllData(textArea, list);

			}

			if (tempNode.left != null)
				queue.enqueue(tempNode.left);

			if (tempNode.right != null)
				queue.enqueue(tempNode.right);
		}
	}
	
	//....................... save

		//method to save the data in file
		public void save(File file) {
			save(root,file);
	       
	    }
		//method to save the data in file
		private void save(MonthAVLNode node,File file) {
			if (node != null) {
				if (node.left != null)
					save(node.left,file);
				DaysAVL daysAvl = node.element.getDaysAVL();
				if (daysAvl != null) {
					daysAvl.save(file);
				}
				if (node.right != null)
					save(node.right,file);
				}
		}

}