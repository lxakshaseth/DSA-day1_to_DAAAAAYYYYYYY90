// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 190: Reverse Bits
 * Reverse bits of a given 32 bits unsigned integer.
 *
 * Bit Manipulation Approach:
 * - Maintain a 32-bit result variable initialized to 0.
 * - In each of the 32 iterations:
 *   1. Shift result to the left by 1 (result << 1) to make room for the next bit.
 *   2. Extract the least significant bit of n using (n & 1).
 *   3. Append this bit to result using bitwise OR (result | (n & 1)).
 *   4. Unsigned right shift n by 1 (n >>> 1).
 *
 * Time Complexity: O(1) - fixed 32 iterations
 * Space Complexity: O(1) - constant auxiliary space
 */
public class p190 {

    // Method to reverse bits of a 32-bit integer
    public int reverseBits(int n) {

        int result = 0;

        for (int i = 0; i < 32; i++) {
            // Shift result left and add lowest bit of n
            result = (result << 1) | (n & 1);
            // Logical unsigned shift of n to the right
            n >>>= 1;
        }

        return result;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p190 obj = new p190();

        // Test case 1: 43261596 (00000010100101000001111010011100) -> 964176192
        int n1 = 43261596;
        int rev1 = obj.reverseBits(n1);
        System.out.println("Reversed " + n1 + ": " + rev1);

        // Test case 2: 1 -> binary reversed becomes 2147483648 (or -2147483648 as signed 32-bit int)
        int n2 = 1;
        int rev2 = obj.reverseBits(n2);
        System.out.println("Reversed " + n2 + ": " + rev2);
    }
}
