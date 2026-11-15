// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 72: Edit Distance
 *
 * Given two strings word1 and word2, return the minimum number of operations
 * required to convert word1 to word2 (insert, delete, or replace a character).
 *
 * Optimal Approach: 2-D Dynamic Programming with 1-D Space Optimization
 * - Let dp[i][j] represent the minimum edit distance to transform prefix
 *   word1[0..i-1] into word2[0..j-1].
 * - Base Cases:
 *   - Converting an empty string to word2[0..j-1] requires j insertions: dp[0][j] = j.
 *   - Converting word1[0..i-1] to an empty string requires i deletions: dp[i][0] = i.
 * - Transitions:
 *   - If word1[i - 1] == word2[j - 1]:
 *       dp[i][j] = dp[i - 1][j - 1] (characters match, 0 cost).
 *   - Else:
 *       dp[i][j] = 1 + min(
 *           dp[i - 1][j],     // Delete from word1
 *           dp[i][j - 1],     // Insert into word1
 *           dp[i - 1][j - 1]  // Replace character
 *       )
 * - Space Optimization:
 *   Notice dp[i][j] only relies on the previous row's current column dp[i - 1][j],
 *   the current row's left column dp[i][j - 1], and the upper-left diagonal dp[i - 1][j - 1].
 *   We compress the table into a single 1-D array of size min(m, n) + 1 and track
 *   the diagonal value across inner iterations.
 *
 * Time Complexity: O(m * n) where m = word1.length(), n = word2.length().
 * Space Complexity: O(min(m, n)) auxiliary space using rolling 1-D DP array.
 */
public class p72 {

    // Method to calculate the minimum edit distance between word1 and word2
    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return 0;
        }

        // Ensure word2 is the shorter string to optimize 1-D array allocation
        if (word1.length() < word2.length()) {
            return minDistance(word2, word1);
        }

        int m = word1.length();
        int n = word2.length();

        // dp[j] holds edit distance for converting word1 prefix to word2[0..j-1]
        int[] dp = new int[n + 1];

        // Base case: transforming empty string into word2 prefix of length j
        for (int j = 0; j <= n; j++) {
            dp[j] = j;
        }

        for (int i = 1; i <= m; i++) {
            int prevDiagonal = dp[0];
            dp[0] = i; // Base case: converting word1 prefix of length i to empty string

            for (int j = 1; j <= n; j++) {
                int temp = dp[j];

                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[j] = prevDiagonal;
                } else {
                    dp[j] = 1 + Math.min(prevDiagonal, Math.min(dp[j], dp[j - 1]));
                }

                prevDiagonal = temp;
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        p72 solver = new p72();

        // Test Case 1: "horse" -> "ros"
        System.out.println("Test Case 1 (\"horse\" -> \"ros\"): " +
                solver.minDistance("horse", "ros")); // Expected: 3

        // Test Case 2: "intention" -> "execution"
        System.out.println("Test Case 2 (\"intention\" -> \"execution\"): " +
                solver.minDistance("intention", "execution")); // Expected: 5

        // Test Case 3: Empty string transformation
        System.out.println("Test Case 3 (\"\" -> \"abc\"): " +
                solver.minDistance("", "abc")); // Expected: 3

        // Test Case 4: Identical strings
        System.out.println("Test Case 4 (\"leetcode\" -> \"leetcode\"): " +
                solver.minDistance("leetcode", "leetcode")); // Expected: 0
    }
}
