// Defines that this class belongs to the leetcode package
package leetcode;

import java.util.Arrays;

/*
 * LeetCode 684: Redundant Connection
 *
 * Given an undirected graph starting as a tree with n nodes (labeled 1 to n) and
 * one extra edge creating a cycle, return an edge that can be removed so that
 * the resulting graph is a valid tree. If multiple answers exist, return the one
 * that occurs last in the input.
 *
 * Optimal Approach: Disjoint Set Union (Union-Find) with Path Compression and Union by Rank
 * - A tree with n nodes contains exactly n - 1 edges and no cycles. The addition of the
 *   n-th edge introduces exactly one cycle.
 * - Initialize a DSU structure where each node 1 to n is its own parent with rank 1.
 * - Iterate through each edge [u, v] in the input order:
 *   - Use find(u) and find(v) with full path compression to find their component roots.
 *   - If find(u) == find(v), u and v already belong to the same connected component.
 *     Adding this edge creates a cycle.
 *   - Since we iterate from left to right, this edge is the redundant edge closing the cycle;
 *     return [u, v] immediately.
 *   - Otherwise, union the two components by attaching the smaller rank tree under the larger rank tree.
 *
 * Time Complexity: O(n * alpha(n)) practically O(n), where alpha is the Inverse Ackermann function.
 * Space Complexity: O(n) for the parent and rank arrays.
 */
public class p684 {

    private static class DSU {
        private final int[] parent;
        private final int[] rank;

        public DSU(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        // Find with path compression
        public int find(int i) {
            if (parent[i] != i) {
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }

        // Union by rank; returns false if nodes are already connected
        public boolean union(int u, int v) {
            int rootU = find(u);
            int rootV = find(v);

            if (rootU == rootV) {
                return false; // Cycle detected
            }

            if (rank[rootU] < rank[rootV]) {
                parent[rootU] = rootV;
            } else if (rank[rootU] > rank[rootV]) {
                parent[rootV] = rootU;
            } else {
                parent[rootV] = rootU;
                rank[rootU]++;
            }

            return true;
        }
    }

    // Method to find the redundant connection
    public int[] findRedundantConnection(int[][] edges) {
        if (edges == null || edges.length == 0) {
            return new int[0];
        }

        int n = edges.length;
        DSU dsu = new DSU(n);

        for (int[] edge : edges) {
            if (!dsu.union(edge[0], edge[1])) {
                return edge;
            }
        }

        return new int[0];
    }

    public static void main(String[] args) {
        p684 solver = new p684();

        // Test Case 1: Simple 3-node cycle
        int[][] edges1 = {
            {1, 2},
            {1, 3},
            {2, 3}
        };
        System.out.println("Test Case 1 Redundant Edge: " +
                Arrays.toString(solver.findRedundantConnection(edges1))); // Expected: [2, 3]

        // Test Case 2: 5-node graph with cycle and tail
        int[][] edges2 = {
            {1, 2},
            {2, 3},
            {3, 4},
            {1, 4},
            {1, 5}
        };
        System.out.println("Test Case 2 Redundant Edge: " +
                Arrays.toString(solver.findRedundantConnection(edges2))); // Expected: [1, 4]

        // Test Case 3: Complete triangle of 3 nodes
        int[][] edges3 = {
            {1, 2},
            {2, 3},
            {3, 1}
        };
        System.out.println("Test Case 3 Redundant Edge: " +
                Arrays.toString(solver.findRedundantConnection(edges3))); // Expected: [3, 1]
    }
}
