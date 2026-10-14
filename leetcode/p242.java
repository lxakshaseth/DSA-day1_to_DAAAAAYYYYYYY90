// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 242: Valid Anagram
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 *
 * Frequency Array Approach:
 * - Use an array of size 26 for lowercase English letters.
 * - Increment count for characters in s, decrement for characters in t.
 * - If all counts remain 0, strings are valid anagrams.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1) (since charset size is constant, 26)
 */
public class p242 {

    // Method to check if t is an anagram of s
    public boolean isAnagram(String s, String t) {

        // If lengths differ, they cannot be anagrams
        if (s.length() != t.length()) {
            return false;
        }

        // Frequency counter for 26 lowercase English letters
        int[] count = new int[26];

        // Traverse both strings simultaneously
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }

        // Verify all character frequencies are zero
        for (int c : count) {
            if (c != 0) {
                return false;
            }
        }

        return true;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p242 obj = new p242();

        // Test case 1
        String s1 = "anagram";
        String t1 = "nagaram";
        System.out.println("\"" + s1 + "\" and \"" + t1 + "\" are anagrams: " + obj.isAnagram(s1, t1));

        // Test case 2
        String s2 = "rat";
        String t2 = "car";
        System.out.println("\"" + s2 + "\" and \"" + t2 + "\" are anagrams: " + obj.isAnagram(s2, t2));
    }
}
