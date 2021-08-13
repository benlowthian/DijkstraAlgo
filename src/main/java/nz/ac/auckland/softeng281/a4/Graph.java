package nz.ac.auckland.softeng281.a4;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

/**
 * You cannot add new fields.
 */
public class Graph {

	//	/**
	//		 * @author Maryam
	//		 *
	//		 */
	////	public class Graph {
	//
	//		/**
	//		 * @param args
	//		 */
	//		public static void main(String[] args) {
	//			// TODO Auto-generated method stub
	//
	//		}
	//
	////	}


	/**
	 * Each node maps to a list of all the outgoing edges from that node
	 */
	private HashMap<Node, EdgesLinkedList> adjacencyMap;
	/**
	 * root of the graph, to know where to start the DFS or BFS
	 */
	private Node root;

	/**
	 * !!!!!! You cannot change this method !!!!!!!
	 */
	public Graph(List<String> edges, List<String> weights) {
		if (edges.isEmpty() || weights.isEmpty()) {
			throw new IllegalArgumentException("edges and weights are empty");
		}
		adjacencyMap = new HashMap<>();
		int i = 0;
		for (String edge : edges) {
			String[] split = edge.split(",");
			Node source = new Node(split[0]);
			Node target = new Node(split[1]);
			Edge edgeObject = new Edge(source, target, Integer.parseInt(weights.get(i)));
			if (!adjacencyMap.containsKey(source)) {
				adjacencyMap.put(source, new EdgesLinkedList());
			}
			adjacencyMap.get(source).append(edgeObject);
			if (i == 0) {
				root = source;
			}
			i++;
		}
	}


	/**
	 * find a particular node, note that a node might not have outgoing edges but only ingoing edges
	 * so you need to check also the target nodes of the edges
	 *
	 * @param node
	 * @return true if adjacencyMap contains the node, false otherwise.
	 */
	public boolean isNodeInGraph(Node node) {

		//		return adjacencyMap.values().contains(node);

		if (adjacencyMap.containsKey(node)) {
			return true;
		}

		Collection<EdgesLinkedList> values = adjacencyMap.values(); 
		for (EdgesLinkedList value : values) {
			for(int i = 0; i < value.size(); i++) {
				if (value.get(i).getTarget().equals(node)) {
					return true;
				}
			}
		}
		return false;	
	}


	/**
	 * This method finds an edge with a specific weight, if there are more
	 * than one you need to return the first you encounter.
	 * You must use Breath First Search (BFS) strategy starting from the root.
	 * <p>
	 * You can create a data structure to keep track of the visited nodes
	 * Set<Node> visited = new HashSet<>();
	 * If you don't keep track of the visited nodes the method will run forever!
	 * <p>
	 * <p>
	 * In addition to the data structure visited you can only create new
	 * data structures of type EdgesLinkedList and NodesStackAndQue
	 *
	 * @param weight
	 * @return the Edge with the specific weight, null if no edge with the specific weight exists in teh graph
	 */
	public Edge searchEdgeByWeight(int weight) {
		Set<Node> visited = new HashSet<>();
		NodesStackAndQueue newQueue = new NodesStackAndQueue();
		newQueue.push(root);
		visited.add(root);


		while (!newQueue.isEmpty()) {
			Node currentNode = newQueue.dequeue();
			EdgesLinkedList newList = adjacencyMap.get(currentNode);

			if (newList != null) {
				for (int i = 0; i <= newList.size()-1; i++) {
					Edge currentEdge = newList.get(i);
					if (currentEdge.getWeight() == weight) {
						return currentEdge;
					}

					Node target = currentEdge.getTarget();

					if (!visited.contains(target)) {
						newQueue.push(target);
						visited.add(target);
					}
				}
			}
		}
		return null;
	}


