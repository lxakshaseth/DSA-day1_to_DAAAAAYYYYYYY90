// Defines that this class belongs to the leetcode package
package leetcode;

// Import utility classes
import java.util.Arrays;

/*
 * LeetCode 62: Unique Paths
 * There is a robot on an m x n grid. The robot is initially located at the top-left corner (0, 0).
 * The robot tries to move to the bottom-right corner (m - 1, n - 1).
 * The robot can only move either down or right at any point in time.
 * Given the two integers m and n, return the number of possible unique paths.
 *
 * 2-D Dynamic Programming (Space-Optimized to 1-D):
 * - Number of ways to reach cell (r, c) = ways from above (r - 1, c) + ways from left (r, c - 1).
 * - Since each row only depends on the current row and previous row,
 *   we can optimize space to a single array of size n.
 *
 * Time Complexity: O(m * n) where m is rows, n is columns
 * Space Complexity: O(n) space-optimized 1-D array
 */
public class p62 {

    // Method to calculate unique paths
    public int uniquePaths(int m, int n) {

        // 1-D array representing the previous row
        int[] row = new int[n];

        // Base case: There is 1 path along the top row (only moving right)
        Arrays.fill(row, 1);

        // Iterate through remaining rows
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // row[j] = value from above row (current row[j]) + value from left (row[j - 1])
                row[j] = row[j] + row[j - 1];
            }
        }

        return row[n - 1];
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p62 obj = new p62();

        // Test case 1: 3 x 7 grid -> 28 unique paths
        int m1 = 3, n1 = 7;
        System.out.println("Unique paths (3x7): " + obj.uniquePaths(m1, n1)); // 28

        // Test case 2: 3 x 2 grid -> 3 unique paths
        int m2 = 3, n2 = 2;
        System.out.println("Unique paths (3x2): " + obj.uniquePaths(m2, n2)); // 3
    }
}
