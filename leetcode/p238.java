// Defines that this class belongs to the leetcode package
package leetcode;

// Import utility classes
import java.util.Arrays;

/*
 * LeetCode 238: Product of Array Except Self
 * Given an integer array nums, return an array answer such that answer[i]
 * is equal to the product of all the elements of nums except nums[i].
 * Must run in O(n) without using the division operation.
 *
 * Prefix and Suffix Product Approach:
 * - Pass 1: compute left prefix products directly in output array.
 * - Pass 2: maintain running suffix product and multiply backwards.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1) extra space (output array does not count as extra)
 */
public class p238 {

    // Method to calculate product of array except self
    public int[] productExceptSelf(int[] nums) {

        int n = nums.length;
        int[] result = new int[n];

        // First pass: Calculate prefix products
        // result[i] will contain product of all elements to the left of i
        result[0] = 1;
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }

        // Second pass: Multiply with suffix products
        // Maintain running product of all elements to the right of i
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] = result[i] * right;
            right *= nums[i];
        }

        return result;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p238 obj = new p238();

        // Sample input array
        int[] nums = { 1, 2, 3, 4 };

        // Output: [24, 12, 8, 6]
        int[] result = obj.productExceptSelf(nums);
        System.out.println("Product except self: " + Arrays.toString(result));
    }
}
