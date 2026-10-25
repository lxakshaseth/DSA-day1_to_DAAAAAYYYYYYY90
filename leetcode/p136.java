// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 136: Single Number
 * Given a non-empty array of integers nums, every element appears twice except for one.
 * Find that single one.
 * Must run in linear time complexity O(n) and use constant extra space O(1).
 *
 * Bit Manipulation (XOR) Approach:
 * - Fundamental XOR properties:
 *   1. x ^ 0 = x (identity)
 *   2. x ^ x = 0 (self-inverse)
 *   3. XOR is commutative and associative: a ^ b ^ a = (a ^ a) ^ b = 0 ^ b = b.
 * - XORing all numbers in the array cancels out all duplicates appearing twice,
 *   leaving strictly the unique number.
 *
 * Time Complexity: O(n) single linear pass
 * Space Complexity: O(1) constant auxiliary space
 */
public class p136 {

    // Method to find the unique single number using XOR
    public int singleNumber(int[] nums) {

        int result = 0;

        // XOR all numbers together
        for (int num : nums) {
            result ^= num;
        }

        return result;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p136 obj = new p136();

        // Test case 1: [2, 2, 1] -> 1
        int[] nums1 = { 2, 2, 1 };
        System.out.println("Single number 1: " + obj.singleNumber(nums1)); // 1

        // Test case 2: [4, 1, 2, 1, 2] -> 4
        int[] nums2 = { 4, 1, 2, 1, 2 };
        System.out.println("Single number 2: " + obj.singleNumber(nums2)); // 4

        // Test case 3: [1] -> 1
        int[] nums3 = { 1 };
        System.out.println("Single number 3: " + obj.singleNumber(nums3)); // 1
    }
}
