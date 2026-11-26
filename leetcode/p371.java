package leetcode;

/**
 * LeetCode 371: Sum of Two Integers
 * https://leetcode.com/problems/sum-of-two-integers/
 *
 * Given two integers a and b, return the sum of the two integers
 * without using the operators + and -.
 */
public class p371 {

    /**
     * Bitwise Addition using XOR (half adder sum) and AND + Left Shift (carry).
     *
     * In two's complement 32-bit arithmetic:
     * - a ^ b computes bitwise addition without carry.
     * - (a & b) << 1 computes the carry bits shifted to the next significant position.
     *
     * Time Complexity: O(1) - Loop executes at most 32 times (word size).
     * Space Complexity: O(1) - In-place register variables.
     */
    public int getSum(int a, int b) {
        while (b != 0) {
            int carry = (a & b) << 1;
            a = a ^ b;
            b = carry;
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println("Testing LeetCode 371: Sum of Two Integers...");
        p371 solver = new p371();

        // Test 1: Positive numbers
        assert solver.getSum(1, 2) == 3 : "Expected 3";
        assert solver.getSum(2, 3) == 5 : "Expected 5";
        assert solver.getSum(20, 30) == 50 : "Expected 50";
        System.out.println("Test 1: Positive additions passed!");

        // Test 2: Zero operand
        assert solver.getSum(0, 5) == 5 : "Expected 5";
        assert solver.getSum(5, 0) == 5 : "Expected 5";
        assert solver.getSum(0, 0) == 0 : "Expected 0";
        System.out.println("Test 2: Zero cases passed!");

        // Test 3: Positive and Negative numbers (subtraction via two's complement)
        assert solver.getSum(-2, 3) == 1 : "Expected 1";
        assert solver.getSum(2, -3) == -1 : "Expected -1";
        assert solver.getSum(-5, 5) == 0 : "Expected 0";
        System.out.println("Test 3: Mixed sign additions passed!");

        // Test 4: Both negative numbers
        assert solver.getSum(-4, -6) == -10 : "Expected -10";
        assert solver.getSum(-1, -1) == -2 : "Expected -2";
        System.out.println("Test 4: Negative additions passed!");

        // Test 5: Large numbers and boundary checks
        assert solver.getSum(1000, -1000) == 0 : "Expected 0";
        assert solver.getSum(1073741823, 1) == 1073741824 : "Expected 1073741824";
        System.out.println("Test 5: Large values passed!");

        System.out.println("All test cases passed successfully!");
    }
}
