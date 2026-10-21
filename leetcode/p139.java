// Defines that this class belongs to the leetcode package
package leetcode;

// Import utility classes
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * LeetCode 139: Word Break
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented
 * into a space-separated sequence of one or more dictionary words.
 *
 * Dynamic Programming Approach:
 * - dp[i] indicates whether the prefix s[0...i] can be segmented using dictionary words.
 * - Base Case: dp[0] = true (empty string can always be formed).
 * - For each index i from 1 to s.length():
 *   Check all partition points j from 0 to i - 1:
 *   If dp[j] is true and substring s[j...i] exists in wordDict, then dp[i] = true.
 *
 * Time Complexity: O(n^2 * k) where n is length of s, k is average word length
 * Space Complexity: O(n) for the dp array + O(m) for the HashSet
 */
public class p139 {

    // Method to determine if s can be segmented into dictionary words
    public boolean wordBreak(String s, List<String> wordDict) {

        // Store words in a HashSet for O(1) average lookup time
        Set<String> wordSet = new HashSet<>(wordDict);

        int n = s.length();
        boolean[] dp = new boolean[n + 1];

        // Base case: empty prefix is valid
        dp[0] = true;

        // Iterate through all prefix lengths
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {

                // If prefix s[0...j] is valid and remaining substring s[j...i] is in dictionary
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break; // No need to check other partition points for this i
                }
            }
        }

        return dp[n];
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p139 obj = new p139();

        // Test case 1: "leetcode" -> "leet" + "code"
        String s1 = "leetcode";
        List<String> dict1 = Arrays.asList("leet", "code");
        System.out.println("Can break \"" + s1 + "\": " + obj.wordBreak(s1, dict1)); // true

        // Test case 2: "applepenapple" -> "apple" + "pen" + "apple"
        String s2 = "applepenapple";
        List<String> dict2 = Arrays.asList("apple", "pen");
        System.out.println("Can break \"" + s2 + "\": " + obj.wordBreak(s2, dict2)); // true

        // Test case 3: "catsandog"
        String s3 = "catsandog";
        List<String> dict3 = Arrays.asList("cats", "dog", "sand", "and", "cat");
        System.out.println("Can break \"" + s3 + "\": " + obj.wordBreak(s3, dict3)); // false
    }
}
