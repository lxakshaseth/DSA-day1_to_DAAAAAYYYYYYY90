// Defines that this class belongs to the leetcode package
package leetcode;

// Import utility classes
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * LeetCode 15: 3Sum
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
 * such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 * Notice that the solution set must not contain duplicate triplets.
 *
 * Sorting + Two-Pointer Approach:
 * - Sort the array in ascending order.
 * - Fix the first element nums[i] and use two pointers (left and right) for the rest.
 * - Skip duplicate elements to ensure unique triplets.
 *
 * Time Complexity: O(n^2)
 * Space Complexity: O(1) auxiliary (excluding output list)
 */
public class p15 {

    // Method to find all unique triplets that sum up to 0
    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        // Sort the array
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {

            // Skip duplicate values for the first element
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // Two pointers for remaining part of the array
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {

                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    // Valid triplet found
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // Skip duplicates for second element
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }

                    // Skip duplicates for third element
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return result;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p15 obj = new p15();

        // Sample input array
        int[] nums = { -1, 0, 1, 2, -1, -4 };

        // Output: [[-1, -1, 2], [-1, 0, 1]]
        List<List<Integer>> result = obj.threeSum(nums);
        System.out.println("Unique triplets summing to 0: " + result);
    }
}
