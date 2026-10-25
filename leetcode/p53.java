// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 53: Maximum Subarray
 * Given an integer array nums, find the subarray with the largest sum, and return its sum.
 *
 * Kadane's Algorithm (Greedy / Dynamic Programming Approach):
 * - Maintain currentSum representing the maximum sum of a contiguous subarray ending at current index.
 * - At each element:
 *   currentSum = max(nums[i], currentSum + nums[i])
 *   (i.e., either start a fresh subarray at nums[i], or extend the existing subarray).
 * - Update maxSum whenever currentSum exceeds it.
 *
 * Time Complexity: O(n) single pass
 * Space Complexity: O(1) constant extra space
 */
public class p53 {

    // Method to find maximum subarray sum
    public int maxSubArray(int[] nums) {

        int currentSum = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // Either add current number to previous running sum or start fresh
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            // Track global maximum
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p53 obj = new p53();

        // Test case 1: [-2,1,-3,4,-1,2,1,-5,4] -> [4,-1,2,1] has largest sum = 6
        int[] nums1 = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
        System.out.println("Maximum subarray sum 1: " + obj.maxSubArray(nums1)); // 6

        // Test case 2: [1] -> 1
        int[] nums2 = { 1 };
        System.out.println("Maximum subarray sum 2: " + obj.maxSubArray(nums2)); // 1

        // Test case 3: [5,4,-1,7,8] -> 23
        int[] nums3 = { 5, 4, -1, 7, 8 };
        System.out.println("Maximum subarray sum 3: " + obj.maxSubArray(nums3)); // 23
    }
}
