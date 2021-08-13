package nz.ac.auckland.softeng281.a4;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EdgesLinkedListTest {

    EdgesLinkedList list;

    @Before
    public void setUp() {
        list = new EdgesLinkedList();
    }

    @Test
    public void testPrependEmptyList() {
        list.prepend(new Edge(new Node("1"), new Node("2"), 1));
        assertEquals(new Edge(new Node("1"), new Node("2"), 1), list.get(0));
    }
    
    @Test
    public void testPrepend() {
        list.prepend(new Edge(new Node("1"), new Node("2"), 1));
        list.prepend(new Edge(new Node("3"), new Node("4"), 2));

        assertEquals(new Edge(new Node("3"), new Node("4"), 2), list.get(0));
        assertEquals(new Edge(new Node("1"), new Node("2"), 1), list.get(1));
    }
    
    @Test
    public void testAppend() {
        list.append(new Edge(new Node("1"), new Node("2"), 1));
        list.append(new Edge(new Node("2"), new Node("3"), 2));
        list.append(new Edge(new Node("1"), new Node("3"), 3));
  
        assertEquals(new Edge(new Node("1"), new Node("2"), 1), list.get(0));
        assertEquals(new Edge(new Node("2"), new Node("3"), 2), list.get(1));
        assertEquals(new Edge(new Node("1"), new Node("3"), 3), list.get(2));
    }
    
    @Test
    public void testAppendEmpty() {
        list.append(new Edge(new Node("1"), new Node("2"), 1));
        assertEquals(new Edge(new Node("1"), new Node("2"), 1), list.get(0));
    }
    
    @Test
    public void testSize() {
    	list.prepend(new Edge(new Node("1"), new Node("2"), 1));
    	list.prepend(new Edge(new Node("2"), new Node("3"), 1));
    	list.remove(0);
    	list.prepend(new Edge(new Node("1"), new Node("2"), 1));
    	
    	assertEquals(list.size(), 2);
    }

    public void testSizeEmpty() {
    	assertEquals(list.size(), 0);
    }

    @Test
    public void testGet() {
        Edge edgeOne = new Edge(new Node("1"), new Node("2"), 1);
        Edge edgeTwo= new Edge(new Node("2"), new Node("3"), 1);

        list.prepend(edgeOne);
        list.prepend(edgeTwo);

        assertEquals(list.get(1), edgeOne);
    }
    
    @Test(expected = InvalidPositionException.class)
    public void testGetEmptyThrows() {
        list.get(0);
    }
    
    @Test
    public void testInsertZero() {
    	list.append(new Edge(new Node("1"), new Node("2"), 1));
        list.insert(0, new Edge(new Node("2"), new Node("3"), 2));
        
        assertEquals(list.get(0), new Edge(new Node("2"), new Node("3"), 2));
    }

    @Test
    public void testInsert() {
        Edge edgeOne = new Edge(new Node("1"), new Node("2"), 1);
        Edge edgeTwo = new Edge(new Node("2"), new Node("3"), 1);
        Edge edgeThree = new Edge(new Node("3"), new Node("4"), 1);

        list.append(edgeTwo);
        list.append(edgeThree);
        list.insert(1, edgeOne);


        assertEquals(edgeTwo, list.get(0));
        assertEquals(edgeOne, list.get(1));
        assertEquals(edgeThree, list.get(2));
    }

    @Test(expected = InvalidPositionException.class)
    public void testRemoveEmpty() {
        list.remove(0);
    }
    
    @Test
    public void testRemoveZero() {
    	list.append(new Edge(new Node("1"), new Node("2"), 1));
        list.remove(0);
        
        assertEquals(list.size(), 0);
    }
    
    @Test
    public void testRemoveEnd() {
    	Edge edgeOneTwo = new Edge(new Node("1"), new Node("2"), 1);
        Edge edgeTwoThree = new Edge(new Node("2"), new Node("3"), 2);

        list.append(edgeOneTwo);
        list.append(edgeTwoThree);

        list.remove(1);
        
        assertEquals(list.get(0), new Edge(new Node("1"), new Node("2"), 1));
    	
    }
    
    


}