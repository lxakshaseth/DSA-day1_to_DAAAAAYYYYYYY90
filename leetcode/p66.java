// Defines that this class belongs to the leetcode package
package leetcode;

// Import utility classes
import java.util.Arrays;

/*
 * LeetCode 66: Plus One
 * You are given a large integer represented as an integer array digits, where each digits[i]
 * is the ith digit of the integer. Digits are ordered from most significant to least significant.
 * Increment the large integer by one and return the resulting array of digits.
 *
 * Right-to-Left Carry Propagation Approach:
 * - Traverse digits backwards from least significant to most significant:
 *   1. If current digit is less than 9, increment by 1 and return immediately.
 *   2. If current digit is 9, it becomes 0 (carry of 1 propagates left).
 * - If all digits were 9 (e.g., 999 -> 1000), allocate a new array of size (n + 1)
 *   with leading digit set to 1.
 *
 * Time Complexity: O(n) where n is number of digits
 * Space Complexity: O(1) in-place unless all digits are 9 (O(n) for new array)
 */
public class p66 {

    // Method to add one to the number represented by digits array
    public int[] plusOne(int[] digits) {

        int n = digits.length;

        // Traverse backwards
        for (int i = n - 1; i >= 0; i--) {

            if (digits[i] < 9) {
                digits[i]++;
                return digits; // No further carry needed
            }

            // Digit was 9, rollover to 0
            digits[i] = 0;
        }

        // If loop completes, all digits were 9 (e.g. 99 -> 100)
        int[] result = new int[n + 1];
        result[0] = 1; // Default values for remaining elements are already 0

        return result;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p66 obj = new p66();

        // Test case 1: [1, 2, 3] -> [1, 2, 4]
        int[] digits1 = { 1, 2, 3 };
        System.out.println("Plus one for [1, 2, 3]: " + Arrays.toString(obj.plusOne(digits1)));

        // Test case 2: [4, 3, 2, 1] -> [4, 3, 2, 2]
        int[] digits2 = { 4, 3, 2, 1 };
        System.out.println("Plus one for [4, 3, 2, 1]: " + Arrays.toString(obj.plusOne(digits2)));

        // Test case 3: [9, 9] -> [1, 0, 0]
        int[] digits3 = { 9, 9 };
        System.out.println("Plus one for [9, 9]: " + Arrays.toString(obj.plusOne(digits3)));
    }
}
