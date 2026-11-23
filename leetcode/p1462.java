// Defines that this class belongs to the leetcode package
package leetcode;

import java.util.ArrayList;
import java.util.List;

/*
 * LeetCode 1462: Course Schedule IV
 *
 * You are given numCourses courses (0 to numCourses - 1) and prerequisites where
 * prerequisites[i] = [a, b] indicates that course a must be taken before course b.
 * Given an array of queries where queries[j] = [u, v], return a list of booleans indicating
 * whether course u is a prerequisite (direct or indirect) of course v.
 *
 * Optimal Approach: Floyd-Warshall Transitive Closure (All-Pairs Reachability)
 * - Since numCourses <= 100, the transitive closure of the directed acyclic graph can be
 *   precomputed in O(V^3) time using the Floyd-Warshall algorithm.
 * - Maintain a 2D boolean reachability matrix isPrereq[numCourses][numCourses]:
 *   - Directly set isPrereq[u][v] = true for every [u, v] in prerequisites.
 *   - For every intermediate course k from 0 to numCourses - 1:
 *     - If course i can reach k and k can reach j, then course i is a prerequisite of j:
 *         isPrereq[i][j] = isPrereq[i][j] || (isPrereq[i][k] && isPrereq[k][j])
 * - Each query [u, v] is then resolved in O(1) time by checking isPrereq[u][v].
 *
 * Time Complexity: O(V^3 + Q) where V = numCourses <= 100 (10^6 operations) and Q = queries.length <= 10^4.
 * Space Complexity: O(V^2) for the 2D reachability matrix.
 */
public class p1462 {

    // Method to check prerequisite reachability for all queries
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        List<Boolean> result = new ArrayList<>();
        if (numCourses <= 0 || queries == null) {
            return result;
        }

        boolean[][] isPrereq = new boolean[numCourses][numCourses];

        // Populate direct prerequisite edges
        if (prerequisites != null) {
            for (int[] edge : prerequisites) {
                isPrereq[edge[0]][edge[1]] = true;
            }
        }

        // Floyd-Warshall algorithm for transitive closure
        for (int k = 0; k < numCourses; k++) {
            for (int i = 0; i < numCourses; i++) {
                // Micro-optimization: only check outgoing edges if i reaches k
                if (isPrereq[i][k]) {
                    for (int j = 0; j < numCourses; j++) {
                        if (isPrereq[k][j]) {
                            isPrereq[i][j] = true;
                        }
                    }
                }
            }
        }

        // Answer each query in O(1)
        for (int[] query : queries) {
            result.add(isPrereq[query[0]][query[1]]);
        }

        return result;
    }

    public static void main(String[] args) {
        p1462 solver = new p1462();

        // Test Case 1: Direct prerequisite query
        int[][] prereqs1 = {{1, 0}};
        int[][] queries1 = {{0, 1}, {1, 0}};
        System.out.println("Test Case 1 Answers: " +
                solver.checkIfPrerequisite(2, prereqs1, queries1)); // Expected: [false, true]

        // Test Case 2: No prerequisites
        int[][] prereqs2 = {};
        int[][] queries2 = {{1, 0}, {0, 1}};
        System.out.println("Test Case 2 Answers: " +
                solver.checkIfPrerequisite(2, prereqs2, queries2)); // Expected: [false, false]

        // Test Case 3: Transitive chain 1 -> 2 -> 0
        int[][] prereqs3 = {{1, 2}, {1, 0}, {2, 0}};
        int[][] queries3 = {{1, 0}, {1, 2}};
        System.out.println("Test Case 3 Answers: " +
                solver.checkIfPrerequisite(3, prereqs3, queries3)); // Expected: [true, true]

        // Test Case 4: Longer chain 0 -> 1 -> 2 -> 3
        int[][] prereqs4 = {{0, 1}, {1, 2}, {2, 3}};
        int[][] queries4 = {{0, 3}, {3, 0}, {1, 3}, {2, 0}};
        System.out.println("Test Case 4 Answers: " +
                solver.checkIfPrerequisite(4, prereqs4, queries4)); // Expected: [true, false, true, false]
    }
}
