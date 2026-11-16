// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 115: Distinct Subsequences
 *
 * Given two strings s and t, return the number of distinct subsequences of s
 * which equals t. The test cases guarantee the answer fits in a 32-bit signed integer.
 *
 * Optimal Approach: 2-D Dynamic Programming with 1-D Space Optimization
 * - Let dp[i][j] be the number of distinct subsequences of s[0..i-1] that match t[0..j-1].
 * - Base Cases:
 *   - dp[i][0] = 1 for all i: An empty target string t can be formed from any prefix
 *     of s in exactly 1 way (by deleting all characters).
 *   - dp[0][j] = 0 for j > 0: A non-empty target string t cannot be formed from an empty s.
 * - Transitions:
 *   - If s[i - 1] == t[j - 1]:
 *       dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j]
 *       (Choice 1: use s[i-1] to match t[j-1] + Choice 2: ignore s[i-1])
 *   - If s[i - 1] != t[j - 1]:
 *       dp[i][j] = dp[i - 1][j]
 *       (Must ignore s[i-1])
 * - Space Optimization:
 *   Since dp[i][j] depends only on the current column and the preceding diagonal entry
 *   from row i - 1, we can compress the DP table into a 1-D array of size n + 1.
 *   Iterating j backwards from n down to 1 ensures that dp[j - 1] preserves its value
 *   from row i - 1 before being overwritten.
 *
 * Time Complexity: O(m * n) where m = s.length(), n = t.length().
 * Space Complexity: O(n) auxiliary space using a 1-D DP array.
 */
public class p115 {

    // Method to calculate the number of distinct subsequences of s that equal t
    public int numDistinct(String s, String t) {
        if (s == null || t == null) {
            return 0;
        }

        int m = s.length();
        int n = t.length();

        // If s is shorter than t, it cannot contain t as a subsequence
        if (m < n) {
            return 0;
        }

        // dp[j] stores distinct subsequences of current prefix of s matching t[0..j-1]
        int[] dp = new int[n + 1];

        // Base case: empty string t can always be formed once
        dp[0] = 1;

        for (int i = 1; i <= m; i++) {
            char sc = s.charAt(i - 1);
            // Iterate backwards to safely reuse row i - 1 values in-place
            for (int j = n; j >= 1; j--) {
                if (sc == t.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        p115 solver = new p115();

        // Test Case 1: "rabbbit" vs "rabbit"
        System.out.println("Test Case 1 (\"rabbbit\", \"rabbit\"): " +
                solver.numDistinct("rabbbit", "rabbit")); // Expected: 3

        // Test Case 2: "babgbag" vs "bag"
        System.out.println("Test Case 2 (\"babgbag\", \"bag\"): " +
                solver.numDistinct("babgbag", "bag")); // Expected: 5

        // Test Case 3: Completely distinct characters
        System.out.println("Test Case 3 (\"abc\", \"def\"): " +
                solver.numDistinct("abc", "def")); // Expected: 0

        // Test Case 4: Identical repeated characters
        System.out.println("Test Case 4 (\"aaaa\", \"aa\"): " +
                solver.numDistinct("aaaa", "aa")); // Expected: 6 (4 choose 2)
    }
}
