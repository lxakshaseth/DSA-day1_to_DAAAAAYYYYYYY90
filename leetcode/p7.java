package leetcode;

/**
 * LeetCode 7: Reverse Integer
 * https://leetcode.com/problems/reverse-integer/
 *
 * Given a signed 32-bit integer x, return x with its digits reversed.
 * If reversing x causes the value to go outside [-2^31, 2^31 - 1], return 0.
 * Operates strictly within 32-bit integer space without 64-bit casting.
 */
public class p7 {

    /**
     * Overflow-Safe Modular Reversal.
     *
     * Time Complexity: O(log10 |x|) <= 10 iterations -> O(1)
     * Space Complexity: O(1) auxiliary space
     */
    public int reverse(int x) {
        int rev = 0;
        int maxThreshold = Integer.MAX_VALUE / 10; // 214748364
        int minThreshold = Integer.MIN_VALUE / 10; // -214748364

        while (x != 0) {
            int pop = x % 10;
            x /= 10;

            // Check overflow for positive limit (2147483647)
            if (rev > maxThreshold || (rev == maxThreshold && pop > 7)) {
                return 0;
            }

            // Check underflow for negative limit (-2147483648)
            if (rev < minThreshold || (rev == minThreshold && pop < -8)) {
                return 0;
            }

            rev = rev * 10 + pop;
        }

        return rev;
    }

    public static void main(String[] args) {
        System.out.println("Testing LeetCode 7: Reverse Integer...");
        p7 solver = new p7();

        // Test 1: Standard positive
        assert solver.reverse(123) == 321 : "Expected 321";
        System.out.println("Test 1: 123 -> 321 (Passed)");

        // Test 2: Negative number
        assert solver.reverse(-123) == -321 : "Expected -321";
        System.out.println("Test 2: -123 -> -321 (Passed)");

        // Test 3: Trailing zeros
        assert solver.reverse(120) == 21 : "Expected 21";
        System.out.println("Test 3: 120 -> 21 (Passed)");

        // Test 4: Single zero
        assert solver.reverse(0) == 0 : "Expected 0";
        System.out.println("Test 4: 0 -> 0 (Passed)");

        // Test 5: Positive overflow
        // 1534236469 reversed would be 9646324351 (> 2^31 - 1) -> 0
        assert solver.reverse(1534236469) == 0 : "Expected 0 for overflow";
        System.out.println("Test 5: Overflow -> 0 (Passed)");

        // Test 6: Negative underflow
        // -2147483648 reversed overflows -> 0
        assert solver.reverse(-2147483648) == 0 : "Expected 0 for underflow";
        System.out.println("Test 6: Underflow -> 0 (Passed)");

        System.out.println("All test cases passed successfully!");
    }
}
