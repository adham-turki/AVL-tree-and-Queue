package application;

public class MyQueue {

	// Node to represent an element in the queue
	QueueNode front; // Points to the front of the queue
	private QueueNode rear; // Points to the rear of the queue
	public int size; // Keeps track of the number of elements in the queue

	
    // Constructor to initialize an empty queue
	public MyQueue() {
		front = rear = null;
	}

    // Enqueue operation to add an element to the rear of the queue
	public void enqueue(Object data) {
		QueueNode newNode = new QueueNode(data);
		if (isEmpty()) {
			front = newNode;
			rear = newNode;
		} else {
			rear.next = newNode;
			rear = newNode;
		}
		size++; // Increment the size of the queue
	}

	public Object dequeue() {
        // If the queue is empty, set front and rear to the new node
		if (isEmpty())
			throw new IllegalStateException("Queue is empty");
		Object data = front.element;
		front = front.next;
		if (front == null)
			rear = null;
		return data;
	}

    // Get the current size of the queue
	public int getSize() {
		return size;
	}

    // Peek operation to get the element at the front of the queue without removing it
	public Object front() {
		if (isEmpty())
			throw new IllegalStateException("Queue is empty");
		return front.element;
	}

    // Check if the queue is empty
	public boolean isEmpty() {
		return front == null;
	}

    // Traverse and print the elements in the queue
	public void traverse() {
		if (isEmpty()) {
			System.out.println("Queue is empty");
			return;
		}
		QueueNode current = front;
		System.out.print("Queue: ");
		while (current != null) {
			System.out.print(current.element + " ");
			current = current.next;
		}
		System.out.println();
	}

    // Get the element at a specific index in the queue
	public Object get(int index) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException("Index is out of bounds.");
		QueueNode current = front;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}

		return current.element;
	}

}
