// Defines that this class belongs to the leetcode package
package leetcode;

// Import utility classes
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * LeetCode 417: Pacific Atlantic Water Flow
 * There is an m x n rectangular island that borders both the Pacific Ocean (top and left edges)
 * and Atlantic Ocean (bottom and right edges). Water can flow from a cell to neighboring cells
 * (north, south, east, west) if the neighboring cell's height is less than or equal to current cell's height.
 * Return a list of grid coordinates where water can flow to BOTH the Pacific and Atlantic oceans.
 *
 * Reverse Multi-Source Depth-First Search (DFS) Approach:
 * - Instead of simulating water flowing downhill from each internal cell to the oceans (which takes O((m*n)^2)),
 *   we reverse the flow: start from the ocean borders and flow uphill (heights[next] >= heights[curr]).
 * - Maintain two boolean visited grids:
 *   1. pacific[r][c]: true if water from cell (r, c) can reach the Pacific Ocean.
 *   2. atlantic[r][c]: true if water from cell (r, c) can reach the Atlantic Ocean.
 * - Launch DFS from all Pacific border cells (top row and left column).
 * - Launch DFS from all Atlantic border cells (bottom row and right column).
 * - Any cell where both pacific[r][c] and atlantic[r][c] are true can reach both oceans.
 *
 * Time Complexity: O(m * n) where m is rows, n is columns (each cell visited at most twice)
 * Space Complexity: O(m * n) for visited boolean arrays and DFS recursion call stack
 */
public class p417 {

    // Helper DFS method to mark all cells reachable moving uphill
    private void dfs(int[][] heights, boolean[][] reachable, int r, int c) {

        reachable[r][c] = true;

        int m = heights.length;
        int n = heights[0].length;

        // 4 cardinal directions: Down, Up, Right, Left
        int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        for (int[] dir : directions) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            // Check grid bounds, visited status, and uphill condition (heights[nr][nc] >= heights[r][c])
            if (nr >= 0 && nr < m && nc >= 0 && nc < n && !reachable[nr][nc] && heights[nr][nc] >= heights[r][c]) {
                dfs(heights, reachable, nr, nc);
            }
        }
    }

    // Method to find all cells that can flow to both Pacific and Atlantic oceans
    public List<List<Integer>> pacificAtlantic(int[][] heights) {

        List<List<Integer>> result = new ArrayList<>();

        if (heights == null || heights.length == 0 || heights[0].length == 0) {
            return result;
        }

        int m = heights.length;
        int n = heights[0].length;

        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];

        // DFS from top and bottom ocean borders
        for (int c = 0; c < n; c++) {
            dfs(heights, pacific, 0, c);        // Top border (Pacific)
            dfs(heights, atlantic, m - 1, c);    // Bottom border (Atlantic)
        }

        // DFS from left and right ocean borders
        for (int r = 0; r < m; r++) {
            dfs(heights, pacific, r, 0);        // Left border (Pacific)
            dfs(heights, atlantic, r, n - 1);    // Right border (Atlantic)
        }

        // Intersect cells reachable by both oceans
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (pacific[r][c] && atlantic[r][c]) {
                    result.add(Arrays.asList(r, c));
                }
            }
        }

        return result;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p417 obj = new p417();

        // Test case 1:
        // [
        //   [1, 2, 2, 3, 5],
        //   [3, 2, 3, 4, 4],
        //   [2, 4, 5, 3, 1],
        //   [6, 7, 1, 4, 5],
        //   [5, 1, 1, 2, 4]
        // ]
        // Expected: [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]]
        int[][] heights1 = {
            { 1, 2, 2, 3, 5 },
            { 3, 2, 3, 4, 4 },
            { 2, 4, 5, 3, 1 },
            { 6, 7, 1, 4, 5 },
            { 5, 1, 1, 2, 4 }
        };
        System.out.println("Cells flowing to both oceans (grid 1): " + obj.pacificAtlantic(heights1));

        // Test case 2: [[1]] -> [[0, 0]]
        int[][] heights2 = { { 1 } };
        System.out.println("Cells flowing to both oceans (grid 2): " + obj.pacificAtlantic(heights2));
    }
}
