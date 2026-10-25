// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 268: Missing Number
 * Given an array nums containing n distinct numbers in the range [0, n],
 * return the only number in the range that is missing from the array.
 * Must run in linear time complexity O(n) and use constant extra space O(1).
 *
 * Bit Manipulation (XOR) Approach:
 * - A complete set from 0 to n has (n + 1) numbers, while nums contains n numbers.
 * - Due to XOR self-inverse property (x ^ x = 0 and x ^ 0 = x),
 *   XORing all indices [0, n] along with all numbers in nums will cancel out all
 *   numbers that are present, leaving strictly the missing number.
 * - This approach avoids arithmetic integer overflow.
 *
 * Time Complexity: O(n) single pass
 * Space Complexity: O(1) constant auxiliary space
 */
public class p268 {

    // Method to find missing number in range [0, n] using XOR
    public int missingNumber(int[] nums) {

        int n = nums.length;
        int missing = n; // Initialize with n

        // XOR each index and corresponding array value
        for (int i = 0; i < n; i++) {
            missing ^= i ^ nums[i];
        }

        return missing;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p268 obj = new p268();

        // Test case 1: [3, 0, 1] -> missing 2
        int[] nums1 = { 3, 0, 1 };
        System.out.println("Missing number 1: " + obj.missingNumber(nums1)); // 2

        // Test case 2: [0, 1] -> missing 2
        int[] nums2 = { 0, 1 };
        System.out.println("Missing number 2: " + obj.missingNumber(nums2)); // 2

        // Test case 3: [9,6,4,2,3,5,7,0,1] -> missing 8
        int[] nums3 = { 9, 6, 4, 2, 3, 5, 7, 0, 1 };
        System.out.println("Missing number 3: " + obj.missingNumber(nums3)); // 8
    }
}
