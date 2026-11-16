// Defines that this class belongs to the leetcode package
package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * LeetCode 51: N-Queens
 *
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard
 * such that no two queens attack each other. Return all distinct board configurations.
 *
 * Optimal Approach: Row-by-Row Backtracking with Bitmask Pruning
 * - Place one queen per row from row 0 to row n - 1, naturally preventing row conflicts.
 * - For row r, test each column c from 0 to n - 1.
 * - Conflicts with previous queens are checked in O(1) time using bitmasks:
 *   1. colMask: Tracks occupied columns (bit c).
 *   2. diag1Mask: Tracks occupied main diagonals (r - c + n - 1).
 *   3. diag2Mask: Tracks occupied anti-diagonals (r + c).
 * - Keep track of queen column indices in queens[r] = c.
 * - When r reaches n, convert queen positions into strings of '.' and 'Q' and record the solution.
 * - Backtrack: Clear the respective bits and try the next column.
 *
 * Time Complexity: O(n!) - Bounded by permutations with aggressive diagonal pruning.
 * Space Complexity: O(n) auxiliary space for recursion call stack and queen positions.
 */
public class p51 {

    // Method to solve the N-Queens puzzle and return all valid board states
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<>();
        if (n <= 0) {
            return solutions;
        }

        int[] queens = new int[n];
        backtrack(0, n, 0, 0, 0, queens, solutions);
        return solutions;
    }

    private void backtrack(int r, int n, int colMask, int diag1Mask, int diag2Mask,
                           int[] queens, List<List<String>> solutions) {
        // Base case: all n queens have been successfully placed
        if (r == n) {
            solutions.add(constructBoard(queens, n));
            return;
        }

        for (int c = 0; c < n; c++) {
            int d1 = r - c + n - 1;
            int d2 = r + c;

            // Check if column c, main diagonal d1, or anti-diagonal d2 is under attack
            if ((colMask & (1 << c)) == 0 &&
                (diag1Mask & (1 << d1)) == 0 &&
                (diag2Mask & (1 << d2)) == 0) {

                // Place queen at (r, c) and set bitmasks
                queens[r] = c;
                backtrack(r + 1, n,
                          colMask | (1 << c),
                          diag1Mask | (1 << d1),
                          diag2Mask | (1 << d2),
                          queens, solutions);
                // Backtrack: no explicit array wipe needed as queens[r] will be overwritten
            }
        }
    }

    // Helper method to build the board strings from queen positions
    private List<String> constructBoard(int[] queens, int n) {
        List<String> board = new ArrayList<>(n);
        char[] rowChars = new char[n];

        for (int r = 0; r < n; r++) {
            Arrays.fill(rowChars, '.');
            rowChars[queens[r]] = 'Q';
            board.add(new String(rowChars));
        }

        return board;
    }

    public static void main(String[] args) {
        p51 solver = new p51();

        // Test Case 1: n = 4 (should produce 2 solutions)
        List<List<String>> res4 = solver.solveNQueens(4);
        System.out.println("Test Case 1 (n = 4) Solution Count: " + res4.size());
        for (List<String> b : res4) {
            System.out.println(b);
        }

        // Test Case 2: n = 1 (should produce 1 solution)
        List<List<String>> res1 = solver.solveNQueens(1);
        System.out.println("Test Case 2 (n = 1): " + res1);

        // Test Case 3: n = 2 and n = 3 (no solutions exist)
        System.out.println("Test Case 3 (n = 2): " + solver.solveNQueens(2).size()); // Expected: 0
        System.out.println("Test Case 4 (n = 3): " + solver.solveNQueens(3).size()); // Expected: 0
    }
}
