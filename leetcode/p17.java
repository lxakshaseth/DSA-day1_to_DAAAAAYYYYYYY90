// Defines that this class belongs to the leetcode package
package leetcode;

// Import utility classes
import java.util.ArrayList;
import java.util.List;

/*
 * LeetCode 17: Letter Combinations of a Phone Number
 * Given a string containing digits from 2-9 inclusive, return all possible letter
 * combinations that the number could represent. Return the answer in any order.
 * Mapping of digits to letters:
 * 2 -> "abc", 3 -> "def", 4 -> "ghi", 5 -> "jkl",
 * 6 -> "mno", 7 -> "pqrs", 8 -> "tuv", 9 -> "wxyz"
 *
 * Backtracking / Depth-First Search Approach:
 * - Map each digit '2' through '9' to its corresponding letters using a lookup array.
 * - Recursively explore each letter choice at position index:
 *   1. Base case: When index == digits.length(), a full combination is formed;
 *      append it to the result list.
 *   2. For each character mapped to digits[index]:
 *      - Append the character to a StringBuilder.
 *      - Recurse for the next digit (index + 1).
 *      - Remove the character (backtrack).
 *
 * Time Complexity: O(n * 4^n) where n is digits.length() (at most 4^n combinations, each taking O(n) to construct)
 * Space Complexity: O(n) recursion call stack depth and StringBuilder buffer (excluding result list)
 */
public class p17 {

    // Phone keypad mapping array where index corresponds to digit
    private static final String[] KEYPAD = {
        "",     // 0
        "",     // 1
        "abc",  // 2
        "def",  // 3
        "ghi",  // 4
        "jkl",  // 5
        "mno",  // 6
        "pqrs", // 7
        "tuv",  // 8
        "wxyz"  // 9
    };

    // Helper backtracking method
    private void backtrack(int index, String digits, StringBuilder current, List<String> result) {

        // Base case: full combination constructed
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        // Get letters mapped to the current digit
        String letters = KEYPAD[digits.charAt(index) - '0'];

        for (int i = 0; i < letters.length(); i++) {
            // Choice: append letter
            current.append(letters.charAt(i));

            // Explore: move to next digit
            backtrack(index + 1, digits, current, result);

            // Undo: backtrack
            current.deleteCharAt(current.length() - 1);
        }
    }

    // Method to generate all letter combinations
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();

        if (digits == null || digits.length() == 0) {
            return result;
        }

        backtrack(0, digits, new StringBuilder(), result);
        return result;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p17 obj = new p17();

        // Test case 1: "23" -> ["ad","ae","af","bd","be","bf","cd","ce","cf"]
        String digits1 = "23";
        System.out.println("Combinations for \"23\": " + obj.letterCombinations(digits1));

        // Test case 2: "" -> []
        String digits2 = "";
        System.out.println("Combinations for \"\": " + obj.letterCombinations(digits2));

        // Test case 3: "2" -> ["a","b","c"]
        String digits3 = "2";
        System.out.println("Combinations for \"2\": " + obj.letterCombinations(digits3));

        // Test case 4: "79" -> 16 combinations (4 * 4)
        String digits4 = "79";
        System.out.println("Combinations count for \"79\": " + obj.letterCombinations(digits4).size());
    }
}
