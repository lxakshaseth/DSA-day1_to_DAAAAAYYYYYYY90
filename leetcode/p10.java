// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 10: Regular Expression Matching
 *
 * Given an input string s and a pattern p, implement regular expression matching
 * with support for '.' and '*' where:
 * - '.' Matches any single character.
 * - '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string.
 *
 * Optimal Approach: 2-D Dynamic Programming
 * - Let dp[i][j] represent whether prefix s[0..i-1] matches pattern prefix p[0..j-1].
 * - Base Cases:
 *   - dp[0][0] = true (empty string matches empty pattern).
 *   - dp[i][0] = false for i > 0 (non-empty string cannot match empty pattern).
 *   - dp[0][j] = dp[0][j - 2] if p[j - 1] == '*' (e.g. "a*", "a*b*" can match empty string).
 * - Transitions:
 *   - When p[j - 1] != '*':
 *       dp[i][j] = dp[i - 1][j - 1] if p[j - 1] == s[i - 1] || p[j - 1] == '.'
 *   - When p[j - 1] == '*':
 *       Let prevChar = p[j - 2].
 *       1. Zero occurrences: dp[i][j] = dp[i][j - 2]
 *       2. One or more occurrences: If prevChar == s[i - 1] || prevChar == '.',
 *          dp[i][j] = dp[i][j] || dp[i - 1][j]
 *
 * Time Complexity: O(m * n) where m = s.length(), n = p.length().
 * Space Complexity: O(m * n) auxiliary DP table space.
 */
public class p10 {

    // Method to evaluate regular expression matching
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }

        int m = s.length();
        int n = p.length();

        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        // Initialize patterns matching the empty string s
        for (int j = 2; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        for (int i = 1; i <= m; i++) {
            char sc = s.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char pc = p.charAt(j - 1);

                if (pc != '*') {
                    if (pc == sc || pc == '.') {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                } else {
                    // Match zero occurrences of the preceding element
                    dp[i][j] = dp[i][j - 2];

                    // Match one or more occurrences of the preceding element
                    char prevChar = p.charAt(j - 2);
                    if (prevChar == sc || prevChar == '.') {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        p10 solver = new p10();

        // Test Case 1: Partial match failure
        System.out.println("Test Case 1 (\"aa\", \"a\"): " +
                solver.isMatch("aa", "a")); // Expected: false

        // Test Case 2: Star matching multiple characters
        System.out.println("Test Case 2 (\"aa\", \"a*\"): " +
                solver.isMatch("aa", "a*")); // Expected: true

        // Test Case 3: Dot and star combined
        System.out.println("Test Case 3 (\"ab\", \".*\"): " +
                solver.isMatch("ab", ".*")); // Expected: true

        // Test Case 4: Zero occurrences of preceding elements
        System.out.println("Test Case 4 (\"aab\", \"c*a*b\"): " +
                solver.isMatch("aab", "c*a*b")); // Expected: true

        // Test Case 5: Complex non-matching
        System.out.println("Test Case 5 (\"mississippi\", \"mis*is*p*.\"): " +
                solver.isMatch("mississippi", "mis*is*p*.")); // Expected: false
    }
}
