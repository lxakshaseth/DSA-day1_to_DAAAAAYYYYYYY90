// Defines that this class belongs to the leetcode package
package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * LeetCode 140: Word Break II
 *
 * Given a string s and a dictionary of strings wordDict, add spaces in s to construct
 * a sentence where each word is a valid dictionary word. Return all such valid sentences.
 *
 * Optimal Approach: Depth-First Search (DFS) with Memoization
 * - Store words in a HashSet for O(1) prefix lookup.
 * - Recursively decompose string s from left to right:
 *   - For each prefix s[0..end-1] found in the dictionary, recursively find all valid
 *     sentences for the remaining suffix s[end..len-1].
 *   - Concatenate the prefix with each sentence from the suffix recursive result.
 *   - Base Case: When the remaining suffix is empty, return a list containing an empty string ("").
 * - Memoization:
 *   - Cache results in a Map<String, List<String>> (suffix -> valid sentences) to avoid
 *     recomputing exponential subtrees for recurring suffix overlaps or dead ends.
 *
 * Time Complexity: O(2^n * n) worst-case bounded by the number of valid sentences (n <= 20).
 * Space Complexity: O(2^n * n) for storing intermediate suffix sentences in the memoization table.
 */
public class p140 {

    // Method to return all valid sentences formed by segmenting s
    public List<String> wordBreak(String s, List<String> wordDict) {
        if (s == null || wordDict == null || s.isEmpty()) {
            return new ArrayList<>();
        }

        Set<String> wordSet = new HashSet<>(wordDict);
        Map<String, List<String>> memo = new HashMap<>();

        return dfs(s, wordSet, memo);
    }

    private List<String> dfs(String remaining, Set<String> wordSet, Map<String, List<String>> memo) {
        if (memo.containsKey(remaining)) {
            return memo.get(remaining);
        }

        List<String> sentences = new ArrayList<>();

        // Base case: successfully partitioned entire string
        if (remaining.isEmpty()) {
            sentences.add("");
            return sentences;
        }

        for (int end = 1; end <= remaining.length(); end++) {
            String prefix = remaining.substring(0, end);

            if (wordSet.contains(prefix)) {
                List<String> suffixSentences = dfs(remaining.substring(end), wordSet, memo);

                for (String suffixSentence : suffixSentences) {
                    if (suffixSentence.isEmpty()) {
                        sentences.add(prefix);
                    } else {
                        sentences.add(prefix + " " + suffixSentence);
                    }
                }
            }
        }

        memo.put(remaining, sentences);
        return sentences;
    }

    public static void main(String[] args) {
        p140 solver = new p140();

        // Test Case 1: Standard branching
        String s1 = "catsanddog";
        List<String> dict1 = Arrays.asList("cat", "cats", "and", "sand", "dog");
        System.out.println("Test Case 1 Sentences: " + solver.wordBreak(s1, dict1));
        // Expected: ["cat sand dog", "cats and dog"] (in any order)

        // Test Case 2: Overlapping prefixes and suffixes
        String s2 = "pineapplepenapple";
        List<String> dict2 = Arrays.asList("apple", "pen", "applepen", "pine", "pineapple");
        System.out.println("Test Case 2 Sentences: " + solver.wordBreak(s2, dict2));
        // Expected: ["pine apple pen apple", "pine applepen apple", "pineapple pen apple"]

        // Test Case 3: Suffix cannot be partitioned
        String s3 = "catsandog";
        List<String> dict3 = Arrays.asList("cats", "dog", "sand", "and", "cat");
        System.out.println("Test Case 3 Sentences: " + solver.wordBreak(s3, dict3));
        // Expected: []
    }
}
