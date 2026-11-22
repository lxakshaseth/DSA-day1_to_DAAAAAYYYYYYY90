// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 678: Valid Parenthesis String
 *
 * Given a string s containing only '(', ')' and '*', return true if s is valid.
 * '*' can be treated as '(', ')', or an empty string "".
 *
 * Optimal Approach: Greedy Range of Open Parentheses Count (O(1) Space)
 * - Track the range [cmin, cmax] of possible unmatched open parentheses:
 *   - cmin: The minimum possible number of open '(' (assuming '*' acts as ')' or "").
 *   - cmax: The maximum possible number of open '(' (assuming '*' acts as '(').
 * - For each character:
 *   - If '(': Both cmin and cmax increment by 1.
 *   - If ')': Both cmin and cmax decrement by 1.
 *   - If '*': Can be ')' (cmin decrements), '(' (cmax increments), or "" (no change).
 *             Thus: cmin--, cmax++.
 * - Validations per step:
 *   - If cmax < 0: Even with every '*' treated as '(', there are too many ')' -> return false.
 *   - cmin = max(cmin, 0): cmin cannot drop below 0 because '*' can simply act as ""
 *     rather than forcing an excess closing parenthesis.
 * - Final Condition:
 *   - Returns true if and only if cmin == 0 at the end of the string.
 *
 * Time Complexity: O(n) - Single pass through the string.
 * Space Complexity: O(1) - Constant variables without array or stack allocation.
 */
public class p678 {

    // Method to check if the parenthesis string is valid
    public boolean checkValidString(String s) {
        if (s == null) {
            return false;
        }

        int cmin = 0; // Minimum possible open parentheses
        int cmax = 0; // Maximum possible open parentheses

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                cmin++;
                cmax++;
            } else if (ch == ')') {
                cmin--;
                cmax--;
            } else if (ch == '*') {
                cmin--; // treated as ')'
                cmax++; // treated as '('
            }

            // More ')' than '(' possible even in the best case
            if (cmax < 0) {
                return false;
            }

            // cmin cannot be negative as '*' can be empty string ""
            cmin = Math.max(cmin, 0);
        }

        return cmin == 0;
    }

    public static void main(String[] args) {
        p678 solver = new p678();

        // Test Case 1: Simple pair
        System.out.println("Test Case 1 (\"()\"): " +
                solver.checkValidString("()")); // Expected: true

        // Test Case 2: Star as empty string or parenthesis
        System.out.println("Test Case 2 (\"(*)\"): " +
                solver.checkValidString("(*)")); // Expected: true

        // Test Case 3: Star as open parenthesis
        System.out.println("Test Case 3 (\"(*))\"): " +
                solver.checkValidString("(*))")); // Expected: true

        // Test Case 4: Invalid starting with closing parenthesis
        System.out.println("Test Case 4 (\")(\"): " +
                solver.checkValidString(")(")); // Expected: false

        // Test Case 5: Excess open parentheses
        System.out.println("Test Case 5 (\"(()\"): " +
                solver.checkValidString("(()")); // Expected: false

        // Test Case 6: Star balancing excess opens
        System.out.println("Test Case 6 (\"((**\"): " +
                solver.checkValidString("((**")); // Expected: true
    }
}
