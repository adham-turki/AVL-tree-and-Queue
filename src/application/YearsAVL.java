package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import javax.xml.validation.Validator;

import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;

public class YearsAVL {
	// root
	private YearAVLNode root;

	// constructor with arguments
	public YearsAVL(File file) {
		root = null;
		readFromFile(file);
	}

	// constructor without arguments
	public YearsAVL() {
		root = null;
	}

	// get root method
	public YearAVLNode getRoot() {
		return root;
	}

	// Public method to check if an element is in the AVL tree
	public boolean contains(Years element) {
		// Start the search from the root
		return contains(element, root);
	}

	private boolean contains(Years e, YearAVLNode current) {
		if (current == null)
			return false; // Not found, empty tree.
		else if (e.getYear() < current.element.getYear()) // if smaller than root.
			return contains(e, current.left); // Search left subtree
		else if (e.getYear() > current.element.getYear()) // if larger than root.
			return contains(e, current.right); // Search right subtree
		return true; // found .
	}

	// Public method to find a specific element in the AVL tree
	public YearAVLNode find(Years element) {
		// Start the search from the root
		return find(element, root);
	}

	// public method to find an element
	private YearAVLNode find(Years element, YearAVLNode current) {
		if (current == null)
			return null;
		if (element.getYear() < current.element.getYear())
			return find(element, current.left);
		else if (element.getYear() > current.element.getYear())
			return find(element, current.right);
		else
			return current;
	}

	// Public method to insert an element into the AVL tree
	public void insert(Years element) {
		// Start the insertion from the root
		root = insert(element, root);
	}

	private YearAVLNode insert(Years element, YearAVLNode current) {
		if (current == null)
			return new YearAVLNode(element); // create one node tree
		else {
			if (element.getYear() < current.element.getYear())
				current.left = insert(element, current.left);
			else
				current.right = insert(element, current.right);
		}
		updateHeight(current);
		return balance(current);
	}

	// Public method to remove an element from the AVL tree
	public void remove(Years element) {
		// Start the removal from the root
		root = remove(element, root);
	}

