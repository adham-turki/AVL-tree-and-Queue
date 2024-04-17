package application;

//Node class representing an element in the queue
public class QueueNode {
    // Data of the node
	public Object element;
    // Reference to the next and previous nodes in the queue
	public QueueNode next, prev;

    // Constructor to initialize a node with an element and no next node
	public QueueNode(Object element) {
		this(element, null);
	}

    // Constructor to initialize a node with an element and a specified next node
	public QueueNode(Object element, QueueNode next) {
		this.element = element;
		this.next = next;
	}

    // Getter method to retrieve the next node
	public QueueNode getNext() {
		return next;
	}

	public void setNext(QueueNode next) {
		this.next = next;
	}

	public QueueNode getPrev() {
		return prev;
	}

	public void setPrev(QueueNode prev) {
		this.prev = prev;
	}

	public Object getElement() {
		return element;
	}

	public void setElement(Object element) {
		this.element = element;
	}
	

}
