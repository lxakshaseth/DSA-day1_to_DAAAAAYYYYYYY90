// Defines that this class belongs to the leetcode package
package leetcode;

// Imports HashMap and Map from Java's utility package
import java.util.HashMap;
import java.util.Map;

/*
 * LeetCode 1: Two Sum
 * Given an array of integers nums and an integer target, return indices of the two numbers
 * such that they add up to target.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class p1 {

    // Method to find two indices that add up to target
    public int[] twoSum(int[] nums, int target) {

        // Create a HashMap to store (number -> index)
        Map<Integer, Integer> map = new HashMap<>();

        // Traverse through the array
        for (int i = 0; i < nums.length; i++) {

            // Calculate the complement needed to reach target
            int complement = target - nums[i];

            // Check if complement already exists in map
            if (map.containsKey(complement)) {

                // Return indices of complement and current element
                return new int[] { map.get(complement), i };
            }

            // Put current element and its index into map
            map.put(nums[i], i);
        }

        // Return empty array if no pair found
        return new int[] {};
    }

    // Main method to test the solution
    public static void main(String[] args) {

        // Create object of p1
        p1 obj = new p1();

        // Sample input array and target
        int[] nums = { 2, 7, 11, 15 };
        int target = 9;

        // Call twoSum method
        int[] result = obj.twoSum(nums, target);

        // Print resulting indices
        System.out.println("[" + result[0] + ", " + result[1] + "]");
    }
}
