package nz.ac.auckland.softeng281.a4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Enclosed.class)
public class GraphTest {

	public static class GraphUnitTest {
		Graph graph;

		@Before
		public void setUp() throws Exception {
			List<String> edges = Arrays.asList("1,2", "2,3", "2,4", "4,2");
			List<String> weights = Arrays.asList("10", "20", "30", "20");
			graph = new Graph(edges, weights);
		}


		@Test
		public void testFindNode() {
			assertTrue(graph.isNodeInGraph(new Node("1")));
			assertTrue(graph.isNodeInGraph(new Node("2")));
			assertTrue(graph.isNodeInGraph(new Node("3")));
			assertTrue(graph.isNodeInGraph(new Node("4")));
			assertFalse(graph.isNodeInGraph(new Node("5")));
		}

		@Test
		public void testShortestPath() {
			List<String> edges = Arrays.asList("1,2", "2,3", "3,4", "4,5");
			List<String> weights = Arrays.asList("10", "20", "30", "20");
			graph = new Graph(edges, weights);
			Path path = new Path(80, new Node("1"), new Node("2"), new Node("3"), new Node("4"), new Node("5"));
			assertEquals(path, graph.computeShortestPath(new Node("1"), new Node("5")));
		}

		@Test
        public void testFindNodeFail() {
            assertFalse(graph.isNodeInGraph(new Node ("test1")));
            assertFalse(graph.isNodeInGraph(new Node ("test2")));
        }
		
		@Test
        public void testEdgeByWeightMissing() {
            Edge edge = graph.searchEdgeByWeight(11);

            assertEquals(null, edge);
        }
		
		@Test 
        public void testEdgeByWeight() {
        	Edge edge1 = graph.searchEdgeByWeight(10);
        	Edge edge2 = graph.searchEdgeByWeight(20);
        	Edge edge3 = graph.searchEdgeByWeight(30);
        	Edge edge4 = graph.searchEdgeByWeight(40);
        	
        	assertEquals(new Edge(new Node("1"), new Node("2"), 10), edge1);
        	assertEquals(new Edge(new Node("2"), new Node("3"), 20), edge2);
        	assertEquals(new Edge(new Node("2"), new Node("4"), 30), edge3);
        	assertEquals(null, edge4);
        }

		@Test 
        public void testWeightByEdge() {
        	int edge1 = graph.searchWeightByEdge(new Node("1"), new Node("2"));
        	int edge2 = graph.searchWeightByEdge(new Node("2"), new Node("3"));
        	int edge3 = graph.searchWeightByEdge(new Node("2"), new Node("4"));
        	int edge4 = graph.searchWeightByEdge(new Node("4"), new Node("2"));
        	
        	assertEquals(10, edge1);
        	assertEquals(20, edge2);
        	assertEquals(30, edge3);
        	assertEquals(20, edge4);
        	
        }
		
		@Test 
        public void testWeightByEdgeMissing() {
        	int edge1 = graph.searchWeightByEdge(new Node("1"), new Node("25"));
        	int edge2 = graph.searchWeightByEdge(new Node("2"), new Node("25"));
        	int edge3 = graph.searchWeightByEdge(new Node("2"), new Node("25"));
        	int edge4 = graph.searchWeightByEdge(new Node("4"), new Node("25"));
        	
        	assertEquals(-1, edge1);
        	assertEquals(-1, edge2);
        	assertEquals(-1, edge3);
        	assertEquals(-1, edge4);
        	
        }
		
		@Test
        public void testComputeShortestPathLengthThreeOrMore() {
            Node source = new Node("1");
            Node target = new Node("4");
            Path path = graph.computeShortestPath(source, target);

            List<Node> edges = path.getPath();

            assertEquals(40, path.getTotalCost());

            assertEquals(new Node("1"), edges.get(0));
            assertEquals(new Node("2"), edges.get(1));
            assertEquals(new Node("4"), edges.get(2));
            assertEquals(3, edges.size());
        }

	}

	public static class GraphSystemTest {

		ByteArrayOutputStream myOut;
		PrintStream origOut;

		@Before
		public void setUp() {
			origOut = System.out;
			myOut = new ByteArrayOutputStream();
			System.setOut(new PrintStream(myOut));
		}

		@After
		public void tearDown() {
			System.setOut(origOut);
			if (myOut.toString().length() > 1) {
				System.out.println(System.lineSeparator() + "the System.out.print was :" + System.lineSeparator()
				+ myOut.toString());
			}
		}

		private void runTest(String fileName, String command) {
			GraphUI.scanner = new Scanner("open " + fileName + System.getProperty("line.separator") + command
					+ System.getProperty("line.separator") + "exit" + System.getProperty("line.separator"));
			GraphControl controller = new GraphControl();
			controller.execute();
		}

		@Test
		public void testSearchWeightA() {
			runTest("a.txt", "search 1 3");
			assertTrue(myOut.toString().contains("Given the edge from source 1 target 3 has weight: 5"));
		}

		@Test
		public void testSearchEdgeByWeightA() {
			runTest("a.txt", "search 5");
			assertTrue(myOut.toString().contains("The edge searched having weight 5 is: 1-->3"));
		}

		@Test
		public void testShortestPathA() {
			runTest("a.txt", "path 5 1");
			assertTrue(myOut.toString().contains("The shortest path is: 5 -> 4 -> 1 cost: 4"));
		}
	}
}