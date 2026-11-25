// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 211: Design Add and Search Words Data Structure
 *
 * Design a data structure supporting adding words and searching words with '.' wildcards,
 * where '.' can match any lowercase letter.
 *
 * Optimal Approach: Prefix Tree (Trie) with Depth-First Search for Wildcards
 * - Maintain a 26-way TrieNode tree:
 *   - children: Array of size 26 for letters 'a' through 'z'.
 *   - isWord: Boolean flag marking complete word terminations.
 * - addWord(word): Standard iterative Trie insertion in O(L) time.
 * - search(word): Recursive DFS matching character-by-character:
 *   - If character is a letter: advance directly to the corresponding child node.
 *   - If character is '.': iterate through all 26 non-null children and recurse;
 *     return true as soon as any branch matches.
 *   - Base Case: When index == word.length(), return current node's isWord flag.
 *
 * Time Complexity:
 * - addWord: O(L) where L is the word length.
 * - search: O(L) for literal words; bounded by O(26^k * L) where k is the number of dots (k <= 2).
 * Space Complexity: O(N * L) for storing N words in the Trie.
 */
public class p211 {

    public static class WordDictionary {

        private static class TrieNode {
            final TrieNode[] children = new TrieNode[26];
            boolean isWord;
        }

        private final TrieNode root;

        public WordDictionary() {
            this.root = new TrieNode();
        }

        // Inserts a word into the Trie
        public void addWord(String word) {
            if (word == null) {
                return;
            }

            TrieNode curr = root;
            for (int i = 0; i < word.length(); i++) {
                int idx = word.charAt(i) - 'a';
                if (curr.children[idx] == null) {
                    curr.children[idx] = new TrieNode();
                }
                curr = curr.children[idx];
            }
            curr.isWord = true;
        }

        // Searches for word with support for '.' wildcard matching
        public boolean search(String word) {
            if (word == null) {
                return false;
            }
            return dfs(word, 0, root);
        }

        private boolean dfs(String word, int index, TrieNode node) {
            if (node == null) {
                return false;
            }

            // Reached the end of the query word
            if (index == word.length()) {
                return node.isWord;
            }

            char ch = word.charAt(index);

            if (ch != '.') {
                int idx = ch - 'a';
                return dfs(word, index + 1, node.children[idx]);
            }

            // Wildcard '.' matches any valid letter branch
            for (TrieNode child : node.children) {
                if (child != null && dfs(word, index + 1, child)) {
                    return true;
                }
            }

            return false;
        }
    }

    public static void main(String[] args) {
        WordDictionary wd = new WordDictionary();

        wd.addWord("bad");
        wd.addWord("dad");
        wd.addWord("mad");

        // Test Case 1: Exact search non-existent word
        System.out.println("Search \"pad\": " + wd.search("pad")); // Expected: false

        // Test Case 2: Exact search existing word
        System.out.println("Search \"bad\": " + wd.search("bad")); // Expected: true

        // Test Case 3: Wildcard prefix
        System.out.println("Search \".ad\": " + wd.search(".ad")); // Expected: true

        // Test Case 4: Multiple wildcards
        System.out.println("Search \"b..\": " + wd.search("b..")); // Expected: true

        // Test Case 5: Partial word prefix (should return false because "ba" is not a terminal word)
        System.out.println("Search \"ba\": " + wd.search("ba")); // Expected: false

        // Test Case 6: Word with trailing wildcard
        wd.addWord("a");
        System.out.println("Search \".\": " + wd.search(".")); // Expected: true
    }
}
