// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 4: Median of Two Sorted Arrays
 *
 * Given two sorted arrays nums1 and nums2 of size m and n respectively,
 * return the median of the two sorted arrays.
 * The overall run time complexity should be O(log(min(m, n))).
 *
 * Optimal Approach: Binary Search Partition on the Smaller Array
 * - Ensure nums1 is the shorter array (m <= n) by swapping if necessary.
 * - Binary search for partition1 in [0, m], and determine partition2 such that
 *   the combined left partition contains exactly (m + n + 1) / 2 elements:
 *     partition2 = (m + n + 1) / 2 - partition1
 * - Boundary elements around the cut:
 *     maxLeft1  = partition1 == 0 ? Integer.MIN_VALUE : nums1[partition1 - 1]
 *     minRight1 = partition1 == m ? Integer.MAX_VALUE : nums1[partition1]
 *     maxLeft2  = partition2 == 0 ? Integer.MIN_VALUE : nums2[partition2 - 1]
 *     minRight2 = partition2 == n ? Integer.MAX_VALUE : nums2[partition2]
 * - A partition is correct when:
 *     maxLeft1 <= minRight2 && maxLeft2 <= minRight1
 * - When valid:
 *     - If (m + n) is odd:  median = max(maxLeft1, maxLeft2)
 *     - If (m + n) is even: median = (max(maxLeft1, maxLeft2) + min(minRight1, minRight2)) / 2.0
 * - If maxLeft1 > minRight2: shift cut left (high = partition1 - 1)
 * - If maxLeft2 > minRight1: shift cut right (low = partition1 + 1)
 *
 * Time Complexity: O(log(min(m, n)))
 * Space Complexity: O(1) constant extra space
 */
public class p4 {

    // Method to find median of two sorted arrays
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Guarantee nums1 is the smaller array to minimize binary search range
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int low = 0;
        int high = m;

        while (low <= high) {
            int partition1 = low + (high - low) / 2;
            int partition2 = (m + n + 1) / 2 - partition1;

            int maxLeft1 = (partition1 == 0) ? Integer.MIN_VALUE : nums1[partition1 - 1];
            int minRight1 = (partition1 == m) ? Integer.MAX_VALUE : nums1[partition1];

            int maxLeft2 = (partition2 == 0) ? Integer.MIN_VALUE : nums2[partition2 - 1];
            int minRight2 = (partition2 == n) ? Integer.MAX_VALUE : nums2[partition2];

            if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
                // Correct partition found
                if ((m + n) % 2 == 1) {
                    return Math.max(maxLeft1, maxLeft2);
                } else {
                    return ((double) Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2)) / 2.0;
                }
            } else if (maxLeft1 > minRight2) {
                // Too far to the right in nums1; move left
                high = partition1 - 1;
            } else {
                // Too far to the left in nums1; move right
                low = partition1 + 1;
            }
        }

        throw new IllegalArgumentException("Input arrays are not sorted or invalid.");
    }

    public static void main(String[] args) {
        p4 solver = new p4();

        // Test Case 1: Odd total length
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        System.out.println("Test Case 1 Median: " + solver.findMedianSortedArrays(nums1, nums2)); // Expected: 2.0

        // Test Case 2: Even total length
        int[] nums3 = {1, 2};
        int[] nums4 = {3, 4};
        System.out.println("Test Case 2 Median: " + solver.findMedianSortedArrays(nums3, nums4)); // Expected: 2.5

        // Test Case 3: One empty array
        int[] nums5 = {};
        int[] nums6 = {1};
        System.out.println("Test Case 3 Median: " + solver.findMedianSortedArrays(nums5, nums6)); // Expected: 1.0

        // Test Case 4: Negative numbers and disjoint ranges
        int[] nums7 = {-5, -3};
        int[] nums8 = {-2, 0, 2};
        System.out.println("Test Case 4 Median: " + solver.findMedianSortedArrays(nums7, nums8)); // Expected: -2.0
    }
}
