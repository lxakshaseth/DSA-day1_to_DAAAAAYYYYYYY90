// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 312: Burst Balloons
 *
 * Given n balloons with values nums[i], bursting balloon i yields
 * nums[i - 1] * nums[i] * nums[i + 1] coins (with out-of-bounds treated as 1).
 * Return the maximum coins collectible by bursting all balloons.
 *
 * Optimal Approach: Interval Dynamic Programming (Reverse "Last Balloon Burst")
 * - Forward thinking makes subproblems dependent because remaining balloons merge together.
 * - Thinking in reverse: consider which balloon k is burst LAST within the open interval (left, right).
 * - Add boundary balloons with value 1 at index 0 and index n + 1:
 *     vals = [1, nums[0], nums[1], ..., nums[n-1], 1] of size n + 2.
 * - Let dp[left][right] be the max coins collected by bursting all balloons strictly between left and right:
 *     dp[left][right] = max_{left < k < right} (dp[left][k] + dp[k][right] + vals[left] * vals[k] * vals[right])
 * - Because k is the last balloon to be burst in (left, right), its adjacent neighbors at the moment
 *   it is burst are guaranteed to be vals[left] and vals[right].
 * - Iterate by increasing interval length len = right - left from 2 to n + 1.
 *
 * Time Complexity: O(n^3) where n <= 300 (roughly (n^3)/6 operations).
 * Space Complexity: O(n^2) for the 2-D DP table.
 */
public class p312 {

    // Method to calculate the maximum coins collected from bursting balloons
    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        // Pad array with boundary balloons of value 1
        int[] vals = new int[n + 2];
        vals[0] = 1;
        vals[n + 1] = 1;
        for (int i = 0; i < n; i++) {
            vals[i + 1] = nums[i];
        }

        // dp[left][right] represents max coins in open interval (left, right)
        int[][] dp = new int[n + 2][n + 2];

        // len is the distance between left and right (right - left)
        for (int len = 2; len <= n + 1; len++) {
            for (int left = 0; left <= n + 1 - len; left++) {
                int right = left + len;

                // Pick balloon k to be burst last in (left, right)
                for (int k = left + 1; k < right; k++) {
                    int coins = dp[left][k] + dp[k][right] + (vals[left] * vals[k] * vals[right]);
                    dp[left][right] = Math.max(dp[left][right], coins);
                }
            }
        }

        return dp[0][n + 1];
    }

    public static void main(String[] args) {
        p312 solver = new p312();

        // Test Case 1: [3, 1, 5, 8]
        int[] nums1 = {3, 1, 5, 8};
        System.out.println("Test Case 1 Max Coins: " +
                solver.maxCoins(nums1)); // Expected: 167

        // Test Case 2: [1, 5]
        int[] nums2 = {1, 5};
        System.out.println("Test Case 2 Max Coins: " +
                solver.maxCoins(nums2)); // Expected: 10

        // Test Case 3: Single balloon [9]
        int[] nums3 = {9};
        System.out.println("Test Case 3 Max Coins: " +
                solver.maxCoins(nums3)); // Expected: 9 (1 * 9 * 1)

        // Test Case 4: Array with zeros
        int[] nums4 = {0, 3, 0};
        System.out.println("Test Case 4 Max Coins: " +
                solver.maxCoins(nums4)); // Expected: 3
    }
}
