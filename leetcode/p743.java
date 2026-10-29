// Defines that this class belongs to the leetcode package
package leetcode;

// Import utility classes
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*
 * LeetCode 743: Network Delay Time
 * You are given a network of n nodes, labeled from 1 to n.
 * You are also given times, a list of travel times as directed edges times[i] = [u, v, w].
 * We will send a signal from a given node k. Return the minimum time it takes for all the n
 * nodes to receive the signal. If impossible, return -1.
 *
 * Dijkstra's Shortest Path Algorithm:
 * - Build adjacency list mapping each node to a list of (neighbor, weight) pairs.
 * - Maintain dist array initialized to infinity, with dist[k] = 0.
 * - Use a Min-Heap (PriorityQueue) to always process the unvisited node with smallest current distance.
 * - Relax edges to adjacent nodes: if time + weight < dist[neighbor], update dist and enqueue.
 * - Return the maximum travel time among all nodes, or -1 if any node remains unreachable.
 *
 * Time Complexity: O((V + E) log V) where V = n, E = times.length
 * Space Complexity: O(V + E) for the adjacency list and priority queue
 */
public class p743 {

    // Method to calculate minimum network delay time
    public int networkDelayTime(int[][] times, int n, int k) {

        // Build adjacency list: u -> list of [v, weight]
        Map<Integer, List<int[]>> adj = new HashMap<>();
        for (int[] time : times) {
            adj.computeIfAbsent(time[0], key -> new ArrayList<>()).add(new int[] { time[1], time[2] });
        }

        // Distance array to store shortest time to each node (1-indexed)
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;

        // Min-Heap storing [currentDistance, node], sorted by currentDistance
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        minHeap.offer(new int[] { 0, k });

        while (!minHeap.isEmpty()) {

            int[] curr = minHeap.poll();
            int currentTime = curr[0];
            int u = curr[1];

            // If we found a longer path than already recorded, skip
            if (currentTime > dist[u]) {
                continue;
            }

            // Relax all outgoing edges from node u
            if (adj.containsKey(u)) {
                for (int[] edge : adj.get(u)) {
                    int v = edge[0];
                    int weight = edge[1];

                    if (currentTime + weight < dist[v]) {
                        dist[v] = currentTime + weight;
                        minHeap.offer(new int[] { dist[v], v });
                    }
                }
            }
        }

        // Find the maximum time to reach any node
        int maxTime = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                return -1; // Unreachable node exists
            }
            maxTime = Math.max(maxTime, dist[i]);
        }

        return maxTime;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p743 obj = new p743();

        // Test case 1: 4 nodes, signal starts at 2
        // [2,1,1], [2,3,1], [3,4,1] -> delay = 2
        int[][] times1 = { { 2, 1, 1 }, { 2, 3, 1 }, { 3, 4, 1 } };
        int n1 = 4;
        int k1 = 2;
        System.out.println("Network delay time 1: " + obj.networkDelayTime(times1, n1, k1)); // 2

        // Test case 2: Unreachable node
        int[][] times2 = { { 1, 2, 1 } };
        int n2 = 2;
        int k2 = 2;
        System.out.println("Network delay time 2: " + obj.networkDelayTime(times2, n2, k2)); // -1
    }
}
