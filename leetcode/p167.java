// Defines that this class belongs to the leetcode package
package leetcode;

// Import utility classes
import java.util.Arrays;

/*
 * LeetCode 167: Two Sum II - Input Array Is Sorted
 * Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order,
 * find two numbers such that they add up to a specific target number.
 * Must use O(1) additional space.
 *
 * Two-Pointer Approach:
 * - Start left at beginning (0) and right at end (length - 1).
 * - If sum == target, return {left + 1, right + 1}.
 * - If sum < target, increment left pointer.
 * - If sum > target, decrement right pointer.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class p167 {

    // Method to find 1-indexed indices that add up to target
    public int[] twoSum(int[] numbers, int target) {

        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {

            int currentSum = numbers[left] + numbers[right];

            if (currentSum == target) {
                // Return 1-based indices
                return new int[] { left + 1, right + 1 };
            } else if (currentSum < target) {
                left++;
            } else {
                right--;
            }
        }

        // Return empty array if no pair found
        return new int[] {};
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p167 obj = new p167();

        // Sample sorted array and target
        int[] numbers = { 2, 7, 11, 15 };
        int target = 9;

        // Output: [1, 2]
        int[] result = obj.twoSum(numbers, target);
        System.out.println("1-based indices: " + Arrays.toString(result));
    }
}
