// Defines that this class belongs to the leetcode package
package leetcode;

import java.util.Arrays;

/*
 * LeetCode 1584: Min Cost to Connect All Points
 *
 * You are given an array points representing integer coordinates of points on a 2D-plane.
 * The cost of connecting two points is the Manhattan distance: |xi - xj| + |yi - yj|.
 * Return the minimum cost to make all points connected into a single spanning tree.
 *
 * Optimal Approach: Prim's Minimum Spanning Tree (MST) Algorithm (Dense Graph O(V^2))
 * - Since every pair of points can be connected, the graph is complete with E = V * (V - 1) / 2 edges.
 * - On dense graphs, array-based Prim's algorithm runs in O(V^2) time without the O(E log V)
 *   overhead and memory churn of a binary heap.
 * - Maintain:
 *   - inMST[i]: boolean indicating whether point i is included in the growing MST.
 *   - minDist[i]: minimum Manhattan distance from point i to any point currently in the MST.
 * - At each of the V steps:
 *   1. Select the unvisited vertex u with the smallest minDist[u].
 *   2. Add minDist[u] to totalCost and mark inMST[u] = true.
 *   3. For each remaining unvisited vertex v, update minDist[v] with the Manhattan distance between u and v.
 *
 * Time Complexity: O(V^2) where V = points.length (at most 10^6 operations for V = 1000).
 * Space Complexity: O(V) auxiliary space for inMST and minDist arrays.
 */
public class p1584 {

    // Method to calculate the minimum cost to connect all points
    public int minCostConnectPoints(int[][] points) {
        if (points == null || points.length <= 1) {
            return 0;
        }

        int n = points.length;
        boolean[] inMST = new boolean[n];
        int[] minDist = new int[n];
        Arrays.fill(minDist, Integer.MAX_VALUE);

        // Start from vertex 0
        minDist[0] = 0;
        int totalCost = 0;

        for (int step = 0; step < n; step++) {
            int u = -1;
            int currentMin = Integer.MAX_VALUE;

            // Pick unvisited vertex with smallest distance to current MST
            for (int i = 0; i < n; i++) {
                if (!inMST[i] && minDist[i] < currentMin) {
                    currentMin = minDist[i];
                    u = i;
                }
            }

            // Include vertex u into MST
            inMST[u] = true;
            totalCost += currentMin;

            // Relax distances to all unvisited neighbors
            int ux = points[u][0];
            int uy = points[u][1];

            for (int v = 0; v < n; v++) {
                if (!inMST[v]) {
                    int dist = Math.abs(ux - points[v][0]) + Math.abs(uy - points[v][1]);
                    if (dist < minDist[v]) {
                        minDist[v] = dist;
                    }
                }
            }
        }

        return totalCost;
    }

    public static void main(String[] args) {
        p1584 solver = new p1584();

        // Test Case 1: Standard 5-point layout
        int[][] points1 = {
            {0, 0},
            {2, 2},
            {3, 10},
            {5, 2},
            {7, 0}
        };
        System.out.println("Test Case 1 Min Cost: " +
                solver.minCostConnectPoints(points1)); // Expected: 20

        // Test Case 2: Points with negative coordinates
        int[][] points2 = {
            {3, 12},
            {-2, 5},
            {-4, 1}
        };
        System.out.println("Test Case 2 Min Cost: " +
                solver.minCostConnectPoints(points2)); // Expected: 18

        // Test Case 3: Single point
        int[][] points3 = {{0, 0}};
        System.out.println("Test Case 3 Min Cost: " +
                solver.minCostConnectPoints(points3)); // Expected: 0

        // Test Case 4: Collinear points along an axis
        int[][] points4 = {
            {0, 0},
            {1, 0},
            {3, 0},
            {6, 0}
        };
        System.out.println("Test Case 4 Min Cost: " +
                solver.minCostConnectPoints(points4)); // Expected: 6
    }
}
