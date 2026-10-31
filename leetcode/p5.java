// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 5: Longest Palindromic Substring
 * Given a string s, return the longest palindromic substring in s.
 *
 * Expand Around Center Approach:
 * - A palindrome mirrors around its center.
 * - In a string of length n, there are 2n - 1 potential centers:
 *   1. Odd-length palindromes centered at a single character s[i].
 *   2. Even-length palindromes centered between two characters s[i] and s[i + 1].
 * - For each center, expand outward while characters at left and right indices match.
 * - Keep track of the start index and maximum length found so far.
 *
 * Time Complexity: O(n^2) where n is s.length() (each expansion takes up to O(n))
 * Space Complexity: O(1) auxiliary space (excluding the returned substring)
 */
public class p5 {

    // Helper method to expand around center and return the length of the palindrome
    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // Length of valid palindrome is (right - 1) - (left + 1) + 1 = right - left - 1
        return right - left - 1;
    }

    // Method to find the longest palindromic substring
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        int start = 0;
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            // Odd length palindrome (centered at i)
            int len1 = expandAroundCenter(s, i, i);

            // Even length palindrome (centered between i and i + 1)
            int len2 = expandAroundCenter(s, i, i + 1);

            int currentMax = Math.max(len1, len2);

            if (currentMax > maxLen) {
                maxLen = currentMax;
                // Calculate starting index:
                // For odd (e.g. len = 3, center = 1): start = 1 - (3 - 1)/2 = 0
                // For even (e.g. len = 4, center = 1): start = 1 - (4 - 1)/2 = 0
                start = i - (currentMax - 1) / 2;
            }
        }

        return s.substring(start, start + maxLen);
    }

    // Main method to test the solution
    public static void main(String[] args) {
        p5 obj = new p5();

        // Test case 1: "babad" -> "bab" or "aba"
        String s1 = "babad";
        System.out.println("Longest palindrome in \"" + s1 + "\": " + obj.longestPalindrome(s1));

        // Test case 2: "cbbd" -> "bb"
        String s2 = "cbbd";
        System.out.println("Longest palindrome in \"" + s2 + "\": " + obj.longestPalindrome(s2));

        // Test case 3: "a" -> "a"
        String s3 = "a";
        System.out.println("Longest palindrome in \"" + s3 + "\": " + obj.longestPalindrome(s3));

        // Test case 4: "racecar" -> "racecar"
        String s4 = "racecar";
        System.out.println("Longest palindrome in \"" + s4 + "\": " + obj.longestPalindrome(s4));

        // Test case 5: "abacdfgdcaba" -> "aba"
        String s5 = "abacdfgdcaba";
        System.out.println("Longest palindrome in \"" + s5 + "\": " + obj.longestPalindrome(s5));
    }
}