	/**
	 * Returns the weight of the Edge with Node source and Node target if the
	 * given Edge is inside the graph.
	 * If there is no edge with the specified source and target, the method returns -1
	 * You must use Depth First Search (DFS) strategy starting from the root.
	 * <p>
	 * RULES
	 * You can create a data structure to keep track of the visited nodes
	 * Set<Node> visited = new HashSet<>();
	 * If you don't keep track of the visited nodes the method will run forever!
	 * <p>
	 * In addition to the data structure visited you can only create new data structures of type
	 * <p>
	 * NodesStackAndQueue and EdgesLinkedList
	 *
	 * @param source
	 * @param target
	 * @return the weight of the first encountered edge with source and target,
	 * -1 if no edge with the given source and target exists
	 */
	public int searchWeightByEdge(Node source, Node target) {
		Set<Node> visited = new HashSet<>();
		NodesStackAndQueue newStack = new NodesStackAndQueue();
		newStack.push(root);

		while (!newStack.isEmpty()) {
			Node currentNode = newStack.pop(); // take top node from stack 
			EdgesLinkedList listOfEdges = adjacencyMap.get(currentNode); // get list of edges

			if (listOfEdges != null) {
					for (int i = 0; i < listOfEdges.size(); i++) {
						
						Edge edge = listOfEdges.get(i);
						
						if (edge.getTarget().equals(target) && (edge.getSource().equals(source))) {
							return edge.getWeight();
						}
						
						if (!visited.contains(edge.getTarget())) {
							newStack.push(edge.getTarget());
							visited.add(edge.getTarget());
						}
					}
				}

			}
		return -1;

	}


	/**
	 * Given a source Node and a target Node it returns the shortest path
	 * between source and target
	 * <p>
	 * A Path is represented as an ordered sequence of nodes, together with the
	 * total weight of the path. (see Path.java class)
	 *
	 * @param source
	 * @param target
	 * @return the shortest path between source and target
	 */
	public Path computeShortestPath(Node source, Node target) {
		HashMap<String, Path> weights = new HashMap<>();
		HashMap<Node, Node> previousValues = new HashMap<>();	
		List<Node> toVisit = new ArrayList<>();
		List<Node> visited = new ArrayList<>();
		int infinity = Integer.MAX_VALUE;

		visited.add(source);
		weights.put(source.getValue(), new Path(0, visited));

		Node currentNode = source;

		while (currentNode != null) {

			EdgesLinkedList listOfEdges = adjacencyMap.get(currentNode);
			int numberOfEdges = listOfEdges.size();
			for (int i = 0; i < numberOfEdges; i++) {
				Edge currentEdge = listOfEdges.get(i);
				String targetKey = currentEdge.getTarget().getValue();

				if (!weights.containsKey(targetKey)){
					weights.put(targetKey, new Path(infinity, toVisit));
				}

				int sourceWeight = weights.get(currentNode.getValue()).getTotalCost();
				int targetWeight = weights.get(targetKey).getTotalCost();


				if (currentEdge.getWeight() + sourceWeight < targetWeight){
					Path altering = weights.get(currentNode.getValue());
					List<Node> pathList = new ArrayList<>();

					pathList.addAll(altering.getPath());
					pathList.add(currentEdge.getTarget());

					Path alteredPath = new Path(altering.getTotalCost() + currentEdge.getWeight(), pathList);
					Path useless = weights.put(targetKey, altering);
				}
			}


			visited.add(currentNode);
			currentNode = LeastValueNode(weights, visited);
		}

		return weights.get(target.getValue());


	}

	public Node LeastValueNode(HashMap<String, Path> weights, List<Node> visited){
		Node lowest = new Node("999");
		int weight = 99999;

		for (HashMap.Entry<String, Path> entry: weights.entrySet()){
			if(entry.getValue().getTotalCost() < weight && !visited.contains(new Node(entry.getKey())) && adjacencyMap.containsKey(new Node(entry.getKey()))){
				weight = entry.getValue().getTotalCost();
				lowest = new Node(entry.getKey());
			}
		}

		if (weight != 99999){
			return lowest;
		}  else {
			return null;
		}
	}
}