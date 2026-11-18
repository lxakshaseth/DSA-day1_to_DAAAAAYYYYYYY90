// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 329: Longest Increasing Path in a Matrix
 *
 * Given an m x n integer matrix, return the length of the longest increasing path.
 * From each cell, you can move in four directions: up, down, left, right.
 * You may not move diagonally or outside the grid.
 *
 * Optimal Approach: Depth-First Search (DFS) with Memoization (DAG DP)
 * - Because any valid path requires strictly increasing values (matrix[nr][nc] > matrix[r][c]),
 *   no cycle can exist. The grid forms a Directed Acyclic Graph (DAG).
 * - On a DAG, the longest path starting from any node can be found via DFS + Memoization:
 *   - memo[r][c] stores the longest increasing path starting at cell (r, c).
 *   - If memo[r][c] > 0, return the cached result.
 *   - Otherwise, initialize maxLen = 1 (the cell itself).
 *   - Explore all 4 adjacent neighbors (nr, nc) where matrix[nr][nc] > matrix[r][c]:
 *       maxLen = Math.max(maxLen, 1 + dfs(nr, nc))
 *   - Cache and return memo[r][c] = maxLen.
 * - Iterate over all m * n cells and return the global maximum path length.
 *
 * Time Complexity: O(m * n) - Each cell and transition is evaluated once and cached.
 * Space Complexity: O(m * n) - For the memoization table and recursion call stack depth.
 */
public class p329 {

    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    // Method to calculate the length of the longest increasing path
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int m = matrix.length;
        int n = matrix[0].length;
        int[][] memo = new int[m][n];
        int maxPath = 0;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                maxPath = Math.max(maxPath, dfs(matrix, r, c, memo, m, n));
            }
        }

        return maxPath;
    }

    private int dfs(int[][] matrix, int r, int c, int[][] memo, int m, int n) {
        if (memo[r][c] != 0) {
            return memo[r][c];
        }

        int maxLen = 1;

        for (int[] dir : DIRS) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            // Only visit strictly increasing neighbors within bounds
            if (nr >= 0 && nr < m && nc >= 0 && nc < n && matrix[nr][nc] > matrix[r][c]) {
                maxLen = Math.max(maxLen, 1 + dfs(matrix, nr, nc, memo, m, n));
            }
        }

        memo[r][c] = maxLen;
        return maxLen;
    }

    public static void main(String[] args) {
        p329 solver = new p329();

        // Test Case 1: [1, 2, 6, 9] -> length 4
        int[][] matrix1 = {
            {9, 9, 4},
            {6, 6, 8},
            {2, 1, 1}
        };
        System.out.println("Test Case 1 Longest Path: " +
                solver.longestIncreasingPath(matrix1)); // Expected: 4

        // Test Case 2: [3, 4, 5, 6] -> length 4
        int[][] matrix2 = {
            {3, 4, 5},
            {3, 2, 6},
            {2, 2, 1}
        };
        System.out.println("Test Case 2 Longest Path: " +
                solver.longestIncreasingPath(matrix2)); // Expected: 4

        // Test Case 3: Single element
        int[][] matrix3 = {{1}};
        System.out.println("Test Case 3 Longest Path: " +
                solver.longestIncreasingPath(matrix3)); // Expected: 1

        // Test Case 4: Flat values (all identical, no strictly increasing path > 1)
        int[][] matrix4 = {
            {7, 7},
            {7, 7}
        };
        System.out.println("Test Case 4 Longest Path: " +
                solver.longestIncreasingPath(matrix4)); // Expected: 1
    }
}
