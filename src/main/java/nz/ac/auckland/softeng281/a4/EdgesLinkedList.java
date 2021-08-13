package nz.ac.auckland.softeng281.a4;

/**
 * The Linked List Class Has only one head pointer to the start node. Nodes are
 * indexed starting from 0. List goes from 0 to size-1
 *
 * @author Partha Roop
 */
public class EdgesLinkedList {
	// the head of the linked list
	private Edge head;

	public EdgesLinkedList() {
		head = null;
	}


	/**
	 * This method adds a node with specified data as the start node of the list
	 *
	 * @param : an integer, which is the value of the Node
	 * @return void
	 */
	public void prepend(Edge e) {
		//If the linked list is empty then the edge e becomes the head and the only edge in the graph
		if (head == null) {
			head = e;
		} else {
			// Assign head to a temporary variable, and then set it to follow the edge e
			Edge temp = head;
			head = e;
			head.setNext(temp);
		}
	}

	/**
	 * This method adds a node with specified data as the end node of the list
	 *
	 * @param : an integer, which is the value of the Node
	 * @return void
	 */

	public void append(Edge edge) {
		Edge temp = head;
		// If list is empty then edge is the only edge in the graph, and thus the head
		if (head == null) {
			head = edge;
		} else {
			// looping until the end of the list
			while (temp.getNext() != null) {
				temp = temp.getNext();
			}
			// Setting the last edge of the list
			temp.setNext(edge);
			edge.setNext(null);
		}
	}

	/**
	 * This method gets the value of a node at a given position
	 *
	 * @param pos: an integer, which is the position
	 * @return the value at the position pos
	 */

	public Edge get(int pos) throws InvalidPositionException {
		if (pos < 0 || pos > size() - 1) {
			throw new InvalidPositionException("Position " + pos + " outside the list boundary");
		}
		Edge temp = head;
		int count = 0;

		// Looping through the code until the specified position
		while (temp != null) {
			if (count == pos) {
				return temp;
			} else {
				temp = temp.getNext();
				count++;
			}
		}

		return null;
	}

	/**
	 * This method fetches the value of a node at a given position
	 *
	 * @param pos: an integer, which is the position
	 * @return the value at the position pos
	 */

	public void insert(int pos, Edge edge) throws InvalidPositionException {
		if (pos < 0 || pos > size() - 1) {
			throw new InvalidPositionException("Position " + pos + " outside the list boundary");
		}
		// Inserting edge at given position
		if (pos == 0) {
			Edge temp = head;
			head = edge;
			head.setNext(temp);
		} else {

			edge.setNext(get(pos));
			get(pos-1).setNext(edge);
		}
	}

	/**
	 * This method removes a node at a given position
	 *
	 * @param pos: an integer, which is the position
	 * @return void
	 */

	public void remove(int pos) throws InvalidPositionException {
		if (pos < 0 || pos > size() - 1) {
			throw new InvalidPositionException("Position " + pos + " outside the list boundary");
		}
		if (pos == 0) {
			head = head.getNext();
		} else if (pos == size() -1) {
			get(size()-2).setNext(null);
		} else {
			get(pos-1).setNext(get(pos+1));
		}
	}

	/**
	 * This method returns the size of a list
	 *
	 * @param
	 * @return the size of the list
	 */

	public int size() {
		Edge temp = head;
		int count = 1;
		if (temp == null) {
			return 0;
		}
		// Looping until the end of the list, incrementing count
		while (temp.getNext() != null) {
			temp = temp.getNext();
			count++;
		}
		return count;
	}

	/**
	 * This method is used for printing the data in the list from head till the last
	 * node
	 *
	 * @param
	 * @return void
	 */

	public void print() {
		Edge edge = head;
		while (edge != null) {
			System.out.println(edge);
			edge = edge.getNext();
		}
	}
}
