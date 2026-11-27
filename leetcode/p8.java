package leetcode;

/**
 * LeetCode 8: String to Integer (atoi)
 * https://leetcode.com/problems/string-to-integer-atoi/
 *
 * Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer.
 * Handles leading whitespace, optional +/- sign, digit parsing, and 32-bit clamping.
 */
public class p8 {

    /**
     * Deterministic Sequential Parsing with Overflow Clamping.
     *
     * Time Complexity: O(n) where n = s.length()
     * Space Complexity: O(1) auxiliary space
     */
    public int myAtoi(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int n = s.length();
        int idx = 0;

        // 1. Skip leading whitespaces
        while (idx < n && s.charAt(idx) == ' ') {
            idx++;
        }

        if (idx == n) {
            return 0;
        }

        // 2. Check for optional sign
        int sign = 1;
        char firstChar = s.charAt(idx);
        if (firstChar == '+') {
            idx++;
        } else if (firstChar == '-') {
            sign = -1;
            idx++;
        }

        // 3. Read subsequent digits and clamp on 32-bit overflow
        int result = 0;
        int threshold = Integer.MAX_VALUE / 10; // 214748364

        while (idx < n) {
            char c = s.charAt(idx);
            if (c < '0' || c > '9') {
                break;
            }

            int digit = c - '0';

            // Check overflow before updating result
            // Integer.MAX_VALUE = 2147483647 (ends in 7)
            // Integer.MIN_VALUE = -2147483648 (ends in 8)
            if (result > threshold || (result == threshold && digit > 7)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            result = result * 10 + digit;
            idx++;
        }

        return sign * result;
    }

    public static void main(String[] args) {
        System.out.println("Testing LeetCode 8: String to Integer (atoi)...");
        p8 solver = new p8();

        // Test 1: Standard positive
        assert solver.myAtoi("42") == 42 : "Expected 42";
        System.out.println("Test 1: '42' -> 42 (Passed)");

        // Test 2: Leading whitespace and negative
        assert solver.myAtoi("   -042") == -42 : "Expected -42";
        System.out.println("Test 2: '   -042' -> -42 (Passed)");

        // Test 3: Trailing words
        assert solver.myAtoi("1337c0d3") == 1337 : "Expected 1337";
        System.out.println("Test 3: '1337c0d3' -> 1337 (Passed)");

        // Test 4: Leading invalid characters
        assert solver.myAtoi("0-1") == 0 : "Expected 0";
        assert solver.myAtoi("words and 987") == 0 : "Expected 0";
        System.out.println("Test 4: Invalid starting characters -> 0 (Passed)");

        // Test 5: Positive overflow
        assert solver.myAtoi("91283472332") == Integer.MAX_VALUE : "Expected MAX_VALUE";
        System.out.println("Test 5: Positive overflow clamped to Integer.MAX_VALUE (Passed)");

        // Test 6: Negative underflow
        assert solver.myAtoi("-91283472332") == Integer.MIN_VALUE : "Expected MIN_VALUE";
        System.out.println("Test 6: Negative underflow clamped to Integer.MIN_VALUE (Passed)");

        // Test 7: Exact boundary tests
        assert solver.myAtoi("2147483647") == Integer.MAX_VALUE : "Expected exact MAX_VALUE";
        assert solver.myAtoi("-2147483648") == Integer.MIN_VALUE : "Expected exact MIN_VALUE";
        assert solver.myAtoi("2147483648") == Integer.MAX_VALUE : "Expected clamped MAX_VALUE";
        assert solver.myAtoi("-2147483649") == Integer.MIN_VALUE : "Expected clamped MIN_VALUE";
        System.out.println("Test 7: Exact 32-bit boundary tests (Passed)");

        // Test 8: Empty and whitespace strings
        assert solver.myAtoi("") == 0 : "Expected 0";
        assert solver.myAtoi("   ") == 0 : "Expected 0";
        System.out.println("Test 8: Empty / whitespace inputs (Passed)");

        System.out.println("All test cases passed successfully!");
    }
}
