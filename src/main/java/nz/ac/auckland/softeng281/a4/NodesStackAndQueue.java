package nz.ac.auckland.softeng281.a4;

import java.util.ArrayList;

public class NodesStackAndQueue {

	private ArrayList<Node> list;
    public NodesStackAndQueue() {
        list = new ArrayList<>();
    }
    
    // Function to check whether list is empty
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Push operation refers to inserting an element on the Top of the stack.
     *
     * @param node
     */
    public void push(Node node) {
        	list.add(node);
    }

    /**
     * pop an element from the top of the stack (removes and returns the tope element)
     *
     * @return
     */
    public Node pop() throws EmptyStackException {
    	// CHecking list is not empty
    	if (list.isEmpty()) {
    		throw new EmptyStackException();
    	}
    	return list.remove(list.size()-1);
    }

    /**
     * get the element from the top of the stack without removing it
     *
     * @return
     */
    public Node peek() throws EmptyStackException{
    	if (this.isEmpty()) {
    		throw new EmptyStackException();
    	}
    	return list.get(list.size()-1);
    }

    /**
     * append an element at the end of the stack
     *
     * @param node
     */
    public void append(Node node) {
        	list.add(0, node);
    }
    
    // Dequeue removes element from the front of the queue and returns it
    public Node dequeue() throws EmptyStackException {
    	// Checking queue is not empty
    	if (list.isEmpty()) {
    		throw new EmptyStackException();
    	}
    	return list.remove(0);
    }
}
