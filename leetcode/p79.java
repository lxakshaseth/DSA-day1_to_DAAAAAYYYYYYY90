// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 79: Word Search
 * Given an m x n grid of characters board and a string word, return true if word
 * exists in the grid. The word can be constructed from letters of sequentially adjacent
 * cells (horizontally or vertically neighboring). The same letter cell may not be used
 * more than once.
 *
 * Backtracking / Matrix DFS Approach:
 * - Iterate over every cell (r, c) on the board.
 * - When board[r][c] matches the first character of word, launch recursive DFS:
 *   1. Check boundary conditions: if out of bounds or character doesn't match word[index], return false.
 *   2. If index == word.length() - 1, full word matched -> return true.
 *   3. Mark cell as visited in-place by temporarily setting board[r][c] = '#' (avoids extra visited matrix).
 *   4. Explore all 4 orthogonal directions (up, down, left, right) with index + 1.
 *   5. Backtrack: restore board[r][c] back to original character.
 *
 * Time Complexity: O(m * n * 3^L) where m, n are board dimensions and L is word length
 * Space Complexity: O(L) recursion call stack depth (O(1) auxiliary space via in-place marking)
 */
public class p79 {

    // Helper DFS method for backtracking on the grid
    private boolean dfs(char[][] board, String word, int r, int c, int index) {

        // Base case: if characters don't match or out of bounds
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] != word.charAt(index)) {
            return false;
        }

        // If matched the entire word
        if (index == word.length() - 1) {
            return true;
        }

        // Mark current cell as visited in-place
        char temp = board[r][c];
        board[r][c] = '#';

        // Explore 4 directions: down, up, right, left
        boolean found = dfs(board, word, r + 1, c, index + 1) ||
                        dfs(board, word, r - 1, c, index + 1) ||
                        dfs(board, word, r, c + 1, index + 1) ||
                        dfs(board, word, r, c - 1, index + 1);

        // Undo choice (backtrack)
        board[r][c] = temp;

        return found;
    }

    // Method to check if word exists in grid
    public boolean exist(char[][] board, String word) {

        if (board == null || board.length == 0 || word == null || word.length() == 0) {
            return false;
        }

        int m = board.length;
        int n = board[0].length;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (board[r][c] == word.charAt(0)) {
                    if (dfs(board, word, r, c, 0)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p79 obj = new p79();

        // Sample board:
        // [
        //   ['A','B','C','E'],
        //   ['S','F','C','S'],
        //   ['A','D','E','E']
        // ]
        char[][] board1 = {
            { 'A', 'B', 'C', 'E' },
            { 'S', 'F', 'C', 'S' },
            { 'A', 'D', 'E', 'E' }
        };

        // Test case 1: "ABCCED" -> true
        System.out.println("Word \"ABCCED\" exists: " + obj.exist(board1, "ABCCED"));

        // Test case 2: "SEE" -> true
        System.out.println("Word \"SEE\" exists: " + obj.exist(board1, "SEE"));

        // Test case 3: "ABCB" -> false
        System.out.println("Word \"ABCB\" exists: " + obj.exist(board1, "ABCB"));
    }
}
