// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 287: Find the Duplicate Number
 * Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
 * There is only one repeated number in nums, return this repeated number.
 * You must solve the problem without modifying the array nums and using only constant extra space.
 *
 * Floyd's Tortoise and Hare (Cycle Detection) Approach:
 * - Since values are in range [1, n], we can treat the array as a linked list where
 *   index i points to node nums[i].
 * - Because values are >= 1, index 0 is outside any cycle and acts as the list head.
 * - By the Pigeonhole Principle, at least two indices point to the same value, creating a cycle.
 *   The entrance to this cycle corresponds exactly to the duplicate number.
 * - Phase 1:
 *   Advance slow by 1 step (slow = nums[slow]) and fast by 2 steps (fast = nums[nums[fast]])
 *   until they intersect inside the cycle.
 * - Phase 2:
 *   Reset slow to nums[0] (start of list) and advance both slow and fast by 1 step at a time.
 *   The node where they meet is the cycle entrance (the duplicate number).
 *
 * Time Complexity: O(n) linear time
 * Space Complexity: O(1) constant extra space without modifying the input array
 */
public class p287 {

    // Method to find the duplicate number
    public int findDuplicate(int[] nums) {

        // Phase 1: Detect cycle intersection using fast and slow pointers
        int slow = nums[0];
        int fast = nums[0];

        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        // Phase 2: Find entrance to cycle (duplicate number)
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p287 obj = new p287();

        // Test case 1: [1, 3, 4, 2, 2] -> 2
        int[] nums1 = { 1, 3, 4, 2, 2 };
        System.out.println("Duplicate in [1, 3, 4, 2, 2]: " + obj.findDuplicate(nums1));

        // Test case 2: [3, 1, 3, 4, 2] -> 3
        int[] nums2 = { 3, 1, 3, 4, 2 };
        System.out.println("Duplicate in [3, 1, 3, 4, 2]: " + obj.findDuplicate(nums2));

        // Test case 3: [3, 3, 3, 3, 3] -> 3
        int[] nums3 = { 3, 3, 3, 3, 3 };
        System.out.println("Duplicate in [3, 3, 3, 3, 3]: " + obj.findDuplicate(nums3));
    }
}
