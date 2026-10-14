// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 33: Search in Rotated Sorted Array
 * Given the array nums after a possible rotation at an unknown pivot index and an integer target,
 * return the index of target if it is in nums, or -1 if it is not in nums.
 * Must achieve O(log n) runtime complexity.
 *
 * Modified Binary Search Approach:
 * - At every step, at least one half of the array (left or right) is guaranteed to be sorted.
 * - Determine which half is sorted and check if target falls within that sorted range.
 * - Narrow the search space accordingly.
 *
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 */
public class p33 {

    // Method to search target in rotated sorted array
    public int search(int[] nums, int target) {

        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                return mid; // Target found
            }

            // Check if left half is sorted
            if (nums[low] <= nums[mid]) {

                // Check if target lies within the sorted left half
                if (nums[low] <= target && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }

            } else {
                // Right half must be sorted
                // Check if target lies within the sorted right half
                if (nums[mid] < target && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }

        return -1; // Target not found
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p33 obj = new p33();

        // Sample rotated sorted array
        int[] nums = { 4, 5, 6, 7, 0, 1, 2 };

        // Test case 1: target present
        int target1 = 0;
        System.out.println("Index of " + target1 + ": " + obj.search(nums, target1));

        // Test case 2: target not present
        int target2 = 3;
        System.out.println("Index of " + target2 + ": " + obj.search(nums, target2));
    }
}
