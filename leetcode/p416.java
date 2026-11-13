// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 416: Partition Equal Subset Sum
 *
 * Given an integer array nums, return true if you can partition the array into
 * two subsets such that the sum of the elements in both subsets is equal, or false otherwise.
 *
 * Optimal Approach: 0/1 Knapsack Dynamic Programming (1-D Space-Optimized)
 * - Let totalSum be the sum of all elements in nums.
 * - If totalSum is odd, it is impossible to partition into two equal integer subsets -> return false.
 * - Set target = totalSum / 2. If any element exceeds target -> return false.
 * - Maintain a 1-D boolean array dp where dp[s] indicates whether a subset sum of s is achievable.
 * - Base Case: dp[0] = true (sum of 0 is always achievable with the empty subset).
 * - For each number num in nums, iterate backwards from target down to num:
 *     dp[s] = dp[s] || dp[s - num]
 * - Traversing backwards ensures that each number is used at most once (0/1 Knapsack constraint).
 * - Short-circuit: if dp[target] becomes true at any point, return true immediately.
 *
 * Time Complexity: O(n * target) where n <= 200 and target <= 10000.
 * Space Complexity: O(target) auxiliary space.
 */
public class p416 {

    // Method to check if array can be partitioned into two subsets of equal sum
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        int totalSum = 0;
        int maxNum = 0;
        for (int num : nums) {
            totalSum += num;
            maxNum = Math.max(maxNum, num);
        }

        // An odd total sum cannot be partitioned into two equal integer subsets
        if (totalSum % 2 != 0) {
            return false;
        }

        int target = totalSum / 2;

        // If the largest single element exceeds target, partition is impossible
        if (maxNum > target) {
            return false;
        }

        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        for (int num : nums) {
            for (int s = target; s >= num; s--) {
                dp[s] = dp[s] || dp[s - num];
            }
            // Early exit if target sum is already formed
            if (dp[target]) {
                return true;
            }
        }

        return dp[target];
    }

    public static void main(String[] args) {
        p416 solver = new p416();

        // Test Case 1: Valid partition [1, 5, 5] and [11]
        int[] nums1 = {1, 5, 11, 5};
        System.out.println("Test Case 1 Can Partition: " + solver.canPartition(nums1)); // Expected: true

        // Test Case 2: Odd total sum / impossible partition
        int[] nums2 = {1, 2, 3, 5};
        System.out.println("Test Case 2 Can Partition: " + solver.canPartition(nums2)); // Expected: false

        // Test Case 3: Two identical elements
        int[] nums3 = {2, 2};
        System.out.println("Test Case 3 Can Partition: " + solver.canPartition(nums3)); // Expected: true

        // Test Case 4: Element larger than target sum
        int[] nums4 = {1, 2, 5};
        System.out.println("Test Case 4 Can Partition: " + solver.canPartition(nums4)); // Expected: false
    }
}
