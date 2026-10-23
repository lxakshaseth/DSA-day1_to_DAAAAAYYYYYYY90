// Defines that this class belongs to the leetcode package
package leetcode;

// Import utility classes
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * LeetCode 133: Clone Graph
 * Given a reference of a node in a connected undirected graph.
 * Return a deep copy (clone) of the graph.
 *
 * Depth-First Search (DFS) with HashMap Approach:
 * - Use a HashMap (originalNode -> clonedNode) to avoid cycles and infinite loops.
 * - For each node visited:
 *   1. If already cloned, return the cloned instance.
 *   2. Otherwise, create a new clone and store it in the map.
 *   3. Recursively clone all neighbors and add them to clone's neighbor list.
 *
 * Time Complexity: O(V + E) where V is vertices, E is edges
 * Space Complexity: O(V) for the visited map and recursion stack
 */
public class p133 {

    // Definition for a Node
    public static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    // Map to track already cloned nodes
    private Map<Node, Node> visited = new HashMap<>();

    // Method to clone the graph using recursive DFS
    public Node cloneGraph(Node node) {

        if (node == null) {
            return null;
        }

        // If the node has already been cloned, return the clone
        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        // Create new clone for current node
        Node cloneNode = new Node(node.val);
        visited.put(node, cloneNode);

        // Recursively clone and attach all neighbors
        for (Node neighbor : node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }

        return cloneNode;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p133 obj = new p133();

        // Build sample graph: 4 nodes in a cycle: 1 - 2 - 3 - 4 - 1
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        node1.neighbors.add(node2);
        node1.neighbors.add(node4);

        node2.neighbors.add(node1);
        node2.neighbors.add(node3);

        node3.neighbors.add(node2);
        node3.neighbors.add(node4);

        node4.neighbors.add(node1);
        node4.neighbors.add(node3);

        Node cloned = obj.cloneGraph(node1);

        System.out.println("Original node 1 hash: " + System.identityHashCode(node1));
        System.out.println("Cloned node 1 hash:   " + System.identityHashCode(cloned));
        System.out.println("Cloned node 1 value:  " + cloned.val);
        System.out.println("Cloned neighbors count: " + cloned.neighbors.size());
    }
}
