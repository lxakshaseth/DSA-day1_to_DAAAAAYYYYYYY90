// Defines that this class belongs to the leetcode package
package leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

/*
 * LeetCode 994: Rotting Oranges
 *
 * Given an m x n grid where:
 * - 0 represents an empty cell,
 * - 1 represents a fresh orange,
 * - 2 represents a rotten orange.
 * Every minute, fresh oranges adjacent (4-directionally) to a rotten orange rot.
 * Return the minimum minutes until no fresh orange remains, or -1 if impossible.
 *
 * Optimal Approach: Multi-Source Breadth-First Search (BFS)
 * - Initial Pass:
 *   - Enqueue all initially rotten oranges (cells with value 2).
 *   - Count all fresh oranges (freshCount).
 *   - If freshCount == 0, no rotting is needed -> return 0 immediately.
 * - Multi-Source BFS Simulation:
 *   - Each level represents 1 minute of rotting propagation.
 *   - Process all rotten oranges at the current minute level (size = queue.size()).
 *   - For each 4-directional neighbor:
 *     - If it is a fresh orange (1), mark it rotten (2), decrement freshCount,
 *       and enqueue its coordinates for the next minute's propagation wave.
 *   - Increment elapsed minutes only while fresh oranges remain to rot.
 * - Completion Check:
 *   - If freshCount == 0, return the elapsed minutes.
 *   - Otherwise, isolated fresh oranges could not be reached -> return -1.
 *
 * Time Complexity: O(m * n) - Each cell is visited and enqueued at most once.
 * Space Complexity: O(m * n) - For the BFS queue in the worst-case.
 */
public class p994 {

    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    // Method to calculate the minimum minutes to rot all fresh oranges
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> queue = new ArrayDeque<>();
        int freshCount = 0;

        // Step 1: Enqueue all multi-source roots and count fresh oranges
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 2) {
                    queue.offer(new int[]{r, c});
                } else if (grid[r][c] == 1) {
                    freshCount++;
                }
            }
        }

        // If no fresh oranges existed at the start
        if (freshCount == 0) {
            return 0;
        }

        int minutes = 0;

        // Step 2: Level-by-level BFS
        while (!queue.isEmpty() && freshCount > 0) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int r = curr[0];
                int c = curr[1];

                for (int[] dir : DIRS) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    // Check boundaries and fresh status
                    if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] == 1) {
                        grid[nr][nc] = 2; // Mark rotten
                        freshCount--;
                        queue.offer(new int[]{nr, nc});
                    }
                }
            }

            minutes++;
        }

        return freshCount == 0 ? minutes : -1;
    }

    public static void main(String[] args) {
        p994 solver = new p994();

        // Test Case 1: Standard 3x3 grid taking 4 minutes
        int[][] grid1 = {
            {2, 1, 1},
            {1, 1, 0},
            {0, 1, 1}
        };
        System.out.println("Test Case 1 Minutes: " +
                solver.orangesRotting(grid1)); // Expected: 4

        // Test Case 2: Isolated fresh orange (impossible)
        int[][] grid2 = {
            {2, 1, 1},
            {0, 1, 1},
            {1, 0, 1}
        };
        System.out.println("Test Case 2 Minutes: " +
                solver.orangesRotting(grid2)); // Expected: -1

        // Test Case 3: Already zero fresh oranges
        int[][] grid3 = {{0, 2}};
        System.out.println("Test Case 3 Minutes: " +
                solver.orangesRotting(grid3)); // Expected: 0

        // Test Case 4: Multiple disjoint rotten sources
        int[][] grid4 = {
            {2, 1, 2},
            {1, 1, 1},
            {2, 1, 2}
        };
        System.out.println("Test Case 4 Minutes: " +
                solver.orangesRotting(grid4)); // Expected: 2
    }
}
