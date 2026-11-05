// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 76: Minimum Window Substring
 * Given two strings s and t of lengths m and n respectively, return the minimum window
 * substring of s such that every character in t (including duplicates) is included in
 * the window. If there is no such substring, return the empty string "".
 *
 * Sliding Window + ASCII Frequency Array Approach:
 * - Use a frequency array of size 128 to record character counts needed for string t.
 * - Track formed: how many unique characters in t currently have their required frequencies met in the window.
 * - Two-pointer Sliding Window:
 *   1. Expand the right pointer right to include characters into the window.
 *      When windowMap[c] matches targetMap[c], increment formed.
 *   2. Once formed == required, the window is valid. Try to shrink from the left pointer left
 *      to find the minimal valid window, updating the minimum window coordinates whenever a shorter
 *      valid substring is found.
 *   3. If removing s.charAt(left) causes its count in the window to drop below the requirement in t,
 *      decrement formed and stop shrinking.
 *
 * Time Complexity: O(m + n) where m is s.length() and n is t.length() (each pointer moves at most m steps)
 * Space Complexity: O(1) auxiliary space (fixed ASCII array of size 128)
 */
public class p76 {

    // Method to find the minimum window substring
    public String minWindow(String s, String t) {

        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        // Target character frequencies in t
        int[] targetMap = new int[128];
        int required = 0;
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (targetMap[c] == 0) {
                required++;
            }
            targetMap[c]++;
        }

        // Current window frequencies
        int[] windowMap = new int[128];
        int formed = 0;

        int left = 0;
        int minLen = Integer.MAX_VALUE;
        int minStart = 0;

        // Expand the right boundary of the window
        for (int right = 0; right < s.length(); right++) {
            char rChar = s.charAt(right);
            windowMap[rChar]++;

            // Check if current character's frequency requirement is satisfied
            if (targetMap[rChar] > 0 && windowMap[rChar] == targetMap[rChar]) {
                formed++;
            }

            // Shrink window from the left while it remains valid
            while (formed == required) {
                // Update minimal window
                int currentLen = right - left + 1;
                if (currentLen < minLen) {
                    minLen = currentLen;
                    minStart = left;
                }

                char lChar = s.charAt(left);
                windowMap[lChar]--;

                // If removing lChar breaks validity
                if (targetMap[lChar] > 0 && windowMap[lChar] < targetMap[lChar]) {
                    formed--;
                }

                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p76 obj = new p76();

        // Test case 1: s = "ADOBECODEBANC", t = "ABC" -> "BANC"
        String s1 = "ADOBECODEBANC";
        String t1 = "ABC";
        System.out.println("Min window for (\"" + s1 + "\", \"" + t1 + "\"): " + obj.minWindow(s1, t1));

        // Test case 2: s = "a", t = "a" -> "a"
        String s2 = "a";
        String t2 = "a";
        System.out.println("Min window for (\"" + s2 + "\", \"" + t2 + "\"): " + obj.minWindow(s2, t2));

        // Test case 3: s = "a", t = "aa" -> ""
        String s3 = "a";
        String t3 = "aa";
        System.out.println("Min window for (\"" + s3 + "\", \"" + t3 + "\"): " + obj.minWindow(s3, t3));

        // Test case 4: s = "ab", t = "b" -> "b"
        String s4 = "ab";
        String t4 = "b";
        System.out.println("Min window for (\"" + s4 + "\", \"" + t4 + "\"): " + obj.minWindow(s4, t4));
    }
}
