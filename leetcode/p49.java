// Defines that this class belongs to the leetcode package
package leetcode;

// Import necessary utility classes
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * LeetCode 49: Group Anagrams
 * Given an array of strings strs, group the anagrams together.
 * You can return the answer in any order.
 *
 * Time Complexity: O(n * k log k) where n is number of strings, k is max string length
 * Space Complexity: O(n * k)
 */
public class p49 {

    // Method to group anagrams together
    public List<List<String>> groupAnagrams(String[] strs) {

        // Create a HashMap with sorted word as key and list of anagrams as value
        Map<String, List<String>> map = new HashMap<>();

        // Traverse every string in the array
        for (String s : strs) {

            // Convert string to character array to sort it
            char[] chars = s.toCharArray();
            Arrays.sort(chars);

            // Create canonical key from sorted characters
            String key = new String(chars);

            // If key is not present in map, initialize a new ArrayList
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }

            // Add original string to corresponding anagram group
            map.get(key).add(s);
        }

        // Return all grouped anagram lists
        return new ArrayList<>(map.values());
    }

    // Main method to test
    public static void main(String[] args) {

        p49 obj = new p49();

        // Sample input array
        String[] strs = { "eat", "tea", "tan", "ate", "nat", "bat" };

        // Call method and print grouped anagrams
        List<List<String>> result = obj.groupAnagrams(strs);
        System.out.println("Grouped Anagrams: " + result);
    }
}
