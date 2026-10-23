// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 200: Number of Islands
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water),
 * return the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally
 * or vertically. You may assume all four edges of the grid are surrounded by water.
 *
 * Depth-First Search (DFS) Approach:
 * - Iterate through every cell in the m x n grid.
 * - When a land cell '1' is encountered, increment the island count.
 * - Perform DFS to sink (change '1' to '0') the entire connected island.
 *
 * Time Complexity: O(m * n) where m is rows, n is columns
 * Space Complexity: O(m * n) for the recursion call stack in worst case (grid filled with land)
 */
public class p200 {

    // Helper DFS method to sink the entire island
    private void dfs(char[][] grid, int r, int c) {

        int rows = grid.length;
        int cols = grid[0].length;

        // Boundary check and water check
        if (r < 0 || r >= rows || c < 0 || c >= cols || grid[r][c] != '1') {
            return;
        }

        // Sink the land cell by marking it as '0'
        grid[r][c] = '0';

        // Recurse into all 4 cardinal directions
        dfs(grid, r + 1, c); // Down
        dfs(grid, r - 1, c); // Up
        dfs(grid, r, c + 1); // Right
        dfs(grid, r, c - 1); // Left
    }

    // Method to count the total number of islands
    public int numIslands(char[][] grid) {

        if (grid == null || grid.length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1') {
                    count++;
                    dfs(grid, r, c); // Sink the connected island
                }
            }
        }

        return count;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p200 obj = new p200();

        // Sample grid with 1 island
        char[][] grid1 = {
            { '1', '1', '1', '1', '0' },
            { '1', '1', '0', '1', '0' },
            { '1', '1', '0', '0', '0' },
            { '0', '0', '0', '0', '0' }
        };
        System.out.println("Number of islands (grid 1): " + obj.numIslands(grid1)); // 1

        // Sample grid with 3 islands
        char[][] grid2 = {
            { '1', '1', '0', '0', '0' },
            { '1', '1', '0', '0', '0' },
            { '0', '0', '1', '0', '0' },
            { '0', '0', '0', '1', '1' }
        };
        System.out.println("Number of islands (grid 2): " + obj.numIslands(grid2)); // 3
    }
}
