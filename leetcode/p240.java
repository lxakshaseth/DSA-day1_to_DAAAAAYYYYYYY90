package leetcode;

/**
 * LeetCode 240: Search a 2D Matrix II
 * https://leetcode.com/problems/search-a-2d-matrix-ii/
 *
 * Write an efficient algorithm that searches for a value target in an m x n integer matrix.
 * The matrix has the following properties:
 * - Integers in each row are sorted in ascending from left to right.
 * - Integers in each column are sorted in ascending from top to bottom.
 */
public class p240 {

    /**
     * Stepwise Search / Elimination starting from Top-Right Corner
     *
     * At matrix[r][c]:
     * - If target == matrix[r][c], found!
     * - If target < matrix[r][c], target cannot exist in column c (all below are larger), prune c--.
     * - If target > matrix[r][c], target cannot exist in row r (all left are smaller), prune r++.
     *
     * Time Complexity: O(m + n)
     * Space Complexity: O(1)
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        int r = 0;
        int c = n - 1;

        while (r < m && c >= 0) {
            int current = matrix[r][c];
            if (current == target) {
                return true;
            } else if (current > target) {
                c--; // Prune current column
            } else {
                r++; // Prune current row
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println("Testing LeetCode 240: Search a 2D Matrix II...");
        p240 solver = new p240();

        int[][] matrix1 = {
            {1,   4,  7, 11, 15},
            {2,   5,  8, 12, 19},
            {3,   6,  9, 16, 22},
            {10, 13, 14, 17, 24},
            {18, 21, 23, 26, 30}
        };

        // Test 1: Target present (5)
        boolean res1 = solver.searchMatrix(matrix1, 5);
        assert res1 : "Expected true for target 5";
        System.out.println("Test 1: target 5 -> " + res1 + " (Passed)");

        // Test 2: Target not present (20)
        boolean res2 = solver.searchMatrix(matrix1, 20);
        assert !res2 : "Expected false for target 20";
        System.out.println("Test 2: target 20 -> " + res2 + " (Passed)");

        // Test 3: Corner cases (top-left, bottom-right)
        assert solver.searchMatrix(matrix1, 1) : "Expected true for target 1 (top-left)";
        assert solver.searchMatrix(matrix1, 30) : "Expected true for target 30 (bottom-right)";
        assert solver.searchMatrix(matrix1, 15) : "Expected true for target 15 (top-right)";
        assert solver.searchMatrix(matrix1, 18) : "Expected true for target 18 (bottom-left)";
        System.out.println("Test 3: All corners found (Passed)");

        // Test 4: Values outside range (smaller than min, greater than max)
        assert !solver.searchMatrix(matrix1, 0) : "Expected false for target 0";
        assert !solver.searchMatrix(matrix1, 100) : "Expected false for target 100";
        System.out.println("Test 4: Out-of-bounds targets (Passed)");

        // Test 5: 1x1 matrix
        int[][] single = {{42}};
        assert solver.searchMatrix(single, 42) : "Expected true for single element 42";
        assert !solver.searchMatrix(single, 10) : "Expected false for single element 10";
        System.out.println("Test 5: 1x1 matrix (Passed)");

        System.out.println("All test cases passed successfully!");
    }
}
