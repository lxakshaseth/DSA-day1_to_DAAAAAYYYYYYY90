// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 153: Find Minimum in Rotated Sorted Array
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times.
 * Given the sorted rotated array nums of unique elements, return the minimum element of this array.
 * Must achieve O(log n) runtime complexity.
 *
 * Modified Binary Search Approach:
 * - A rotated sorted array consists of two sorted subarrays, with the minimum element at the inflection point.
 * - Compare nums[mid] with nums[right]:
 *   1. If nums[mid] > nums[right]:
 *      The inflection point (minimum) lies strictly to the right of mid, so search in [mid + 1, right].
 *      Set left = mid + 1.
 *   2. If nums[mid] <= nums[right]:
 *      The right half [mid, right] is sorted, meaning the minimum element is either nums[mid]
 *      or somewhere to its left.
 *      Set right = mid.
 * - When left == right, the search converges to the minimum element.
 *
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 */
public class p153 {

    // Method to find the minimum element in a rotated sorted array
    public int findMin(int[] nums) {

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            // If mid element is greater than the rightmost element, the minimum is in the right half
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                // Otherwise, the minimum is at mid or in the left half
                right = mid;
            }
        }

        return nums[left];
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p153 obj = new p153();

        // Test case 1: [3, 4, 5, 1, 2] -> 1
        int[] nums1 = { 3, 4, 5, 1, 2 };
        System.out.println("Minimum in [3, 4, 5, 1, 2]: " + obj.findMin(nums1)); // 1

        // Test case 2: [4, 5, 6, 7, 0, 1, 2] -> 0
        int[] nums2 = { 4, 5, 6, 7, 0, 1, 2 };
        System.out.println("Minimum in [4, 5, 6, 7, 0, 1, 2]: " + obj.findMin(nums2)); // 0

        // Test case 3: [11, 13, 15, 17] -> 11 (not rotated or rotated n times)
        int[] nums3 = { 11, 13, 15, 17 };
        System.out.println("Minimum in [11, 13, 15, 17]: " + obj.findMin(nums3)); // 11

        // Test case 4: [2, 1] -> 1
        int[] nums4 = { 2, 1 };
        System.out.println("Minimum in [2, 1]: " + obj.findMin(nums4)); // 1

        // Test case 5: [1] -> 1
        int[] nums5 = { 1 };
        System.out.println("Minimum in [1]: " + obj.findMin(nums5)); // 1
    }
}
