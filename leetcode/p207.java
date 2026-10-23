// Defines that this class belongs to the leetcode package
package leetcode;

// Import utility classes
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * LeetCode 207: Course Schedule
 * There are a total of numCourses courses labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [a, b] indicates that
 * you must take course b first if you want to take course a.
 * Return true if you can finish all courses. Otherwise, return false.
 *
 * Topological Sort (Kahn's Algorithm - BFS) Approach:
 * - A directed edge b -> a represents taking b before a.
 * - Compute in-degrees (number of prerequisite dependencies) for all courses.
 * - Add all courses with 0 in-degree into a queue.
 * - Process nodes level by level: decrement in-degrees of dependent courses.
 * - If all courses can be processed without encountering a cycle, return true.
 *
 * Time Complexity: O(V + E) where V = numCourses, E = prerequisites.length
 * Space Complexity: O(V + E) for adjacency list and queue
 */
public class p207 {

    // Method to check if all courses can be finished
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        // Build adjacency list graph
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        // Array to track in-degrees (number of prerequisites for each course)
        int[] inDegree = new int[numCourses];

        for (int[] pre : prerequisites) {
            int course = pre[0];
            int prerequisite = pre[1];
            adj.get(prerequisite).add(course); // Directed edge: prerequisite -> course
            inDegree[course]++;
        }

        // Queue for courses with no prerequisites (inDegree == 0)
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        int completedCourses = 0;

        // BFS traversal
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            completedCourses++;

            // Reduce dependency count for all courses requiring curr
            for (int neighbor : adj.get(curr)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // If completed courses count equals total courses, no cycle exists
        return completedCourses == numCourses;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p207 obj = new p207();

        // Test case 1: 2 courses, [1, 0] -> Can take 0 then 1
        int numCourses1 = 2;
        int[][] pre1 = { { 1, 0 } };
        System.out.println("Can finish 1: " + obj.canFinish(numCourses1, pre1)); // true

        // Test case 2: 2 courses, [1, 0] and [0, 1] -> Cycle!
        int numCourses2 = 2;
        int[][] pre2 = { { 1, 0 }, { 0, 1 } };
        System.out.println("Can finish 2: " + obj.canFinish(numCourses2, pre2)); // false
    }
}
