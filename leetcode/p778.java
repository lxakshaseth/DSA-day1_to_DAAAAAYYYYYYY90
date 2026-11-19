// Defines that this class belongs to the leetcode package
package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * LeetCode 778: Swim in Rising Water
 *
 * Given an n x n integer matrix grid where grid[i][j] is the elevation at (i, j),
 * find the minimum time t such that you can swim from (0, 0) to (n - 1, n - 1).
 * You can move in 4 directions to an adjacent cell if and only if both cells have
 * elevation <= t.
 *
 * Optimal Approach: Dijkstra's Algorithm (Minimax Path with Min-Heap)
 * - The problem asks for a path from (0, 0) to (n - 1, n - 1) that minimizes the
 *   maximum cell elevation along the path.
 * - This is the classic Minimax Path problem, solved optimally via modified Dijkstra:
 *   - Priority queue stores state triplets [maxElevationSoFar, r, c], ordered ascending by maxElevationSoFar.
 *   - Start with [grid[0][0], 0, 0] and mark (0, 0) as visited.
 *   - Pop the cell with the minimum maximum elevation. If it reaches (n - 1, n - 1), return its time.
 *   - For each 4-directional neighbor (nr, nc):
 *       newTime = max(maxElevationSoFar, grid[nr][nc])
 *       Push [newTime, nr, nc] to the priority queue and mark visited.
 * - Greedy expansion guarantees that the first time (n - 1, n - 1) is popped from the min-heap,
 *   the path has the absolute minimum bottleneck elevation.
 *
 * Time Complexity: O(n^2 log n) where n is grid dimension (at most n^2 cells pushed/popped from heap).
 * Space Complexity: O(n^2) for the visited matrix and priority queue.
 */
public class p778 {

    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    // Method to find the minimum time to swim from top-left to bottom-right
    public int swimInWater(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int n = grid.length;
        boolean[][] visited = new boolean[n][n];

        // Min-heap ordered by the bottleneck time encountered along the path
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        pq.offer(new int[]{grid[0][0], 0, 0});
        visited[0][0] = true;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int time = curr[0];
            int r = curr[1];
            int c = curr[2];

            // Reached destination
            if (r == n - 1 && c == n - 1) {
                return time;
            }

            for (int[] dir : DIRS) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    pq.offer(new int[]{Math.max(time, grid[nr][nc]), nr, nc});
                }
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        p778 solver = new p778();

        // Test Case 1: 2x2 grid
        int[][] grid1 = {
            {0, 2},
            {1, 3}
        };
        System.out.println("Test Case 1 Least Time: " +
                solver.swimInWater(grid1)); // Expected: 3

        // Test Case 2: 5x5 spiral obstacle grid
        int[][] grid2 = {
            { 0,  1,  2,  3,  4},
            {24, 23, 22, 21,  5},
            {12, 13, 14, 15, 16},
            {11, 17, 18, 19, 20},
            {10,  9,  8,  7,  6}
        };
        System.out.println("Test Case 2 Least Time: " +
                solver.swimInWater(grid2)); // Expected: 16

        // Test Case 3: 1x1 grid
        int[][] grid3 = {{0}};
        System.out.println("Test Case 3 Least Time: " +
                solver.swimInWater(grid3)); // Expected: 0

        // Test Case 4: 3x3 diagonal gradient
        int[][] grid4 = {
            {0, 1, 5},
            {3, 4, 6},
            {2, 7, 8}
        };
        System.out.println("Test Case 4 Least Time: " +
                solver.swimInWater(grid4)); // Expected: 8
    }
}
