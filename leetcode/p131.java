// Defines that this class belongs to the leetcode package
package leetcode;

// Import utility classes
import java.util.ArrayList;
import java.util.List;

/*
 * LeetCode 131: Palindrome Partitioning
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 *
 * Backtracking with Palindrome Validation Approach:
 * - Use recursive backtracking starting from index 0.
 * - At each step, consider all possible prefixes s[start...end]:
 *   1. Check if the prefix s[start...end] is a palindrome using two pointers (left and right).
 *   2. If it is a palindrome:
 *      - Add s.substring(start, end + 1) to the current partition list.
 *      - Recurse for the remainder of the string starting from end + 1.
 *      - Remove the last added substring (backtrack).
 * - Base Case: When start == s.length(), the entire string has been partitioned into palindromes;
 *   add a copy of current to the result list.
 *
 * Time Complexity: O(n * 2^n) where n is s.length() (at most 2^(n-1) partitions, each taking O(n))
 * Space Complexity: O(n) recursion call stack depth and partition buffer (excluding result list)
 */
public class p131 {

    // Helper method to verify if substring s[left...right] is a palindrome
    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    // Helper backtracking method
    private void backtrack(int start, String s, List<String> current, List<List<String>> result) {

        // Base case: entire string successfully partitioned into palindromes
        if (start == s.length()) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Try every possible substring starting from index start
        for (int end = start; end < s.length(); end++) {
            if (isPalindrome(s, start, end)) {
                // Choice: add palindromic substring
                current.add(s.substring(start, end + 1));

                // Explore: recurse with the remaining substring
                backtrack(end + 1, s, current, result);

                // Undo: backtrack
                current.remove(current.size() - 1);
            }
        }
    }

    // Method to partition string into palindromic substrings
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return result;
        }
        backtrack(0, s, new ArrayList<>(), result);
        return result;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p131 obj = new p131();

        // Test case 1: "aab" -> [["a","a","b"],["aa","b"]]
        String s1 = "aab";
        System.out.println("Partitions for \"aab\": " + obj.partition(s1));

        // Test case 2: "a" -> [["a"]]
        String s2 = "a";
        System.out.println("Partitions for \"a\": " + obj.partition(s2));

        // Test case 3: "racecar"
        String s3 = "racecar";
        System.out.println("Partitions count for \"racecar\": " + obj.partition(s3).size());
        System.out.println("Partitions for \"racecar\": " + obj.partition(s3));
    }
}
