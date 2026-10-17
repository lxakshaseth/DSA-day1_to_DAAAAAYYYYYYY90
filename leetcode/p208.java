// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 208: Implement Trie (Prefix Tree)
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently
 * store and retrieve keys in a dataset of strings.
 *
 * Implement the Trie class:
 * - Trie() Initializes the trie object.
 * - void insert(String word) Inserts the string word into the trie.
 * - boolean search(String word) Returns true if the string word is in the trie.
 * - boolean startsWith(String prefix) Returns true if there is a previously inserted string
 *   that has the prefix prefix.
 *
 * Time Complexity:
 * - insert: O(L) where L is word length
 * - search: O(L)
 * - startsWith: O(L)
 * Space Complexity: O(N * L) where N is number of words, L is average word length
 */
public class p208 {

    // Node representation in the Trie
    static class TrieNode {
        TrieNode[] children;
        boolean isEndOfWord;

        public TrieNode() {
            children = new TrieNode[26]; // 26 lowercase English letters
            isEndOfWord = false;
        }
    }

    public static class Trie {

        private TrieNode root;

        // Constructor to initialize root node
        public Trie() {
            root = new TrieNode();
        }

        // Inserts a word into the trie
        public void insert(String word) {
            TrieNode curr = root;

            for (char ch : word.toCharArray()) {
                int index = ch - 'a';
                if (curr.children[index] == null) {
                    curr.children[index] = new TrieNode();
                }
                curr = curr.children[index];
            }

            curr.isEndOfWord = true; // Mark end of word
        }

        // Returns true if the word is in the trie
        public boolean search(String word) {
            TrieNode curr = root;

            for (char ch : word.toCharArray()) {
                int index = ch - 'a';
                if (curr.children[index] == null) {
                    return false; // Character path does not exist
                }
                curr = curr.children[index];
            }

            return curr.isEndOfWord; // Must be marked as complete word
        }

        // Returns true if there is any word in the trie that starts with the given prefix
        public boolean startsWith(String prefix) {
            TrieNode curr = root;

            for (char ch : prefix.toCharArray()) {
                int index = ch - 'a';
                if (curr.children[index] == null) {
                    return false; // Prefix path does not exist
                }
                curr = curr.children[index];
            }

            return true; // All prefix characters found
        }
    }

    // Main method to test the solution
    public static void main(String[] args) {

        Trie trie = new Trie();

        trie.insert("apple");
        System.out.println("Search 'apple': " + trie.search("apple"));     // true
        System.out.println("Search 'app': " + trie.search("app"));         // false
        System.out.println("StartsWith 'app': " + trie.startsWith("app")); // true

        trie.insert("app");
        System.out.println("Search 'app' after insert: " + trie.search("app")); // true
    }
}
