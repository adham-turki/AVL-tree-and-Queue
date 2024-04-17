package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;

public class DaysAVL {

	private AVLTreeNode root;

	public DaysAVL() {
		root = null;
	}

	public AVLTreeNode getRoot() {
		return root;
	}

	// Public method to check if an element is in the AVL tree
	public boolean contains(Days element) {
		// Start the search from the root
		return contains(element, root);
	}

	private boolean contains(Days e, AVLTreeNode current) {
		if (current == null)
			return false; // Not found, empty tree.
		else if (e.getDay() < current.element.getDay()) // if smaller than root.
			return contains(e, current.left); // Search left subtree
		else if (e.getDay() > current.element.getDay()) // if larger than root.
			return contains(e, current.right); // Search right subtree
		return true; // found .
	}

	// Public method to find a specific element in the AVL tree
	public AVLTreeNode find(Days element) {
		// Start the search from the root
		return find(element, root);
	}

	private AVLTreeNode find(Days element, AVLTreeNode current) {
		if (current == null)
			return null;
		if (element.getDay() < current.element.getDay())
			return find(element, current.left);
		else if (element.getDay() > current.element.getDay())
			return find(element, current.right);
		else
			return current;
	}

	// Public method to insert an element into the AVL tree
	public void insert(Days element) {
		// Start the insertion from the root
		root = insert(element, root);
	}

	private AVLTreeNode insert(Days element, AVLTreeNode current) {
		if (current == null)
			return new AVLTreeNode(element); // create one node tree
		else {
			if (element.getDay() < current.element.getDay())
				current.left = insert(element, current.left);
			else
				current.right = insert(element, current.right);
		}
		updateHeight(current);
		return balance(current);
	}

	// Public method to remove an element from the AVL tree
	public void remove(Days element) {
		// Start the removal from the root
		root = remove(element, root);
	}

