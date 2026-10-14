// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 704: Binary Search
 * Given an array of integers nums which is sorted in ascending order, and an integer target,
 * write a function to search target in nums. If target exists, then return its index.
 * Otherwise, return -1.
 *
 * Binary Search Approach:
 * - Maintain search boundaries: low and high.
 * - Calculate mid avoiding integer overflow: low + (high - low) / 2.
 * - Adjust boundaries based on comparison with target.
 *
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 */
public class p704 {

    // Method to perform binary search for target
    public int search(int[] nums, int target) {

        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {

            // Calculate middle index without risk of integer overflow
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                return mid; // Target found
            } else if (nums[mid] < target) {
                low = mid + 1; // Target lies in right half
            } else {
                high = mid - 1; // Target lies in left half
            }
        }

        return -1; // Target not found
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p704 obj = new p704();

        // Sample sorted array
        int[] nums = { -1, 0, 3, 5, 9, 12 };

        // Test case 1: target present
        int target1 = 9;
        System.out.println("Index of " + target1 + ": " + obj.search(nums, target1));

        // Test case 2: target not present
        int target2 = 2;
        System.out.println("Index of " + target2 + ": " + obj.search(nums, target2));
    }
}
