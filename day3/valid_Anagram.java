/*valid Anagram
Given two strings s and t, return true if t is an anagram of s, and false otherwise.
An Anagram is a word or phrase formed by rearranging the letters of a different 
word or phrase, typically using all the original letters exactly once.
 

s = "race" t="care"  TRUE
 
s = "heart" t="earth"  TRUE

s = "tulip" t = "lipid"  FALSE
 */

package day3;

import java.util.*;

public class valid_Anagram {

    // Function to check whether two strings are anagrams
    public static boolean isAnagram(String s, String t) {

        // Create a HashMap to store character frequencies
        HashMap<Character, Integer> map = new HashMap<>();

        // Count frequency of every character in string s
        for (int i = 0; i < s.length(); i++) {

            // Get current character
            char ch = s.charAt(i);

            // Increase its frequency
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        // Traverse string t
        for (int i = 0; i < t.length(); i++) {

            // Get current character
            char ch = t.charAt(i);

            // Check if character exists in map
            if (map.get(ch) != null) {

                // If frequency becomes zero, remove character
                if (map.get(ch) == 1) {
                    map.remove(ch);

                } else {

                    // Decrease character frequency
                    map.put(ch, map.get(ch) - 1);
                }

            } else {

                // Character doesn't exist in s
                // Therefore, strings are not anagrams
                return false;
            }
        }

        // If map is empty, both strings have same characters
        return map.isEmpty();
    }

    // Main method
    public static void main(String[] args) {

        // First string
        String s = "race";

        // Second string
        String t = "care";

        // Call isAnagram() and print the result
        System.out.println(isAnagram(s, t));
    }
}