	private AVLTreeNode remove(Days e, AVLTreeNode current) {
		if (current == null)
			return null; // Item not found, Empty tree

		if (e.getDay() < current.element.getDay())
			current.left = remove(e, current.left);
		else if (e.getDay() > current.element.getDay())
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
	public AVLTreeNode findMin() {
		// Start the search from the root
		return findMin(root);
	}

	private AVLTreeNode findMin(AVLTreeNode current) {
		if (current == null)
			return null;
		else if (current.left == null)
			return current;
		else
			return findMin(current.left); // keep going to the left
	}

	// Public method to find the maximum element in the AVL tree
	public AVLTreeNode findMax() {
		// Start the search from the root
		return findMax(root);
	}

	private AVLTreeNode findMax(AVLTreeNode current) {
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
	private AVLTreeNode rotateWithLeftChild(AVLTreeNode k2) {
		if (k2 == null || k2.left == null) {
			return k2; // Return k2 if it or its left child is null
		}
		AVLTreeNode k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
		k1.height = Math.max(height(k1.left), k2.height) + 1;
		return k1;
	}

	// Private method to rotate binary tree node with right child (single rotate to
	// left)
	private AVLTreeNode rotateWithRightChild(AVLTreeNode k1) {
		if (k1 == null || k1.right == null) {
			return k1; // Return k1 if it or its right child is null
		}

		AVLTreeNode k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;
		k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
		k2.height = Math.max(height(k2.right), k1.height) + 1;
		return k2;
	}

	// Private method to perform double rotation with left child
	private AVLTreeNode doubleWithLeftChild(AVLTreeNode k3) {
		if (k3 == null || k3.left == null) {
			return k3; // Return k3 if it or its left child is null
		}
		k3.left = rotateWithRightChild(k3.left);
		return rotateWithLeftChild(k3);
	}

	// Private method to perform double rotation with right child
	private AVLTreeNode doubleWithRightChild(AVLTreeNode k1) {
		if (k1 == null || k1.right == null) {
			return k1; // Return k1 if it or its right child is null
		}
		k1.right = rotateWithLeftChild(k1.right);
		return rotateWithRightChild(k1);
	}

	// Private method to update the height of a node
	private void updateHeight(AVLTreeNode node) {
		if (node != null) {
			node.height = Math.max(height(node.left), height(node.right)) + 1;
		}
	}

	// Private method to calculate the height of a node
	private int height(AVLTreeNode node) {
		return (node == null) ? -1 : node.height;
	}

	// Private method to calculate the balance factor of a node
	private int balanceFactor(AVLTreeNode node) {
		return (node == null) ? 0 : height(node.left) - height(node.right);
	}

	// Private method to balance a node
	private AVLTreeNode balance(AVLTreeNode node) {
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
	//method to print the data in order
	public void printInOrder() {
		System.out.println("AVL Tree Elements (In-Order):");
		inOrderTraversal(root);
	}
	//method to print the data in order
	private void inOrderTraversal(AVLTreeNode node) {
		if (node != null) {
			inOrderTraversal(node.left);
			System.out.println(node.element);
			inOrderTraversal(node.right);
		}
	}
	//method to print the data in reverse order
	public void printReverseInOrder() {
		System.out.println("AVL Tree Elements (Reverse In-Order):");
		printReverseInOrder(root);
	}
	//method to print the data in reverse order
	private void printReverseInOrder(AVLTreeNode node) {
		if (node != null) {
			printReverseInOrder(node.right);
			System.out.println(node.element);
			printReverseInOrder(node.left);
		}
	}
	//method to print the data in order in the table view
	public void inOrderTraversalDaysData(ObservableList<Days> daysData) {
		inOrderTraversalDaysData(root, daysData);
	}
	//method to print the data in order in the table view
	private void inOrderTraversalDaysData(AVLTreeNode node, ObservableList<Days> daysData) {
		if (node != null) {
			inOrderTraversalDaysData(node.left, daysData);
			daysData.add(node.element);
			inOrderTraversalDaysData(node.right, daysData);
		}
	}

//...................... InOrder
	//method to print the data in Order
	public void inOrderAppendsDaysData(TextArea textArea, Days day, ArrayList<Days> list) {
		inOrderAppendsDaysData(root, textArea, day, list);
	}
	//method to print the data in Order
	private void inOrderAppendsDaysData(AVLTreeNode node, TextArea textArea, Days dayObj, ArrayList<Days> list) {
		if (node != null) {
			if (node.left != null)
				inOrderAppendsDaysData(node.left, textArea, dayObj, list);
			if (node.element.getDay() == dayObj.getDay()) {
				dayObj = node.element;
				list.add(dayObj);
				LocalDate localDate = dayObj.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				textArea.appendText(localDate + "==> ,Israeli_Lines_MWs:" + dayObj.getIsraeli_Lines_MWs()
						+ " ,Gaza_Power_Plant_MWs:" + dayObj.getGaza_Power_Plant_MWs() + " ,Egyptian_Lines_MW:"
						+ dayObj.getEgyptian_Lines_MWs() + " ,Total_daily_Supply_available_in_MWs:"
						+ dayObj.getTotal_daily_Supply_available_in_MWs() + " ,Overall_demand_in_MWs:"
						+ dayObj.getOverall_demand_in_MWs() + " ,Power_Cuts_hours_day_400mg:"
						+ dayObj.getPower_Cuts_hours_day_400mg() + " ,Temp" + dayObj.getTemp() + "\n");
			}
			if (node.right != null)
				inOrderAppendsDaysData(node.right, textArea, dayObj, list);
		}

	}
	//method to print the data in Order
	public void inOrderAppendsMonthsData(TextArea textArea, ArrayList<Days> list) {
		inOrderAppendsMonthsData(root, textArea, list);
	}
	//method to print the data in Order
	private void inOrderAppendsMonthsData(AVLTreeNode node, TextArea textArea, ArrayList<Days> list) {

		if (node != null) {
			if (node.left != null)
				inOrderAppendsMonthsData(node.left, textArea, list);
			Days dayObj = node.element;
			list.add(dayObj);
			LocalDate localDate = dayObj.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			textArea.appendText(localDate + "==> ,Israeli_Lines_MWs:" + dayObj.getIsraeli_Lines_MWs()
					+ " ,Gaza_Power_Plant_MWs:" + dayObj.getGaza_Power_Plant_MWs() + " ,Egyptian_Lines_MW:"
					+ dayObj.getEgyptian_Lines_MWs() + " ,Total_daily_Supply_available_in_MWs:"
					+ dayObj.getTotal_daily_Supply_available_in_MWs() + " ,Overall_demand_in_MWs:"
					+ dayObj.getOverall_demand_in_MWs() + " ,Power_Cuts_hours_day_400mg:"
					+ dayObj.getPower_Cuts_hours_day_400mg() + " ,Temp" + dayObj.getTemp() + "\n");

			if (node.right != null)
				inOrderAppendsMonthsData(node.right, textArea, list);
		}

	}
	// ,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,, PreOrder
	//method to print the data in PreOrder
	public void preOrderAppendsDaysData(TextArea textArea, Days day, ArrayList<Days> list) {
		preOrderAppendsDaysData(root, textArea, day, list);
	}
	//method to print the data in PreOrder
	private void preOrderAppendsDaysData(AVLTreeNode node, TextArea textArea, Days dayObj, ArrayList<Days> list) {

		if (node != null) {
			if (node.element.getDay() == dayObj.getDay()) {
				dayObj = node.element;
				list.add(dayObj);
				LocalDate localDate = dayObj.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				textArea.appendText(localDate + " ==>,Israeli_Lines_MWs:" + dayObj.getIsraeli_Lines_MWs()
						+ " ,Gaza_Power_Plant_MWs:" + dayObj.getGaza_Power_Plant_MWs() + " ,Egyptian_Lines_MW:"
						+ dayObj.getEgyptian_Lines_MWs() + " ,Total_daily_Supply_available_in_MWs:"
						+ dayObj.getTotal_daily_Supply_available_in_MWs() + " ,Overall_demand_in_MWs:"
						+ dayObj.getOverall_demand_in_MWs() + " ,Power_Cuts_hours_day_400mg:"
						+ dayObj.getPower_Cuts_hours_day_400mg() + " ,Temp" + dayObj.getTemp() + "\n");
			}
			if (node.left != null)
				preOrderAppendsDaysData(node.left, textArea, dayObj, list);
			if (node.right != null)
				preOrderAppendsDaysData(node.right, textArea, dayObj, list);
		}

	}
	//method to print the data in PreOrder
	public void preOrderAppendsMonthsData(TextArea textArea, ArrayList<Days> list) {
		preOrderAppendsMonthsData(root, textArea, list);
	}
	//method to print the data in PreOrder
	private void preOrderAppendsMonthsData(AVLTreeNode node, TextArea textArea, ArrayList<Days> list) {

		if (node != null) {
			Days dayObj = node.element;
			list.add(dayObj);
			LocalDate localDate = dayObj.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			textArea.appendText(localDate + "==> ,Israeli_Lines_MWs:" + dayObj.getIsraeli_Lines_MWs()
					+ " ,Gaza_Power_Plant_MWs:" + dayObj.getGaza_Power_Plant_MWs() + " ,Egyptian_Lines_MW:"
					+ dayObj.getEgyptian_Lines_MWs() + " ,Total_daily_Supply_available_in_MWs:"
					+ dayObj.getTotal_daily_Supply_available_in_MWs() + " ,Overall_demand_in_MWs:"
					+ dayObj.getOverall_demand_in_MWs() + " ,Power_Cuts_hours_day_400mg:"
					+ dayObj.getPower_Cuts_hours_day_400mg() + " ,Temp" + dayObj.getTemp() + "\n");

			if (node.left != null)
				preOrderAppendsMonthsData(node.left, textArea, list);
			if (node.right != null)
				preOrderAppendsMonthsData(node.right, textArea, list);
		}

	}

	// ,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,, PostOrder
	//method to print the data in PostOrder
	public void postOrderAppendsDaysData(TextArea textArea, Days day, ArrayList<Days> list) {
		postOrderAppendsDaysData(root, textArea, day, list);
	}
	//method to print the data in PostOrder
	private void postOrderAppendsDaysData(AVLTreeNode node, TextArea textArea, Days dayObj, ArrayList<Days> list) {

		if (node != null) {
			if (node.left != null)
				postOrderAppendsDaysData(node.left, textArea, dayObj, list);
			if (node.right != null)
				postOrderAppendsDaysData(node.right, textArea, dayObj, list);
			if (node.element.getDay() == dayObj.getDay()) {
				dayObj = node.element;
				list.add(dayObj);
				LocalDate localDate = dayObj.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				textArea.appendText(localDate + " ==>,Israeli_Lines_MWs:" + dayObj.getIsraeli_Lines_MWs()
						+ " ,Gaza_Power_Plant_MWs:" + dayObj.getGaza_Power_Plant_MWs() + " ,Egyptian_Lines_MW:"
						+ dayObj.getEgyptian_Lines_MWs() + " ,Total_daily_Supply_available_in_MWs:"
						+ dayObj.getTotal_daily_Supply_available_in_MWs() + " ,Overall_demand_in_MWs:"
						+ dayObj.getOverall_demand_in_MWs() + " ,Power_Cuts_hours_day_400mg:"
						+ dayObj.getPower_Cuts_hours_day_400mg() + " ,Temp" + dayObj.getTemp() + "\n");
			}
		}

	}
	//method to print the data in PostOrder
	public void postOrderAppendsMonthsData(TextArea textArea, ArrayList<Days> list) {
		postOrderAppendsMonthsData(root, textArea, list);
	}
	//method to print the data in PostOrder
	private void postOrderAppendsMonthsData(AVLTreeNode node, TextArea textArea, ArrayList<Days> list) {

		if (node != null) {
			if (node.left != null)
				postOrderAppendsMonthsData(node.left, textArea, list);
			if (node.right != null)
				postOrderAppendsMonthsData(node.right, textArea, list);
			Days dayObj = node.element;
			list.add(dayObj);
			LocalDate localDate = dayObj.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			textArea.appendText(localDate + "==> ,Israeli_Lines_MWs:" + dayObj.getIsraeli_Lines_MWs()
					+ " ,Gaza_Power_Plant_MWs:" + dayObj.getGaza_Power_Plant_MWs() + " ,Egyptian_Lines_MW:"
					+ dayObj.getEgyptian_Lines_MWs() + " ,Total_daily_Supply_available_in_MWs:"
					+ dayObj.getTotal_daily_Supply_available_in_MWs() + " ,Overall_demand_in_MWs:"
					+ dayObj.getOverall_demand_in_MWs() + " ,Power_Cuts_hours_day_400mg:"
					+ dayObj.getPower_Cuts_hours_day_400mg() + " ,Temp" + dayObj.getTemp() + "\n");

		}

	}

	// ............................... LevelByLevel

	// print a specific day level by level
	public void levelOrderAppendsDaysData(TextArea textArea, Days day, ArrayList<Days> list) {
		if (root == null)
			return;

		MyQueue queue = new MyQueue();
		queue.enqueue(root);

		while (!queue.isEmpty()) {
			AVLTreeNode tempNode = (AVLTreeNode) queue.dequeue();
			if (tempNode.element.getDay() == day.getDay()) {
				Days dayObj = tempNode.element;
				list.add(dayObj);
				LocalDate localDate = dayObj.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				textArea.appendText(localDate + "==> ,Israeli_Lines_MWs:" + dayObj.getIsraeli_Lines_MWs()
						+ " ,Gaza_Power_Plant_MWs:" + dayObj.getGaza_Power_Plant_MWs() + " ,Egyptian_Lines_MW:"
						+ dayObj.getEgyptian_Lines_MWs() + " ,Total_daily_Supply_available_in_MWs:"
						+ dayObj.getTotal_daily_Supply_available_in_MWs() + " ,Overall_demand_in_MWs:"
						+ dayObj.getOverall_demand_in_MWs() + " ,Power_Cuts_hours_day_400mg:"
						+ dayObj.getPower_Cuts_hours_day_400mg() + " ,Temp" + dayObj.getTemp() + "\n");

			}
			if (tempNode.left != null)
				queue.enqueue(tempNode.left);

			if (tempNode.right != null)
				queue.enqueue(tempNode.right);
		}
	}

	// print all the days level by level
	public void levelOrderAppendsAllData(TextArea textArea, ArrayList<Days> list) {
		if (root == null)
			return;

		MyQueue queue = new MyQueue();
		queue.enqueue(root);

		while (!queue.isEmpty()) {
			AVLTreeNode tempNode = (AVLTreeNode) queue.dequeue();
			Days dayObj = tempNode.element;
			list.add(dayObj);
			LocalDate localDate = dayObj.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			textArea.appendText(localDate + "==> ,Israeli_Lines_MWs:" + dayObj.getIsraeli_Lines_MWs()
					+ " ,Gaza_Power_Plant_MWs:" + dayObj.getGaza_Power_Plant_MWs() + " ,Egyptian_Lines_MW:"
					+ dayObj.getEgyptian_Lines_MWs() + " ,Total_daily_Supply_available_in_MWs:"
					+ dayObj.getTotal_daily_Supply_available_in_MWs() + " ,Overall_demand_in_MWs:"
					+ dayObj.getOverall_demand_in_MWs() + " ,Power_Cuts_hours_day_400mg:"
					+ dayObj.getPower_Cuts_hours_day_400mg() + " ,Temp" + dayObj.getTemp() + "\n");

			if (tempNode.left != null)
				queue.enqueue(tempNode.left);

			if (tempNode.right != null)
				queue.enqueue(tempNode.right);
		}
	}

	// ................ save
	//method to save the data in file
	public void save(File file) {
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
	        save(root, file, writer);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	//method to save the data in file
	private void save(AVLTreeNode node, File file, BufferedWriter writer) {
	    try {
	        if (node != null) {
	            if (node.left != null)
	                save(node.left, file, writer);
	            
	            Days dayObj = node.element;
	            LocalDate localDate = dayObj.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	            String formattedDate = localDate.format(formatter);
	            writer.write(formattedDate + "," + dayObj.getIsraeli_Lines_MWs() + ","
	                    + dayObj.getGaza_Power_Plant_MWs() + "," + dayObj.getEgyptian_Lines_MWs() + ","
	                    + dayObj.getTotal_daily_Supply_available_in_MWs() + "," + dayObj.getOverall_demand_in_MWs()
	                    + "," + dayObj.getPower_Cuts_hours_day_400mg() + "," + dayObj.getTemp());
	            writer.newLine();

	            if (node.right != null)
	                save(node.right, file, writer);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

}