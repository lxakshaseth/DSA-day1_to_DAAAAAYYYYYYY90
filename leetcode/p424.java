// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 424: Longest Repeating Character Replacement
 * You are given a string s consisting of uppercase English letters and an integer k.
 * You can choose any character of the string and change it to any other uppercase English
 * character at most k times. Return the length of the longest substring containing the same letter.
 *
 * Sliding Window Approach:
 * - Maintain a frequency array of size 26 for characters in the current window [left, right].
 * - The number of characters that need replacement in the current window is:
 *   replacementsNeeded = (windowLength) - maxFrequency
 * - If replacementsNeeded <= k, the entire window can be converted to the most frequent character.
 * - If replacementsNeeded > k, the window is invalid; shrink from the left until valid again.
 * - Track the maximum valid window length found.
 *
 * Time Complexity: O(n) where n is s.length() (each pointer advances at most n times)
 * Space Complexity: O(1) auxiliary space (fixed 26-element array for character counts)
 */
public class p424 {

    // Method to find the length of longest repeating character substring after k replacements
    public int characterReplacement(String s, int k) {

        int[] count = new int[26];
        int left = 0;
        int maxFreq = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {

            // Increment frequency of current character
            int charIndex = s.charAt(right) - 'A';
            count[charIndex]++;

            // Update highest frequency of any character seen in the current window
            maxFreq = Math.max(maxFreq, count[charIndex]);

            // Number of characters to replace = current window length - maxFreq
            // If replacements needed exceed k, shrink window from left
            while ((right - left + 1) - maxFreq > k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }

            // Update maximum valid substring length
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p424 obj = new p424();

        // Test case 1: s = "ABAB", k = 2 -> 4
        String s1 = "ABAB";
        int k1 = 2;
        System.out.println("Longest repeating substring for \"ABAB\" (k=2): " + obj.characterReplacement(s1, k1));

        // Test case 2: s = "AABABBA", k = 1 -> 4
        String s2 = "AABABBA";
        int k2 = 1;
        System.out.println("Longest repeating substring for \"AABABBA\" (k=1): " + obj.characterReplacement(s2, k2));

        // Test case 3: s = "AAAA", k = 0 -> 4
        String s3 = "AAAA";
        int k3 = 0;
        System.out.println("Longest repeating substring for \"AAAA\" (k=0): " + obj.characterReplacement(s3, k3));

        // Test case 4: s = "ABBB", k = 2 -> 4
        String s4 = "ABBB";
        int k4 = 2;
        System.out.println("Longest repeating substring for \"ABBB\" (k=2): " + obj.characterReplacement(s4, k4));
    }
}
