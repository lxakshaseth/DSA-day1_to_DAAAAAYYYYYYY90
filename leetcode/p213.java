// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 213: House Robber II
 *
 * Given an integer array nums representing the amount of money in houses arranged in a circle,
 * return the maximum money you can rob without robbing adjacent houses.
 *
 * Optimal Approach: Two-Pass Space-Optimized Linear DP (O(1) Space)
 * - Because the first house (index 0) and the last house (index n - 1) are circular neighbors,
 *   they cannot both be robbed.
 * - This decomposes into two independent linear House Robber I instances:
 *   1. Scenario 1: Consider houses from index 0 to n - 2 (omit the last house).
 *   2. Scenario 2: Consider houses from index 1 to n - 1 (omit the first house).
 * - For each linear sub-problem on range [start, end]:
 *   - Use two rolling variables rob1 and rob2:
 *       newRob = max(rob2, rob1 + nums[i])
 *       rob1 = rob2
 *       rob2 = newRob
 * - Edge Cases:
 *   - n == 1: Only 1 house exists; rob it -> return nums[0].
 * - The final answer is max(robLinear(nums, 0, n - 2), robLinear(nums, 1, n - 1)).
 *
 * Time Complexity: O(n) - Two linear passes across the array.
 * Space Complexity: O(1) - Constant variables without array allocation.
 */
public class p213 {

    // Method to calculate the maximum loot from circularly arranged houses
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        int n = nums.length;
        return Math.max(robLinear(nums, 0, n - 2), robLinear(nums, 1, n - 1));
    }

    // Helper method to solve standard linear House Robber in O(1) space
    private int robLinear(int[] nums, int start, int end) {
        int rob1 = 0; // max profit up to i - 2
        int rob2 = 0; // max profit up to i - 1

        for (int i = start; i <= end; i++) {
            int newRob = Math.max(rob2, rob1 + nums[i]);
            rob1 = rob2;
            rob2 = newRob;
        }

        return rob2;
    }

    public static void main(String[] args) {
        p213 solver = new p213();

        // Test Case 1: First and last house conflict
        int[] nums1 = {2, 3, 2};
        System.out.println("Test Case 1 Max Robbed: " +
                solver.rob(nums1)); // Expected: 3

        // Test Case 2: 4 houses
        int[] nums2 = {1, 2, 3, 1};
        System.out.println("Test Case 2 Max Robbed: " +
                solver.rob(nums2)); // Expected: 4

        // Test Case 3: 3 houses
        int[] nums3 = {1, 2, 3};
        System.out.println("Test Case 3 Max Robbed: " +
                solver.rob(nums3)); // Expected: 3

        // Test Case 4: Single house
        int[] nums4 = {5};
        System.out.println("Test Case 4 Max Robbed: " +
                solver.rob(nums4)); // Expected: 5

        // Test Case 5: Large profit in the last house
        int[] nums5 = {1, 3, 1, 3, 100};
        System.out.println("Test Case 5 Max Robbed: " +
                solver.rob(nums5)); // Expected: 103 (3 + 100)
    }
}