	private YearAVLNode remove(Years e, YearAVLNode current) {
		if (current == null)
			return null; // Item not found, Empty tree

		if (e.getYear() < current.element.getYear())
			current.left = remove(e, current.left);
		else if (e.getYear() > current.element.getYear())
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
	public YearAVLNode findMin() {
		// Start the search from the root
		return findMin(root);
	}

	private YearAVLNode findMin(YearAVLNode current) {
		if (current == null)
			return null;
		else if (current.left == null)
			return current;
		else
			return findMin(current.left); // keep going to the left
	}

	// Public method to find the maximum element in the AVL tree
	public YearAVLNode findMax() {
		// Start the search from the root
		return findMax(root);
	}

	private YearAVLNode findMax(YearAVLNode current) {
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
	private YearAVLNode rotateWithLeftChild(YearAVLNode k2) {
		if (k2 == null || k2.left == null) {
			return k2; // Return k2 if it or its left child is null
		}
		YearAVLNode k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
		k1.height = Math.max(height(k1.left), k2.height) + 1;
		return k1;
	}

	// Private method to rotate binary tree node with right child (single rotate to
	// left)
	private YearAVLNode rotateWithRightChild(YearAVLNode k1) {
		if (k1 == null || k1.right == null) {
			return k1; // Return k1 if it or its right child is null
		}

		YearAVLNode k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;
		k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
		k2.height = Math.max(height(k2.right), k1.height) + 1;
		return k2;
	}

	// Private method to perform double rotation with left child
	private YearAVLNode doubleWithLeftChild(YearAVLNode k3) {
		if (k3 == null || k3.left == null) {
			return k3; // Return k3 if it or its left child is null
		}
		k3.left = rotateWithRightChild(k3.left);
		return rotateWithLeftChild(k3);
	}

	// Private method to perform double rotation with right child
	private YearAVLNode doubleWithRightChild(YearAVLNode k1) {
		if (k1 == null || k1.right == null) {
			return k1; // Return k1 if it or its right child is null
		}
		k1.right = rotateWithLeftChild(k1.right);
		return rotateWithRightChild(k1);
	}

	// Private method to update the height of a node
	private void updateHeight(YearAVLNode node) {
		if (node != null) {
			node.height = Math.max(height(node.left), height(node.right)) + 1;
		}
	}

	// Private method to calculate the height of a node
	private int height(YearAVLNode node) {
		return (node == null) ? -1 : node.height;
	}

	// Private method to calculate the balance factor of a node
	private int balanceFactor(YearAVLNode node) {
		return (node == null) ? 0 : height(node.left) - height(node.right);
	}

	// Private method to balance a node
	private YearAVLNode balance(YearAVLNode node) {
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

	// public method to print the tree in order
	public void printInOrder() {
		System.out.println("AVL Tree Elements (In-Order):");
		inOrderTraversal(root);
	}

	// the private method of the print in order
	private void inOrderTraversal(YearAVLNode node) {
		if (node != null) {
			inOrderTraversal(node.left);
			System.out.println(node.element);
			inOrderTraversal(node.right);
		}
	}

	// public method to print the tree reverse in order
	public void printReverseInOrder() {
		System.out.println("AVL Tree Elements (Reverse In-Order):");
		printReverseInOrder(root);
	}

	// private method for prrint reverse in order
	private void printReverseInOrder(YearAVLNode node) {
		if (node != null) {
			printReverseInOrder(node.right);
			System.out.println(node.element);
			printReverseInOrder(node.left);
		}
	}

	// Method to read data from a file
	public void readFromFile(File file) {
		try {
			// scanner to read the file
			Scanner scanner = new Scanner(file);

			while (scanner.hasNextLine()) {
				// read the next line
				String line = scanner.nextLine();
				// split the line into parts
				String[] parts = line.split(",");

				String dateStr = parts[0];
				// extract the info
				double Israeli_Lines_MWs = Double.parseDouble(parts[1]);
				double Gaza_Power_Plant_MWs = Double.parseDouble(parts[2]);
				double Egyptian_Lines_MWs = Double.parseDouble(parts[3]);
				double Total_daily_Supply_available_in_MWs = Double.parseDouble(parts[4]);
				double Overall_demand_in_MWs = Double.parseDouble(parts[5]);
				double Power_Cuts_hours_day_400mg = Double.parseDouble(parts[6]);
				double Temp = Double.parseDouble(parts[7]);

				try {// extract the date
					Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
					SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
					SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
					SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
					int dayNumber = Integer.parseInt(dayFormat.format(date));
					int monthNumber = Integer.parseInt(monthFormat.format(date));
					int yearNumber = Integer.parseInt(yearFormat.format(date));
					// make a day object and put the info in it
					Days day = new Days(dayNumber, Israeli_Lines_MWs, Gaza_Power_Plant_MWs, Egyptian_Lines_MWs,
							Total_daily_Supply_available_in_MWs, Overall_demand_in_MWs, Power_Cuts_hours_day_400mg,
							Temp, date);
					// adding the the day object to the tree
					adding(day, yearNumber, monthNumber, dayNumber);

				} catch (ParseException e) {
					e.printStackTrace();
				}

			}

			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	// Method to add a day to the appropriate year, month, and day in the list
	public void adding(Days day, int yearNumber, int monthNumber, int dayNumber) {
		boolean validYear = false;
		boolean validMonth = false;
		boolean validDay = false;
		// check if the tree contain this year
		if (contains(new Years(yearNumber))) {
			validYear = true;
			// check if the month tree contain this month
			if (find(new Years(yearNumber)).element.getMonthsAVL().contains(new Months(monthNumber))) {
				validMonth = true;
				// check if the day tree have this day
				if (find(new Years(yearNumber)).element.getMonthsAVL().find(new Months(monthNumber)).element
						.getDaysAVL().contains(day)) {
					validDay = true;
				}
				// if the day tree do not have this day
				if (!validDay) {
					find(new Years(yearNumber)).element.getMonthsAVL().find(new Months(monthNumber)).element
							.getDaysAVL().insert(day);
				}
			}
			// if the month tree do not have this month
			if (!validMonth) {
				Months month = new Months(monthNumber, new DaysAVL());
				find(new Years(yearNumber)).element.getMonthsAVL().insert(month);

			}
		}
		// if the tree do not have this year
		if (!validYear) {
			MonthsAVL monthAVL = new MonthsAVL();
			Years year = new Years(yearNumber, monthAVL);
			insert(year);
		}

	}

	// print a specific day in order in the table view
	public void inOrderTraversalDaysData(ObservableList<Days> daysData) {
		inOrderTraversalDaysData(root, daysData);
	}

	private void inOrderTraversalDaysData(YearAVLNode node, ObservableList<Days> daysData) {
		if (node != null) {
			inOrderTraversalDaysData(node.left, daysData);
			MonthsAVL monthsAVL = node.element.getMonthsAVL();
			if (monthsAVL != null) {
				monthsAVL.inOrderTraversalDaysData(daysData);
			}
			inOrderTraversalDaysData(node.right, daysData);
		}
	}

// -------------------- InOrder

	// print a specific day in order
	public void inOrderAppendsDaysData(TextArea textArea, Days day, ArrayList<Days> list) {
		inOrderAppendsDaysData(root, textArea, day, list);
	}

	private void inOrderAppendsDaysData(YearAVLNode node, TextArea textArea, Days day, ArrayList<Days> list) {
		if (node != null) {
			if (node.left != null)
				inOrderAppendsDaysData(node.left, textArea, day, list);
			MonthsAVL monthsAVL = node.element.getMonthsAVL();
			if (monthsAVL != null) {
				monthsAVL.inOrderAppendsDaysData(textArea, day, list);
			}
			if (node.right != null)
				inOrderAppendsDaysData(node.right, textArea, day, list);
		}
	}

	// print a specific month inorder
	public void inOrderAppendsMonthsData(TextArea textArea, Months month, ArrayList<Days> list) {
		inOrderAppendsMonthsData(root, textArea, month, list);
	}

	private void inOrderAppendsMonthsData(YearAVLNode node, TextArea textArea, Months month, ArrayList<Days> list) {
		if (node != null) {
			if (node.left != null)
				inOrderAppendsMonthsData(node.left, textArea, month, list);
			MonthsAVL monthsAVL = node.element.getMonthsAVL();
			if (monthsAVL != null) {
				monthsAVL.inOrderAppendsMonthsData(textArea, month, list);
			}
			if (node.right != null)
				inOrderAppendsMonthsData(node.right, textArea, month, list);
		}
	}

	// print a specific year inorder
	public void inOrderAppendsYearsData(TextArea textArea, Years year, ArrayList<Days> list) {
		inOrderAppendsYearsData(root, textArea, year, list);
	}

	private void inOrderAppendsYearsData(YearAVLNode node, TextArea textArea, Years year, ArrayList<Days> list) {
		if (node != null) {
			if (node.left != null)
				inOrderAppendsYearsData(node.left, textArea, year, list);
			MonthsAVL monthsAVL = node.element.getMonthsAVL();
			if (node.element.getYear() == year.getYear()) {
				if (monthsAVL != null) {
					monthsAVL.inOrderAppendsYearsData(textArea, list);
				}
			}
			if (node.right != null)
				inOrderAppendsYearsData(node.right, textArea, year, list);
		}
	}

	// print all the data in order
	public void inOrderAppendAllData(TextArea textArea, ArrayList<Days> list) {
		inOrderAppendAllData(root, textArea, list);
	}

	private void inOrderAppendAllData(YearAVLNode node, TextArea textArea, ArrayList<Days> list) {
		if (node != null) {
			if (node.left != null)
				inOrderAppendAllData(node.left, textArea, list);
			MonthsAVL monthsAVL = node.element.getMonthsAVL();
			if (monthsAVL != null) {
				monthsAVL.inOrderAppendsYearsData(textArea, list);

			}
			if (node.right != null)
				inOrderAppendAllData(node.right, textArea, list);
		}
	}

//...................... PostOrder
	// print a specific day PreOrder
	public void preOrderAppendsDaysData(TextArea textArea, Days day, ArrayList<Days> list) {
		preOrderAppendsDaysData(root, textArea, day, list);
	}

	private void preOrderAppendsDaysData(YearAVLNode node, TextArea textArea, Days day, ArrayList<Days> list) {
		if (node != null) {
			MonthsAVL monthsAVL = node.element.getMonthsAVL();
			if (monthsAVL != null) {
				monthsAVL.preOrderAppendsDaysData(textArea, day, list);
			}
			if (node.left != null)
				preOrderAppendsDaysData(node.left, textArea, day, list);
			if (node.right != null)
				preOrderAppendsDaysData(node.right, textArea, day, list);
		}
	}

	// print a specific month PreOrder
	public void preOrderAppendsMonthsData(TextArea textArea, Months month, ArrayList<Days> list) {
		preOrderAppendsMonthsData(root, textArea, month, list);
	}

	private void preOrderAppendsMonthsData(YearAVLNode node, TextArea textArea, Months month, ArrayList<Days> list) {
		if (node != null) {
			MonthsAVL monthsAVL = node.element.getMonthsAVL();
			if (monthsAVL != null) {
				monthsAVL.preOrderAppendsMonthsData(textArea, month, list);
			}
			if (node.left != null)
				preOrderAppendsMonthsData(node.left, textArea, month, list);
			if (node.right != null)
				preOrderAppendsMonthsData(node.right, textArea, month, list);
		}
	}

	// print a specific year PreOrder
	public void preOrderAppendsYearsData(TextArea textArea, Years year, ArrayList<Days> list) {
		preOrderAppendsYearsData(root, textArea, year, list);
	}

	private void preOrderAppendsYearsData(YearAVLNode node, TextArea textArea, Years year, ArrayList<Days> list) {
		if (node != null) {
			MonthsAVL monthsAVL = node.element.getMonthsAVL();
			if (node.element.getYear() == year.getYear()) {
				if (monthsAVL != null) {
					monthsAVL.preOrderAppendsYearsData(textArea, list);
				}
			}
			if (node.left != null)
				preOrderAppendsYearsData(node.left, textArea, year, list);
			if (node.right != null)
				preOrderAppendsYearsData(node.right, textArea, year, list);
		}
	}

	// print all the data PreOrder
	public void preOrderAppendsAllData(TextArea textArea, ArrayList<Days> list) {
		preOrderAppendsAllData(root, textArea, list);
	}

	private void preOrderAppendsAllData(YearAVLNode node, TextArea textArea, ArrayList<Days> list) {
		if (node != null) {
			MonthsAVL monthsAVL = node.element.getMonthsAVL();
			if (monthsAVL != null) {
				monthsAVL.preOrderAppendsYearsData(textArea, list);

			}
			if (node.left != null)
				preOrderAppendsAllData(node.left, textArea, list);
			if (node.right != null)
				preOrderAppendsAllData(node.right, textArea, list);
		}
	}

//...................... PreOrder
	// print a specific day PostOrder
	public void postOrderAppendsDaysData(TextArea textArea, Days day, ArrayList<Days> list) {
		postOrderAppendsDaysData(root, textArea, day, list);
	}

	private void postOrderAppendsDaysData(YearAVLNode node, TextArea textArea, Days day, ArrayList<Days> list) {
		if (node != null) {
			if (node.left != null)
				postOrderAppendsDaysData(node.left, textArea, day, list);
			if (node.right != null)
				postOrderAppendsDaysData(node.right, textArea, day, list);
			MonthsAVL monthsAVL = node.element.getMonthsAVL();
			if (monthsAVL != null) {
				monthsAVL.postOrderAppendsDaysData(textArea, day, list);
			}

		}
	}

	// print a specific month PostOrder
	public void postOrderAppendsMonthsData(TextArea textArea, Months month, ArrayList<Days> list) {
		postOrderAppendsMonthsData(root, textArea, month, list);
	}

	private void postOrderAppendsMonthsData(YearAVLNode node, TextArea textArea, Months month, ArrayList<Days> list) {
		if (node != null) {
			if (node.left != null)
				postOrderAppendsMonthsData(node.left, textArea, month, list);
			if (node.right != null)
				postOrderAppendsMonthsData(node.right, textArea, month, list);
			MonthsAVL monthsAVL = node.element.getMonthsAVL();
			if (monthsAVL != null) {
				monthsAVL.postOrderAppendsMonthsData(textArea, month, list);
			}
		}
	}

	// print a specific year PostOrder
	public void postOrderAppendsYearsData(TextArea textArea, Years year, ArrayList<Days> list) {
		postOrderAppendsYearsData(root, textArea, year, list);
	}

	private void postOrderAppendsYearsData(YearAVLNode node, TextArea textArea, Years year, ArrayList<Days> list) {
		if (node != null) {
			if (node.left != null)
				postOrderAppendsYearsData(node.left, textArea, year, list);
			if (node.right != null)
				postOrderAppendsYearsData(node.right, textArea, year, list);
			MonthsAVL monthsAVL = node.element.getMonthsAVL();
			if (node.element.getYear() == year.getYear()) {
				if (monthsAVL != null) {
					monthsAVL.postOrderAppendsYearsData(textArea, list);
				}
			}
		}
	}

	// print all the data PostOrder
	public void postOrderAppendsAllData(TextArea textArea, ArrayList<Days> list) {
		postOrderAppendsAllData(root, textArea, list);
	}

	private void postOrderAppendsAllData(YearAVLNode node, TextArea textArea, ArrayList<Days> list) {
		if (node != null) {
			if (node.left != null)
				postOrderAppendsAllData(node.left, textArea, list);
			if (node.right != null)
				postOrderAppendsAllData(node.right, textArea, list);
			MonthsAVL monthsAVL = node.element.getMonthsAVL();
			if (monthsAVL != null) {
				monthsAVL.postOrderAppendsYearsData(textArea, list);

			}
		}
	}

	// ............................... LevelByLevel

	// print a specific day levelOrder
	public void levelOrderAppendsDaysData(TextArea textArea, Days day, ArrayList<Days> list) {
		if (root == null)
			return;

		MyQueue queue = new MyQueue();
		queue.enqueue(root);

		while (!queue.isEmpty()) {
			YearAVLNode tempNode = (YearAVLNode) queue.dequeue();
			MonthsAVL monthsAVL = tempNode.element.getMonthsAVL();
			if (monthsAVL != null) {
				monthsAVL.levelOrderAppendsDaysData(textArea, day, list);

			}

			if (tempNode.left != null)
				queue.enqueue(tempNode.left);

			if (tempNode.right != null)
				queue.enqueue(tempNode.right);
		}
	}

	// print a specific month levelOrder
	public void levelOrderAppendsMonthsData(TextArea textArea, Months month, ArrayList<Days> list) {
		if (root == null)
			return;

		MyQueue queue = new MyQueue();
		queue.enqueue(root);

		while (!queue.isEmpty()) {
			YearAVLNode tempNode = (YearAVLNode) queue.dequeue();
			MonthsAVL monthsAVL = tempNode.element.getMonthsAVL();
			if (monthsAVL != null) {
				monthsAVL.levelOrderAppendsMonthsData(textArea, month, list);

			}

			if (tempNode.left != null)
				queue.enqueue(tempNode.left);

			if (tempNode.right != null)
				queue.enqueue(tempNode.right);
		}
	}
	//print a specific year level order
	public void levelOrderAppendsYearsData(TextArea textArea, Years year, ArrayList<Days> list) {
		if (root == null)
			return;

		MyQueue queue = new MyQueue();
		queue.enqueue(root);

		while (!queue.isEmpty()) {
			YearAVLNode tempNode = (YearAVLNode) queue.dequeue();
			if (tempNode.element.getYear() == year.getYear()) {
				MonthsAVL monthsAVL = tempNode.element.getMonthsAVL();
				if (monthsAVL != null) {
					monthsAVL.levelOrderAppendsAllData(textArea, list);

				}
			}

			if (tempNode.left != null)
				queue.enqueue(tempNode.left);

			if (tempNode.right != null)
				queue.enqueue(tempNode.right);
		}
	}
	//print all the data level Order
	public void levelOrderAppendsAllData(TextArea textArea, ArrayList<Days> list) {
		if (root == null)
			return;

		MyQueue queue = new MyQueue();
		queue.enqueue(root);

		while (!queue.isEmpty()) {
			YearAVLNode tempNode = (YearAVLNode) queue.dequeue();
			MonthsAVL monthsAVL = tempNode.element.getMonthsAVL();
			if (monthsAVL != null) {
				monthsAVL.levelOrderAppendsAllData(textArea, list);

			}

			if (tempNode.left != null)
				queue.enqueue(tempNode.left);

			if (tempNode.right != null)
				queue.enqueue(tempNode.right);
		}
	}

//....................... save
	//clearing the file
	public void clearFile(File file) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//public method to save all the data in the file
	public void save(File file) {
		clearFile(file);
		save(root, file);

	}

	private void save(YearAVLNode node, File file) {
		if (node != null) {
			if (node.left != null)
				save(node.left, file);
			MonthsAVL monthsAVL = node.element.getMonthsAVL();
			if (monthsAVL != null) {
				monthsAVL.save(file);
			}
			if (node.right != null)
				save(node.right, file);
		}
	}

}