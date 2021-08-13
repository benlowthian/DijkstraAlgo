package nz.ac.auckland.softeng281.a4;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NodesStackAndQueueTest {

	NodesStackAndQueue stack;

	@Before
	public void setUp() {
		stack = new NodesStackAndQueue();
	}

	@Test
	public void isEmptyEmptyStack() {
		assertTrue(stack.isEmpty());
	}

	@Test
	public void isEmptyNotEmpty() {
		stack.append(new Node("4"));
		assertFalse(stack.isEmpty());
	}

	@Test
	public void testPeek() {
		Node node = new Node("4");

		stack.append(node);
		assertEquals(node, stack.peek());
	}

	@Test 
	public void testPeekElement() {
		Node expected = new Node("4");

		stack.push(new Node("1"));
		stack.push(new Node("2"));
		stack.push(new Node("3"));
		stack.push(expected);

		Node peekedElement = stack.peek();
		assertEquals(expected, peekedElement);
	}

	@Test(expected = EmptyStackException.class)
	public void testPeekEmpty() {
		stack.peek();
	}

	@Test
	public void testPush() {
		Node Node = new Node("test");

		stack.push(Node);
		assertEquals(Node, stack.peek());
	}

	@Test
	public void pushMultiple() {
		Node nodeOne = new Node("1");
		Node nodeTwo = new Node("2");
		Node nodeThree = new Node("3");

		stack.append(nodeOne);
		stack.append(nodeTwo);
		stack.push(nodeThree);

		assertEquals(nodeThree, stack.peek());
		stack.pop();
		assertEquals(nodeOne, stack.peek());
		stack.pop();
		assertEquals(nodeTwo, stack.peek());

	}

	@Test
	public void testPop() {
		Node expected = new Node("4");

		stack.push(new Node("1"));
		stack.push(new Node("2"));
		stack.push(new Node("3"));
		stack.push(expected);

		Node node = stack.pop();
		assertEquals(expected, node);
	}

	@Test(expected = EmptyStackException.class)
	public void testPopEmpty() {
		stack.pop();
	}

	@Test
	public void testAppend() {
		Node node = new Node("test");

		stack.append(node);
		assertEquals(node, stack.peek());
	}


	@Test
	public void testAppendTwo() {
		stack.append(new Node("1"));
		stack.append(new Node("2"));

		Node nodeOne = new Node("1");
		Node nodeTwo= new Node("1");

		assertEquals(nodeOne, stack.peek());
		assertEquals(nodeTwo, stack.pop());
	}

	@Test
	public void testDequeue() {
		Node expected = new Node("4");

		stack.push(expected);
		stack.push(new Node("1"));
		stack.push(new Node("2"));
		stack.push(new Node("3"));

		Node node = stack.dequeue();
		assertEquals(expected, node);
	}

	@Test(expected = EmptyStackException.class)
	public void testDequeueEmpty() {
		stack.dequeue();
	}

}