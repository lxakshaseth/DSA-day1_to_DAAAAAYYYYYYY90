// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 91: Decode Ways
 * A message containing letters from A-Z can be encoded into numbers using the mapping
 * 'A' -> "1", 'B' -> "2", ..., 'Z' -> "26".
 * Given a string s containing only digits, return the number of ways to decode it.
 *
 * Space-Optimized 1-D Dynamic Programming Approach:
 * - Let dp[i] represent the number of ways to decode the prefix s[0...i-1].
 * - For each position i from 2 to n:
 *   1. Single-digit decode: If s[i - 1] is between '1' and '9', it can be decoded into
 *      a single letter: current += dp[i - 1].
 *   2. Two-digit decode: If the two-digit substring s[i - 2...i - 1] is between 10 and 26,
 *      it can be decoded into a letter: current += dp[i - 2].
 * - Since dp[i] only depends on the previous two states (dp[i - 1] and dp[i - 2]),
 *   we optimize space from O(n) to O(1) using two rolling variables (prev1 and prev2).
 *
 * Time Complexity: O(n) single pass over string of length n
 * Space Complexity: O(1) constant extra space
 */
public class p91 {

    // Method to calculate total decoding ways
    public int numDecodings(String s) {

        // If string is empty or has a leading zero, it cannot be decoded
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }

        int n = s.length();

        // prev2 corresponds to dp[i - 2], prev1 corresponds to dp[i - 1]
        int prev2 = 1; // dp[0] = 1 (empty prefix)
        int prev1 = 1; // dp[1] = 1 (single non-zero character)

        for (int i = 2; i <= n; i++) {
            int current = 0;

            // Single digit decode (s[i - 1])
            int oneDigit = s.charAt(i - 1) - '0';
            if (oneDigit >= 1 && oneDigit <= 9) {
                current += prev1;
            }

            // Two digit decode (s[i - 2 ... i - 1])
            int twoDigits = Integer.parseInt(s.substring(i - 2, i));
            if (twoDigits >= 10 && twoDigits <= 26) {
                current += prev2;
            }

            // Roll the state variables forward
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p91 obj = new p91();

        // Test case 1: "12" -> 2 ("AB" or "L")
        String s1 = "12";
        System.out.println("Decode ways for \"" + s1 + "\": " + obj.numDecodings(s1));

        // Test case 2: "226" -> 3 ("BZ", "VF", "BBF")
        String s2 = "226";
        System.out.println("Decode ways for \"" + s2 + "\": " + obj.numDecodings(s2));

        // Test case 3: "06" -> 0 (leading zero invalid)
        String s3 = "06";
        System.out.println("Decode ways for \"" + s3 + "\": " + obj.numDecodings(s3));

        // Test case 4: "10" -> 1 ("J")
        String s4 = "10";
        System.out.println("Decode ways for \"" + s4 + "\": " + obj.numDecodings(s4));

        // Test case 5: "2101" -> 1 ("2", "10", "1")
        String s5 = "2101";
        System.out.println("Decode ways for \"" + s5 + "\": " + obj.numDecodings(s5));
    }
}
