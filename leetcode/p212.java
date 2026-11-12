// Defines that this class belongs to the leetcode package
package leetcode;

import java.util.ArrayList;
import java.util.List;

/*
 * LeetCode 212: Word Search II
 *
 * Given an m x n board of characters and a list of strings words, return all words on the board.
 * Each word must be constructed from letters of sequentially adjacent cells (horizontally or
 * vertically neighboring), where the same letter cell may not be used more than once in a word.
 *
 * Optimal Approach: Prefix Tree (Trie) + Backtracking DFS + Leaf Pruning
 * - Insert all target words into a Trie, storing the complete word directly on terminal nodes.
 * - Track the number of active children (childCount) on each TrieNode to allow O(1) dead-branch pruning.
 * - Traverse every cell in the m x n grid as a potential starting letter if it exists in root.children.
 * - During DFS:
 *   - If current TrieNode holds a word, add it to the result and clear node.word to avoid duplicates.
 *   - Temporarily mark board[r][c] = '#' as visited, recurse into valid 4-directional neighbors
 *     whose characters exist in node.children, then restore board[r][c].
 *   - After unwinding, if node.word == null and node.childCount == 0, prune node from its parent.
 *
 * Time Complexity: O(M * N * 3^L) worst-case where M*N is grid size and L is max word length
 *                  (greatly accelerated in practice by prefix sharing and progressive leaf pruning).
 * Space Complexity: O(W * L) for the Trie where W is the number of words, plus O(L) recursion stack.
 */
public class p212 {

    private static class TrieNode {
        final TrieNode[] children = new TrieNode[26];
        String word;
        int childCount;
    }

    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    // Method to find all words from the dictionary present on the board
    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        if (board == null || board.length == 0 || board[0].length == 0 || words == null || words.length == 0) {
            return result;
        }

        TrieNode root = buildTrie(words);
        int rows = board.length;
        int cols = board[0].length;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int idx = board[r][c] - 'a';
                if (root.children[idx] != null) {
                    dfs(board, r, c, root, result);
                }
            }
        }

        return result;
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode curr = root;
            for (int i = 0; i < word.length(); i++) {
                int idx = word.charAt(i) - 'a';
                if (curr.children[idx] == null) {
                    curr.children[idx] = new TrieNode();
                    curr.childCount++;
                }
                curr = curr.children[idx];
            }
            curr.word = word;
        }
        return root;
    }

    private void dfs(char[][] board, int r, int c, TrieNode parent, List<String> result) {
        char ch = board[r][c];
        int charIdx = ch - 'a';
        TrieNode node = parent.children[charIdx];

        if (node.word != null) {
            result.add(node.word);
            node.word = null;
        }

        // Mark visited cell in-place and explore neighbors only if subtrees remain
        board[r][c] = '#';
        if (node.childCount > 0) {
            int rows = board.length;
            int cols = board[0].length;
            for (int[] dir : DIRS) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && board[nr][nc] != '#') {
                    int nextIdx = board[nr][nc] - 'a';
                    if (node.children[nextIdx] != null) {
                        dfs(board, nr, nc, node, result);
                    }
                }
            }
        }
        board[r][c] = ch;

        // Progressive leaf pruning: remove exhausted branches from parent
        if (node.word == null && node.childCount == 0) {
            parent.children[charIdx] = null;
            parent.childCount--;
        }
    }

    public static void main(String[] args) {
        p212 solver = new p212();

        // Test Case 1: Standard dictionary search
        char[][] board1 = {
            {'o', 'a', 'a', 'n'},
            {'e', 't', 'a', 'e'},
            {'i', 'h', 'k', 'r'},
            {'i', 'f', 'l', 'v'}
        };
        String[] words1 = {"oath", "pea", "eat", "rain"};
        System.out.println("Test Case 1 Found Words: " + solver.findWords(board1, words1)); // Expected: [oath, eat]

        // Test Case 2: No cell reuse allowed
        char[][] board2 = {
            {'a', 'b'},
            {'c', 'd'}
        };
        String[] words2 = {"abcb"};
        System.out.println("Test Case 2 Found Words: " + solver.findWords(board2, words2)); // Expected: []

        // Test Case 3: Overlapping prefix words on 1x2 board
        char[][] board3 = {
            {'a', 'a'}
        };
        String[] words3 = {"a", "aa", "aaa"};
        System.out.println("Test Case 3 Found Words: " + solver.findWords(board3, words3)); // Expected: [a, aa]
    }
}
