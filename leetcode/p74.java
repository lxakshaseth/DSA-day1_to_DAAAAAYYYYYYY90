// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 74: Search a 2D Matrix
 * You are given an m x n integer matrix with the following properties:
 * 1. Each row is sorted in non-decreasing order.
 * 2. The first integer of each row is greater than the last integer of the previous row.
 * Given an integer target, return true if target is in matrix or false otherwise.
 * Must run in O(log(m * n)) time complexity.
 *
 * Virtual 1D Binary Search Approach:
 * - Treat the m x n matrix as a single 1D sorted array of length (m * n).
 * - Map 1D index mid back to 2D matrix coordinates:
 *   row = mid / n
 *   col = mid % n
 *
 * Time Complexity: O(log(m * n))
 * Space Complexity: O(1)
 */
public class p74 {

    // Method to search for target in 2D sorted matrix
    public boolean searchMatrix(int[][] matrix, int target) {

        int m = matrix.length;
        int n = matrix[0].length;

        int low = 0;
        int high = m * n - 1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            // Map 1D mid index to row and column in 2D matrix
            int row = mid / n;
            int col = mid % n;
            int midVal = matrix[row][col];

            if (midVal == target) {
                return true;
            } else if (midVal < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return false;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p74 obj = new p74();

        // Sample 3x4 sorted matrix
        int[][] matrix = {
            { 1,  3,  5,  7 },
            { 10, 11, 16, 20 },
            { 23, 30, 34, 60 }
        };

        // Test case 1
        int target1 = 3;
        System.out.println("Target " + target1 + " present: " + obj.searchMatrix(matrix, target1));

        // Test case 2
        int target2 = 13;
        System.out.println("Target " + target2 + " present: " + obj.searchMatrix(matrix, target2));
    }
}
