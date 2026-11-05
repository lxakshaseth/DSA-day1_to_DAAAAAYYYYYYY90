// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 152: Maximum Product Subarray
 * Given an integer array nums, find a contiguous non-empty subarray within the array
 * that has the largest product, and return the product.
 *
 * Modified Kadane's Algorithm / 1-D Dynamic Programming:
 * - Unlike maximum subarray sum, multiplying by a negative number can turn a very small
 *   negative product into a very large positive product.
 * - Therefore, we maintain two rolling values at each index:
 *   1. maxProd: maximum product of a subarray ending at the current index.
 *   2. minProd: minimum product of a subarray ending at the current index.
 * - When nums[i] is negative, multiplying by it swaps the maximum and minimum;
 *   hence, we swap maxProd and minProd before evaluating.
 * - At each index:
 *   maxProd = max(nums[i], maxProd * nums[i])
 *   minProd = min(nums[i], minProd * nums[i])
 * - Update the global maximum product with maxProd.
 *
 * Time Complexity: O(n) single pass over array
 * Space Complexity: O(1) constant extra space
 */
public class p152 {

    // Method to find maximum product of a contiguous subarray
    public int maxProduct(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        int maxProd = nums[0];
        int minProd = nums[0];
        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {

            // When current number is negative, min and max products swap
            if (nums[i] < 0) {
                int temp = maxProd;
                maxProd = minProd;
                minProd = temp;
            }

            // Either start a new subarray at nums[i] or multiply with previous
            maxProd = Math.max(nums[i], maxProd * nums[i]);
            minProd = Math.min(nums[i], minProd * nums[i]);

            // Update global maximum product
            result = Math.max(result, maxProd);
        }

        return result;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p152 obj = new p152();

        // Test case 1: [2, 3, -2, 4] -> 6 ([2, 3])
        int[] nums1 = { 2, 3, -2, 4 };
        System.out.println("Max product for [2, 3, -2, 4]: " + obj.maxProduct(nums1));

        // Test case 2: [-2, 0, -1] -> 0
        int[] nums2 = { -2, 0, -1 };
        System.out.println("Max product for [-2, 0, -1]: " + obj.maxProduct(nums2));

        // Test case 3: [-2, 3, -4] -> 24 ([-2, 3, -4])
        int[] nums3 = { -2, 3, -4 };
        System.out.println("Max product for [-2, 3, -4]: " + obj.maxProduct(nums3));

        // Test case 4: [-2] -> -2
        int[] nums4 = { -2 };
        System.out.println("Max product for [-2]: " + obj.maxProduct(nums4));

        // Test case 5: [0, 2] -> 2
        int[] nums5 = { 0, 2 };
        System.out.println("Max product for [0, 2]: " + obj.maxProduct(nums5));
    }
}
