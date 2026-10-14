// Defines that this class belongs to the leetcode package
package leetcode;

// Imports HashSet and Set from Java's utility package
import java.util.HashSet;
import java.util.Set;

/*
 * LeetCode 3: Longest Substring Without Repeating Characters
 * Given a string s, find the length of the longest substring without duplicate characters.
 *
 * Sliding Window approach using HashSet:
 * Time Complexity: O(n)
 * Space Complexity: O(min(m, n)) where m is size of charset
 */
public class p3 {

    // Method to find length of longest substring without repeating characters
    public int lengthOfLongestSubstring(String s) {

        // Set to store unique characters in current window
        Set<Character> set = new HashSet<>();

        // Left pointer of sliding window
        int left = 0;

        // Variable to track maximum length found
        int maxLength = 0;

        // Right pointer expands the window
        for (int right = 0; right < s.length(); right++) {

            // If duplicate character is present, shrink window from left
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }

            // Add current character to set
            set.add(s.charAt(right));

            // Update maximum length
            maxLength = Math.max(maxLength, right - left + 1);
        }

        // Return maximum length of substring found
        return maxLength;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        // Create object of p3
        p3 obj = new p3();

        // Test string
        String s = "abcabcbb";

        // Print length of longest substring without repeating characters
        System.out.println("Longest substring length: " + obj.lengthOfLongestSubstring(s));
    }
}
