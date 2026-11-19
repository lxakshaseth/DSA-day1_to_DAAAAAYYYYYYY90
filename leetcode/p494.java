// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 494: Target Sum
 *
 * You are given an integer array nums and an integer target.
 * Assign '+' or '-' to each integer so that the evaluated expression equals target.
 * Return the number of distinct expressions that achieve target.
 *
 * Optimal Approach: Mathematical Reduction to 0/1 Subset Sum (1-D DP)
 * - Let P be the subset of elements with a '+' sign and N be the subset with a '-' sign:
 *     sum(P) - sum(N) = target
 *     sum(P) + sum(N) = totalSum
 * - Adding the equations:
 *     2 * sum(P) = totalSum + target  ==>  sum(P) = (totalSum + target) / 2
 * - Constraints & Early Pruning:
 *   1. If (totalSum + target) is negative or odd, return 0.
 *   2. If Math.abs(target) > totalSum, return 0.
 * - The problem reduces to counting subsets of nums whose sum equals subsetTarget = (totalSum + target) / 2.
 * - Maintain dp[s]: number of subsets that sum to s.
 *   Base Case: dp[0] = 1 (empty subset sums to 0).
 *   For each num in nums, iterate backwards from subsetTarget down to num:
 *       dp[s] += dp[s - num]
 * - Traversing down to num (including when num == 0) correctly doubles combinations for zeros (+0 and -0).
 *
 * Time Complexity: O(n * subsetTarget) where n <= 20 and subsetTarget <= 1000.
 * Space Complexity: O(subsetTarget) auxiliary space using a 1-D array.
 */
public class p494 {

    // Method to calculate the number of expressions evaluating to target
    public int findTargetSumWays(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        // Target cannot be achieved if out of range or if (totalSum + target) is odd
        if (Math.abs(target) > totalSum || (totalSum + target) % 2 != 0) {
            return 0;
        }

        int subsetTarget = (totalSum + target) / 2;
        int[] dp = new int[subsetTarget + 1];
        dp[0] = 1;

        for (int num : nums) {
            for (int s = subsetTarget; s >= num; s--) {
                dp[s] += dp[s - num];
            }
        }

        return dp[subsetTarget];
    }

    public static void main(String[] args) {
        p494 solver = new p494();

        // Test Case 1: Standard example
        int[] nums1 = {1, 1, 1, 1, 1};
        System.out.println("Test Case 1 Ways (target = 3): " +
                solver.findTargetSumWays(nums1, 3)); // Expected: 5

        // Test Case 2: Single element matching target
        int[] nums2 = {1};
        System.out.println("Test Case 2 Ways (target = 1): " +
                solver.findTargetSumWays(nums2, 1)); // Expected: 1

        // Test Case 3: Elements containing zeros
        int[] nums3 = {0, 0, 1};
        System.out.println("Test Case 3 Ways (target = 1): " +
                solver.findTargetSumWays(nums3, 1)); // Expected: 4

        // Test Case 4: Target exceeds total sum
        int[] nums4 = {2, 3};
        System.out.println("Test Case 4 Ways (target = 10): " +
                solver.findTargetSumWays(nums4, 10)); // Expected: 0
    }
}
