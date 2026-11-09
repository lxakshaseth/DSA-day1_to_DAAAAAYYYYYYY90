// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 36: Valid Sudoku
 * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated
 * according to the following rules:
 * 1. Each row must contain the digits 1-9 without repetition.
 * 2. Each column must contain the digits 1-9 without repetition.
 * 3. Each of the nine 3 x 3 sub-boxes must contain the digits 1-9 without repetition.
 *
 * Single-Pass Boolean Lookup Array Approach:
 * - Maintain three boolean tracking matrices of size 9x9:
 *   - rows[r][d]: true if digit d is already present in row r.
 *   - cols[c][d]: true if digit d is already present in column c.
 *   - boxes[b][d]: true if digit d is already present in 3x3 sub-box b.
 * - Sub-box index mapping: boxIndex = (r / 3) * 3 + (c / 3).
 * - For each non-empty cell (board[r][c] != '.'):
 *   - Extract digit index: d = board[r][c] - '1'.
 *   - If rows[r][d], cols[c][d], or boxes[boxIndex][d] is already true, return false (duplicate found).
 *   - Otherwise, mark all three as true.
 * - If entire board is traversed without conflict, return true.
 *
 * Time Complexity: O(1) fixed 9x9 = 81 iterations
 * Space Complexity: O(1) fixed 3 x 9 x 9 boolean table
 */
public class p36 {

    // Method to validate if the current 9x9 Sudoku board state is valid
    public boolean isValidSudoku(char[][] board) {

        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] boxes = new boolean[9][9];

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {

                char cell = board[r][c];
                if (cell == '.') {
                    continue; // Skip empty cells
                }

                int digit = cell - '1'; // Map '1'-'9' to index 0-8
                int boxIndex = (r / 3) * 3 + (c / 3);

                // Check for duplicate in row, column, or 3x3 sub-box
                if (rows[r][digit] || cols[c][digit] || boxes[boxIndex][digit]) {
                    return false;
                }

                // Record digit presence
                rows[r][digit] = true;
                cols[c][digit] = true;
                boxes[boxIndex][digit] = true;
            }
        }

        return true;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p36 obj = new p36();

        // Test case 1: Valid board
        char[][] board1 = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };
        System.out.println("Board 1 is valid: " + obj.isValidSudoku(board1)); // true

        // Test case 2: Invalid board (duplicate '8' in first column at [0,0] and [3,0])
        char[][] board2 = {
            {'8','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };
        System.out.println("Board 2 is valid: " + obj.isValidSudoku(board2)); // false
    }
}
