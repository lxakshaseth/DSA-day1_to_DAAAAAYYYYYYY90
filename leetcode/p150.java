// Defines that this class belongs to the leetcode package
package leetcode;

// Import Stack utility class
import java.util.Stack;

/*
 * LeetCode 150: Evaluate Reverse Polish Notation
 * You are given an array of strings tokens that represents an arithmetic expression in a Reverse Polish Notation.
 * Evaluate the expression and return an integer that represents the value of the expression.
 * Valid operators are '+', '-', '*', and '/'.
 *
 * Stack Approach:
 * - If token is an operator, pop top two elements, apply operator, and push result back.
 * - If token is a number, push it onto the stack.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class p150 {

    // Method to evaluate Reverse Polish Notation
    public int evalRPN(String[] tokens) {

        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {

            if (token.equals("+")) {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a + b);
            } else if (token.equals("-")) {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a - b);
            } else if (token.equals("*")) {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a * b);
            } else if (token.equals("/")) {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a / b);
            } else {
                // Token is an integer
                stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p150 obj = new p150();

        // Sample expression: ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
        String[] tokens = { "2", "1", "+", "3", "*" };

        System.out.println("Result: " + obj.evalRPN(tokens));
    }
}
