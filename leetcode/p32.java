// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 32: Longest Valid Parentheses
 *
 * Given a string containing just the characters '(' and ')', return the length
 * of the longest valid (well-formed) parentheses substring.
 *
 * Optimal Approach: Two-Pass Counter (O(1) Auxiliary Space)
 * - An invalid sequence occurs either when closing parentheses exceed opening parentheses
 *   (excess ')') or opening parentheses exceed closing ones (excess '(').
 * - Pass 1 (Left to Right):
 *   - Maintain counters left and right for '(' and ')' respectively.
 *   - When left == right, a valid balanced substring is formed; update maxLen = max(maxLen, 2 * right).
 *   - If right > left, the sequence is broken by an unmatched ')'; reset left = right = 0.
 * - Pass 2 (Right to Left):
 *   - Symmetrically scan backwards to catch valid sequences with excess '(' (like "(()").
 *   - When left == right, update maxLen = max(maxLen, 2 * left).
 *   - If left > right, reset left = right = 0.
 *
 * Time Complexity: O(n) - Two linear scans over the input string.
 * Space Complexity: O(1) - Constant variables without heap or stack allocations.
 */
public class p32 {

    // Method to calculate the length of the longest valid parentheses substring
    public int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }

        int maxLen = 0;
        int left = 0;
        int right = 0;

        // Left to right scan
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                maxLen = Math.max(maxLen, 2 * right);
            } else if (right > left) {
                left = 0;
                right = 0;
            }
        }

        // Right to left scan
        left = 0;
        right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                maxLen = Math.max(maxLen, 2 * left);
            } else if (left > right) {
                left = 0;
                right = 0;
            }
        }

        return maxLen;
    }

    public static void main(String[] args) {
        p32 solver = new p32();

        // Test Case 1: Standard prefix invalid "(()"
        System.out.println("Test Case 1 (\"(()\"): " +
                solver.longestValidParentheses("(()")); // Expected: 2

        // Test Case 2: Alternating pairs ")()())"
        System.out.println("Test Case 2 (\")()())\"): " +
                solver.longestValidParentheses(")()())")); // Expected: 4

        // Test Case 3: Empty string
        System.out.println("Test Case 3 (\"\"): " +
                solver.longestValidParentheses("")); // Expected: 0

        // Test Case 4: Complete valid nested string "(())"
        System.out.println("Test Case 4 (\"(())\"): " +
                solver.longestValidParentheses("(())")); // Expected: 4

        // Test Case 5: Independent valid blocks separated by invalid boundary "()(()"
        System.out.println("Test Case 5 (\"()(()\"): " +
                solver.longestValidParentheses("()(()")); // Expected: 2
    }
}
