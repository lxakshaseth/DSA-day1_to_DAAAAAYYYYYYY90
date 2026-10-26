// Defines that this class belongs to the leetcode package
package leetcode;

// Import utility classes
import java.util.Arrays;

/*
 * LeetCode 73: Set Matrix Zeroes
 * Given an m x n integer matrix, if an element is 0, set its entire row and column to 0's.
 * Must modify the matrix in place with O(1) constant auxiliary space.
 *
 * In-Place First Row/Column Marker Approach:
 * - Use matrix[0][c] to mark whether column c should be zeroed.
 * - Use matrix[r][0] to mark whether row r should be zeroed.
 * - Since matrix[0][0] overlaps, use a separate boolean flag (firstRowZero) for the 0th row.
 * - Step 1: Scan matrix and mark first row & column.
 * - Step 2: Use markers to update inner cells (1 to m-1, 1 to n-1).
 * - Step 3: Update first column if matrix[0][0] == 0.
 * - Step 4: Update first row if firstRowZero is true.
 *
 * Time Complexity: O(m * n) where m is rows, n is columns
 * Space Complexity: O(1) in-place auxiliary space
 */
public class p73 {

    // Method to set matrix zeroes in-place
    public void setZeroes(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;
        boolean firstRowZero = false;

        // Step 1: Determine which rows and columns must be zeroed
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (matrix[r][c] == 0) {
                    // Mark the column in the top row
                    matrix[0][c] = 0;

                    // Mark the row in the leftmost column
                    if (r > 0) {
                        matrix[r][0] = 0;
                    } else {
                        firstRowZero = true;
                    }
                }
            }
        }

        // Step 2: Zero out cells based on top row and left column markers
        for (int r = 1; r < m; r++) {
            for (int c = 1; c < n; c++) {
                if (matrix[r][0] == 0 || matrix[0][c] == 0) {
                    matrix[r][c] = 0;
                }
            }
        }

        // Step 3: Zero out the first column if needed
        if (matrix[0][0] == 0) {
            for (int r = 0; r < m; r++) {
                matrix[r][0] = 0;
            }
        }

        // Step 4: Zero out the first row if needed
        if (firstRowZero) {
            for (int c = 0; c < n; c++) {
                matrix[0][c] = 0;
            }
        }
    }

    // Helper method to print matrix
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p73 obj = new p73();

        // Sample 3x3 matrix:
        // [1, 1, 1]
        // [1, 0, 1]
        // [1, 1, 1]
        int[][] matrix1 = {
            { 1, 1, 1 },
            { 1, 0, 1 },
            { 1, 1, 1 }
        };

        System.out.println("Original Matrix:");
        printMatrix(matrix1);

        obj.setZeroes(matrix1);

        System.out.println("\nMatrix After setZeroes:");
        printMatrix(matrix1);
    }
}
