// Defines that this class belongs to the leetcode package
package leetcode;

// Import utility classes
import java.util.Arrays;

/*
 * LeetCode 300: Longest Increasing Subsequence
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 *
 * Dynamic Programming Approach:
 * - dp[i] represents the length of the longest increasing subsequence ending at index i.
 * - Initialize all entries in dp array to 1 (each individual element is a subsequence of length 1).
 * - For each element i, check all previous elements j < i:
 *   If nums[j] < nums[i], then dp[i] = max(dp[i], dp[j] + 1).
 *
 * Time Complexity: O(n^2) where n is the length of nums
 * Space Complexity: O(n) for the dp array
 */
public class p300 {

    // Method to find length of longest increasing subsequence
    public int lengthOfLIS(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int[] dp = new int[n];

        // Every individual element is an increasing subsequence of length 1
        Arrays.fill(dp, 1);

        int maxLength = 1;

        // Bottom-up DP: compare every pair (j, i) with j < i
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
        }

        return maxLength;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p300 obj = new p300();

        // Sample input 1: LIS is [2, 3, 7, 101] -> length 4
        int[] nums1 = { 10, 9, 2, 5, 3, 7, 101, 18 };
        System.out.println("LIS length 1: " + obj.lengthOfLIS(nums1));

        // Sample input 2: LIS is [0, 1, 2, 3] -> length 4
        int[] nums2 = { 0, 1, 0, 3, 2, 3 };
        System.out.println("LIS length 2: " + obj.lengthOfLIS(nums2));
    }
}
