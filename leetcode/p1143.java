// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 1143: Longest Common Subsequence
 * Given two strings text1 and text2, return the length of their longest common subsequence.
 * If there is no common subsequence, return 0.
 *
 * 2-D Dynamic Programming (Space-Optimized to 1-D):
 * - If text1[i - 1] == text2[j - 1], characters match:
 *   dp[i][j] = 1 + dp[i - 1][j - 1] (extend subsequence by 1).
 * - Else:
 *   dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]) (skip either character).
 * - Space can be optimized to 1-D array using a rolling previous diagonal variable.
 *
 * Time Complexity: O(m * n) where m = text1.length(), n = text2.length()
 * Space Complexity: O(min(m, n)) for the space-optimized DP array
 */
public class p1143 {

    // Method to find the length of longest common subsequence
    public int longestCommonSubsequence(String text1, String text2) {

        // Ensure text2 is the shorter string for space optimization
        if (text1.length() < text2.length()) {
            return longestCommonSubsequence(text2, text1);
        }

        int m = text1.length();
        int n = text2.length();

        // 1-D DP array
        int[] dp = new int[n + 1];

        for (int i = 1; i <= m; i++) {
            int prevDiagonal = 0; // Represents dp[i - 1][j - 1]

            for (int j = 1; j <= n; j++) {
                int temp = dp[j]; // Store dp[i - 1][j] before overwriting

                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[j] = 1 + prevDiagonal;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }

                prevDiagonal = temp; // Update diagonal for next column
            }
        }

        return dp[n];
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p1143 obj = new p1143();

        // Test case 1: "abcde" and "ace" -> "ace" length 3
        String text1_a = "abcde";
        String text2_a = "ace";
        System.out.println("LCS length 1: " + obj.longestCommonSubsequence(text1_a, text2_a)); // 3

        // Test case 2: "abc" and "abc" -> 3
        String text1_b = "abc";
        String text2_b = "abc";
        System.out.println("LCS length 2: " + obj.longestCommonSubsequence(text1_b, text2_b)); // 3

        // Test case 3: "abc" and "def" -> 0
        String text1_c = "abc";
        String text2_c = "def";
        System.out.println("LCS length 3: " + obj.longestCommonSubsequence(text1_c, text2_c)); // 0
    }
}
