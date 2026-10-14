// Defines that this class belongs to the leetcode package
package leetcode;

// Import Stack utility class
import java.util.Stack;

/*
 * LeetCode 20: Valid Parentheses
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']',
 * determine if the input string is valid.
 *
 * Stack Approach:
 * - For opening brackets, push the corresponding closing bracket onto the stack.
 * - For closing brackets, check if stack is non-empty and matches the popped element.
 * - A valid string will leave the stack empty at the end.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class p20 {

    // Method to determine if brackets are valid
    public boolean isValid(String s) {

        // Stack to track expected closing brackets
        Stack<Character> stack = new Stack<>();

        // Traverse each character in the string
        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                stack.push(')');
            } else if (ch == '{') {
                stack.push('}');
            } else if (ch == '[') {
                stack.push(']');
            } else {
                // If closing bracket encountered but stack is empty or mismatch
                if (stack.isEmpty() || stack.pop() != ch) {
                    return false;
                }
            }
        }

        // Valid only if all opened brackets were closed properly
        return stack.isEmpty();
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p20 obj = new p20();

        // Test cases
        String s1 = "()[]{}";
        System.out.println("\"" + s1 + "\" is valid: " + obj.isValid(s1));

        String s2 = "(]";
        System.out.println("\"" + s2 + "\" is valid: " + obj.isValid(s2));

        String s3 = "([{}])";
        System.out.println("\"" + s3 + "\" is valid: " + obj.isValid(s3));
    }
}
