// Defines that this class belongs to the leetcode package
package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * LeetCode 127: Word Ladder
 *
 * Given two words, beginWord and endWord, and a dictionary wordList, return the number of words
 * in the shortest transformation sequence from beginWord to endWord, where each adjacent pair
 * of words differs by a single letter and every intermediate word exists in wordList. Return 0
 * if no such transformation sequence exists.
 *
 * Optimal Approach: Bidirectional Breadth-First Search (BFS)
 * - Convert wordList into a HashSet for O(1) dictionary lookups. If endWord is missing, return 0.
 * - Maintain two search frontiers: beginSet (from beginWord) and endSet (from endWord).
 * - Always expand the smaller frontier at each step to minimize branching (b^d -> 2 * b^(d/2)).
 * - For each word in the smaller frontier, mutate every character position through 'a' to 'z':
 *   - If the mutated word is already in the opposite frontier, a shortest bridge is found;
 *     return current sequence length + 1.
 *   - If it exists in dict, remove it from dict (marking visited) and add it to the next frontier.
 *
 * Time Complexity: O(N * L * 26) worst-case where N is wordList size and L is word length,
 *                  dramatically faster in practice due to bidirectional search pruning.
 * Space Complexity: O(N * L) for the dictionary HashSet and BFS frontier sets.
 */
public class p127 {

    // Method to find the length of the shortest transformation sequence
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord == null || endWord == null || wordList == null) {
            return 0;
        }
        if (beginWord.equals(endWord)) {
            return 1;
        }

        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) {
            return 0;
        }

        // Remove endpoints so they are not revisited as intermediate nodes
        dict.remove(beginWord);
        dict.remove(endWord);

        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);

        int sequenceLength = 1;

        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            // Always expand from the smaller frontier for balanced search
            if (beginSet.size() > endSet.size()) {
                Set<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
            }

            Set<String> nextLevel = new HashSet<>();

            for (String word : beginSet) {
                char[] chars = word.toCharArray();

                for (int i = 0; i < chars.length; i++) {
                    char originalChar = chars[i];

                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == originalChar) {
                            continue;
                        }
                        chars[i] = c;
                        String nextWord = new String(chars);

                        // Frontiers meet -> shortest transformation path completed
                        if (endSet.contains(nextWord)) {
                            return sequenceLength + 1;
                        }

                        if (dict.remove(nextWord)) {
                            nextLevel.add(nextWord);
                        }
                    }

                    chars[i] = originalChar;
                }
            }

            beginSet = nextLevel;
            sequenceLength++;
        }

        return 0;
    }

    public static void main(String[] args) {
        p127 solver = new p127();

        // Test Case 1: Standard transformation path of length 5
        List<String> dict1 = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        System.out.println("Test Case 1 Ladder Length: " +
                solver.ladderLength("hit", "cog", dict1)); // Expected: 5

        // Test Case 2: endWord not in wordList
        List<String> dict2 = Arrays.asList("hot", "dot", "dog", "lot", "log");
        System.out.println("Test Case 2 Ladder Length: " +
                solver.ladderLength("hit", "cog", dict2)); // Expected: 0

        // Test Case 3: Direct 1-step transformation
        List<String> dict3 = Arrays.asList("a", "b", "c");
        System.out.println("Test Case 3 Ladder Length: " +
                solver.ladderLength("a", "c", dict3)); // Expected: 2

        // Test Case 4: Disconnected dictionary components
        List<String> dict4 = Arrays.asList("hot", "dog");
        System.out.println("Test Case 4 Ladder Length: " +
                solver.ladderLength("hot", "dog", dict4)); // Expected: 0
    }
}
