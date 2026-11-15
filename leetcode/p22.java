// Defines that this class belongs to the leetcode package
package leetcode;

import java.util.ArrayList;
import java.util.List;

/*
 * LeetCode 22: Generate Parentheses
 *
 * Given n pairs of parentheses, write a function to generate all combinations
 * of well-formed parentheses.
 *
 * Optimal Approach: Backtracking with State Validation
 * - Construct combinations character-by-character while maintaining two counters:
 *   - openCount: Number of '(' added so far (bounded by n).
 *   - closeCount: Number of ')' added so far (bounded by openCount to ensure validity).
 * - Recursion choices:
 *   1. If openCount < n, append '(' and recurse with openCount + 1.
 *   2. If closeCount < openCount, append ')' and recurse with closeCount + 1.
 * - Base Case: When the current string length reaches 2 * n, a valid combination
 *   is complete; add it to the results list.
 * - Backtracking uses a single reusable StringBuilder with backtrack step (deleteCharAt).
 *
 * Time Complexity: O(4^n / sqrt(n)) bounded by the n-th Catalan number C_n * n.
 * Space Complexity: O(n) auxiliary recursion call stack depth and StringBuilder buffer.
 */
public class p22 {

    // Method to generate all well-formed parentheses combinations
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        if (n <= 0) {
            return result;
        }

        StringBuilder current = new StringBuilder();
        backtrack(n, 0, 0, current, result);
        return result;
    }

    private void backtrack(int n, int openCount, int closeCount, StringBuilder current, List<String> result) {
        // Base case: combination is complete
        if (current.length() == 2 * n) {
            result.add(current.toString());
            return;
        }

        // Choice 1: Add '(' if we have not exhausted all n open parentheses
        if (openCount < n) {
            current.append('(');
            backtrack(n, openCount + 1, closeCount, current, result);
            current.deleteCharAt(current.length() - 1); // backtrack
        }

        // Choice 2: Add ')' only if it will not exceed current open parentheses
        if (closeCount < openCount) {
            current.append(')');
            backtrack(n, openCount, closeCount + 1, current, result);
            current.deleteCharAt(current.length() - 1); // backtrack
        }
    }

    public static void main(String[] args) {
        p22 solver = new p22();

        // Test Case 1: n = 3
        System.out.println("Test Case 1 (n = 3): " + solver.generateParenthesis(3));
        // Expected: ["((()))","(()())","(())()","()(())","()()()"]

        // Test Case 2: n = 1
        System.out.println("Test Case 2 (n = 1): " + solver.generateParenthesis(1));
        // Expected: ["()"]

        // Test Case 3: n = 2
        System.out.println("Test Case 3 (n = 2): " + solver.generateParenthesis(2));
        // Expected: ["(())","()()"]

        // Test Case 4: Verify count for n = 4 (C_4 = 14)
        List<String> res4 = solver.generateParenthesis(4);
        System.out.println("Test Case 4 (n = 4 count): " + res4.size()); // Expected: 14
    }
}
