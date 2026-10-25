// Defines that this class belongs to the leetcode package
package leetcode;

// Import utility classes
import java.util.Arrays;

/*
 * LeetCode 48: Rotate Image
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 * Must rotate the image in-place with O(1) extra memory.
 *
 * Transpose and Reverse Approach:
 * 1. Transpose the matrix:
 *    Swap elements across the main diagonal: matrix[i][j] <-> matrix[j][i].
 * 2. Reverse each row horizontally:
 *    Swap elements from start to end of each row.
 *
 * Time Complexity: O(n^2) where n is matrix dimension
 * Space Complexity: O(1) in-place auxiliary space
 */
public class p48 {

    // Method to rotate n x n matrix 90 degrees clockwise in-place
    public void rotate(int[][] matrix) {

        int n = matrix.length;

        // Step 1: Transpose the matrix (swap matrix[i][j] with matrix[j][i])
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Step 2: Reverse each row
        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = n - 1;
            while (left < right) {
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
                left++;
                right--;
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

        p48 obj = new p48();

        // Sample 3x3 matrix
        int[][] matrix = {
            { 1, 2, 3 },
            { 4, 5, 6 },
            { 7, 8, 9 }
        };

        System.out.println("Original Matrix:");
        printMatrix(matrix);

        obj.rotate(matrix);

        System.out.println("\nRotated 90 Degrees Clockwise:");
        printMatrix(matrix);
    }
}
