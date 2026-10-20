// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 198: House Robber
 * You are a professional robber planning to rob houses along a street.
 * Adjacent houses have security systems connected and will contact the police
 * if two adjacent houses are broken into on the same night.
 * Given an integer array nums representing money in each house, return the maximum amount
 * you can rob without alerting the police.
 *
 * Dynamic Programming Approach:
 * - At house i, choose either:
 *   1. Rob house i: gain nums[i] + max profit from up to house (i - 2).
 *   2. Skip house i: keep max profit from up to house (i - 1).
 * - Recurrence: dp[i] = max(nums[i] + dp[i - 2], dp[i - 1]).
 * - Space optimized to O(1) using two rolling variables.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class p198 {

    // Method to calculate maximum robbed money
    public int rob(int[] nums) {

        int rob1 = 0; // Represents max robbed up to house i - 2
        int rob2 = 0; // Represents max robbed up to house i - 1

        for (int num : nums) {
            // Maximum between robbing current house + rob1, or skipping current house (rob2)
            int currentMax = Math.max(num + rob1, rob2);
            rob1 = rob2;
            rob2 = currentMax;
        }

        return rob2;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p198 obj = new p198();

        // Sample input 1: rob house 1 (1) + house 3 (3) -> 4
        int[] nums1 = { 1, 2, 3, 1 };
        System.out.println("Max robbed amount 1: " + obj.rob(nums1));

        // Sample input 2: rob house 1 (2) + house 3 (9) + house 5 (1) -> 12
        int[] nums2 = { 2, 7, 9, 3, 1 };
        System.out.println("Max robbed amount 2: " + obj.rob(nums2));
    }
}
