// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 55: Jump Game
 * You are given an integer array nums. You are initially positioned at the array's first index,
 * and each element in the array represents your maximum jump length at that position.
 * Return true if you can reach the last index, or false otherwise.
 *
 * Greedy Backward Pass Approach:
 * - Start with the target goal at the last index: goal = nums.length - 1.
 * - Iterate backwards from right to left:
 *   If the current index i can jump to or beyond the goal (i + nums[i] >= goal),
 *   then reaching index i is sufficient to reach the end. Hence, move the goal to i.
 * - If goal reaches 0 at the end, the last index is reachable from index 0.
 *
 * Time Complexity: O(n) single pass
 * Space Complexity: O(1) constant extra space
 */
public class p55 {

    // Method to check if last index is reachable
    public boolean canJump(int[] nums) {

        int goal = nums.length - 1;

        // Traverse backwards from second-to-last element down to index 0
        for (int i = nums.length - 2; i >= 0; i--) {

            // If current position can jump to or beyond the goal
            if (i + nums[i] >= goal) {
                goal = i; // Shift the goalpost forward to current index
            }
        }

        // True if goalpost shifted all the way back to the start index (0)
        return goal == 0;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p55 obj = new p55();

        // Test case 1: [2,3,1,1,4] -> can reach end -> true
        int[] nums1 = { 2, 3, 1, 1, 4 };
        System.out.println("Can jump 1: " + obj.canJump(nums1)); // true

        // Test case 2: [3,2,1,0,4] -> always arrives at index 3 with 0 jump -> false
        int[] nums2 = { 3, 2, 1, 0, 4 };
        System.out.println("Can jump 2: " + obj.canJump(nums2)); // false
    }
}
