// Defines that this class belongs to the leetcode package
package leetcode;

// Import utility classes
import java.util.ArrayList;
import java.util.List;

/*
 * LeetCode 54: Spiral Matrix
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 *
 * Four-Boundary Simulation Approach:
 * - Define 4 dynamic boundaries: top, bottom, left, right.
 * - In each spiral loop:
 *   1. Traverse from left to right along top boundary, then increment top.
 *   2. Traverse from top to bottom along right boundary, then decrement right.
 *   3. If top <= bottom, traverse from right to left along bottom boundary, then decrement bottom.
 *   4. If left <= right, traverse from bottom to top along left boundary, then increment left.
 *
 * Time Complexity: O(m * n) visiting each cell exactly once
 * Space Complexity: O(1) auxiliary space (excluding returned result list)
 */
public class p54 {

    // Method to return matrix elements in spiral order
    public List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> result = new ArrayList<>();

        if (matrix == null || matrix.length == 0) {
            return result;
        }

        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {

            // 1. Traverse across top row from left to right
            for (int col = left; col <= right; col++) {
                result.add(matrix[top][col]);
            }
            top++; // Move top boundary downward

            // 2. Traverse down rightmost column from top to bottom
            for (int row = top; row <= bottom; row++) {
                result.add(matrix[row][right]);
            }
            right--; // Move right boundary leftward

            // 3. Traverse across bottom row from right to left (if valid row exists)
            if (top <= bottom) {
                for (int col = right; col >= left; col--) {
                    result.add(matrix[bottom][col]);
                }
                bottom--; // Move bottom boundary upward
            }

            // 4. Traverse up leftmost column from bottom to top (if valid column exists)
            if (left <= right) {
                for (int row = bottom; row >= top; row--) {
                    result.add(matrix[row][left]);
                }
                left++; // Move left boundary rightward
            }
        }

        return result;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p54 obj = new p54();

        // Sample 3x3 matrix:
        // [1, 2, 3]
        // [4, 5, 6]
        // [7, 8, 9]
        // Spiral order: [1, 2, 3, 6, 9, 8, 7, 4, 5]
        int[][] matrix1 = {
            { 1, 2, 3 },
            { 4, 5, 6 },
            { 7, 8, 9 }
        };
        System.out.println("Spiral order 1: " + obj.spiralOrder(matrix1));

        // Sample 3x4 matrix:
        // [1,  2,  3,  4]
        // [5,  6,  7,  8]
        // [9, 10, 11, 12]
        // Spiral order: [1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]
        int[][] matrix2 = {
            { 1, 2, 3, 4 },
            { 5, 6, 7, 8 },
            { 9, 10, 11, 12 }
        };
        System.out.println("Spiral order 2: " + obj.spiralOrder(matrix2));
    }
}
