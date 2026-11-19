// Defines that this class belongs to the leetcode package
package leetcode;

import java.util.Arrays;

/*
 * LeetCode 130: Surrounded Regions
 *
 * Given an m x n matrix board containing 'X' and 'O', capture all regions that are
 * 4-directionally surrounded by 'X' by flipping all surrounded 'O's into 'X's.
 * Any 'O' that is on the border or connected to a border 'O' is NOT captured.
 *
 * Optimal Approach: Reverse Flood Fill / Boundary DFS
 * - Any 'O' connected to the 4 outer borders of the board cannot be captured.
 * - Reverse thinking:
 *   1. Traverse all border cells (top row, bottom row, left column, right column).
 *   2. When a border cell contains 'O', trigger a Depth-First Search (DFS) to mark
 *      all connected 'O' cells as safe using a temporary marker 'S'.
 *   3. After visiting all border-connected components, iterate across the entire board:
 *      - Remaining 'O' cells are surrounded -> flip them to 'X'.
 *      - Cells marked 'S' are safe -> restore them to 'O'.
 *
 * Time Complexity: O(m * n) - Every cell is visited a constant number of times.
 * Space Complexity: O(m * n) - Maximum recursion call stack depth in the worst case.
 */
public class p130 {

    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    // Method to capture surrounded regions in-place
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }

        int m = board.length;
        int n = board[0].length;

        // Step 1: Mark border-connected 'O' cells as safe ('S')
        for (int r = 0; r < m; r++) {
            if (board[r][0] == 'O') {
                dfs(board, r, 0, m, n);
            }
            if (board[r][n - 1] == 'O') {
                dfs(board, r, n - 1, m, n);
            }
        }

        for (int c = 0; c < n; c++) {
            if (board[0][c] == 'O') {
                dfs(board, 0, c, m, n);
            }
            if (board[m - 1][c] == 'O') {
                dfs(board, m - 1, c, m, n);
            }
        }

        // Step 2: Flip captured 'O's to 'X', and restore safe 'S's to 'O'
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (board[r][c] == 'O') {
                    board[r][c] = 'X';
                } else if (board[r][c] == 'S') {
                    board[r][c] = 'O';
                }
            }
        }
    }

    private void dfs(char[][] board, int r, int c, int m, int n) {
        if (r < 0 || r >= m || c < 0 || c >= n || board[r][c] != 'O') {
            return;
        }

        board[r][c] = 'S'; // Mark safe

        for (int[] dir : DIRS) {
            dfs(board, r + dir[0], c + dir[1], m, n);
        }
    }

    public static void main(String[] args) {
        p130 solver = new p130();

        // Test Case 1: Standard 4x4 board
        char[][] board1 = {
            {'X', 'X', 'X', 'X'},
            {'X', 'O', 'O', 'X'},
            {'X', 'X', 'O', 'X'},
            {'X', 'O', 'X', 'X'}
        };
        solver.solve(board1);
        System.out.println("Test Case 1 Board Output:");
        for (char[] row : board1) {
            System.out.println(Arrays.toString(row));
        }
        /* Expected:
         * [X, X, X, X]
         * [X, X, X, X]
         * [X, X, X, X]
         * [X, O, X, X]
         */

        // Test Case 2: 1x1 board
        char[][] board2 = {{'X'}};
        solver.solve(board2);
        System.out.println("Test Case 2 Board Output: " + Arrays.toString(board2[0])); // Expected: [X]

        // Test Case 3: Fully enclosed center 'O'
        char[][] board3 = {
            {'X', 'X', 'X'},
            {'X', 'O', 'X'},
            {'X', 'X', 'X'}
        };
        solver.solve(board3);
        System.out.println("Test Case 3 Board Output:");
        for (char[] row : board3) {
            System.out.println(Arrays.toString(row));
        }
    }
}
