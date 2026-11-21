// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 97: Interleaving String
 *
 * Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
 *
 * Optimal Approach: 2-D Dynamic Programming with 1-D Space Optimization
 * - Let m = s1.length(), n = s2.length().
 * - If m + n != s3.length(), return false immediately.
 * - Guarantee n <= m by swapping s1 and s2 if necessary to minimize 1-D array size.
 * - Let dp[i][j] denote whether prefix s1[0..i-1] and s2[0..j-1] interleave to form s3[0..i+j-1].
 * - State Transition:
 *     dp[i][j] = (dp[i - 1][j] && s1[i - 1] == s3[i + j - 1]) ||
 *                (dp[i][j - 1] && s2[j - 1] == s3[i + j - 1])
 * - Space Optimization:
 *   Since dp[i][j] only depends on the same column in the previous row (dp[i-1][j])
 *   and the previous column in the current row (dp[i][j-1]), we compress the table
 *   into a 1-D array dp of size n + 1:
 *     dp[j] = (dp[j] && s1[i - 1] == s3[i + j - 1]) || (dp[j - 1] && s2[j - 1] == s3[i + j - 1])
 *
 * Time Complexity: O(m * n) where m = s1.length(), n = s2.length().
 * Space Complexity: O(min(m, n)) auxiliary space using rolling 1-D boolean array.
 */
public class p97 {

    // Method to check if s3 is an interleaving of s1 and s2
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }

        int m = s1.length();
        int n = s2.length();

        if (m + n != s3.length()) {
            return false;
        }

        // Ensure s2 is the shorter string to minimize 1-D array allocation
        if (m < n) {
            return isInterleave(s2, s1, s3);
        }

        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        // Base case: matching s2 alone against s3 prefix
        for (int j = 1; j <= n; j++) {
            dp[j] = dp[j - 1] && (s2.charAt(j - 1) == s3.charAt(j - 1));
        }

        // DP transitions across s1 rows
        for (int i = 1; i <= m; i++) {
            // First column: matching s1 alone against s3 prefix
            dp[0] = dp[0] && (s1.charAt(i - 1) == s3.charAt(i - 1));

            for (int j = 1; j <= n; j++) {
                char s3Char = s3.charAt(i + j - 1);
                boolean fromS1 = dp[j] && (s1.charAt(i - 1) == s3Char);
                boolean fromS2 = dp[j - 1] && (s2.charAt(j - 1) == s3Char);

                dp[j] = fromS1 || fromS2;
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        p97 solver = new p97();

        // Test Case 1: Valid interleaving
        String s1_1 = "aabcc";
        String s2_1 = "dbbca";
        String s3_1 = "aadbbcbcac";
        System.out.println("Test Case 1 Is Interleaving: " +
                solver.isInterleave(s1_1, s2_1, s3_1)); // Expected: true

        // Test Case 2: Invalid interleaving
        String s1_2 = "aabcc";
        String s2_2 = "dbbca";
        String s3_2 = "aadbbbaccc";
        System.out.println("Test Case 2 Is Interleaving: " +
                solver.isInterleave(s1_2, s2_2, s3_2)); // Expected: false

        // Test Case 3: Empty strings
        System.out.println("Test Case 3 Is Interleaving: " +
                solver.isInterleave("", "", "")); // Expected: true

        // Test Case 4: Length mismatch
        System.out.println("Test Case 4 Is Interleaving: " +
                solver.isInterleave("a", "b", "abc")); // Expected: false
    }
}
