// Defines that this class belongs to the leetcode package
package leetcode;

// Import Stack utility class
import java.util.Stack;

/*
 * LeetCode 155: Min Stack
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * Implement the MinStack class:
 * - push(val) pushes the element val onto the stack.
 * - pop() removes the element on the top of the stack.
 * - top() gets the top element of the stack.
 * - getMin() retrieves the minimum element in the stack.
 *
 * Approach:
 * - Use two stacks:
 *   1. mainStack: stores all elements.
 *   2. minStack: stores the minimum value seen up to the current element.
 *
 * Time Complexity: O(1) for all operations
 * Space Complexity: O(n)
 */
public class p155 {

    static class MinStack {

        private Stack<Integer> stack;
        private Stack<Integer> minStack;

        // Constructor to initialize the stacks
        public MinStack() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }

        // Push operation
        public void push(int val) {
            stack.push(val);

            // If minStack is empty or val is smaller than current min, push val
            if (minStack.isEmpty() || val <= minStack.peek()) {
                minStack.push(val);
            } else {
                // Otherwise duplicate current min
                minStack.push(minStack.peek());
            }
        }

        // Pop operation
        public void pop() {
            stack.pop();
            minStack.pop();
        }

        // Top element
        public int top() {
            return stack.peek();
        }

        // Minimum element in constant time
        public int getMin() {
            return minStack.peek();
        }
    }

    // Main method to test MinStack
    public static void main(String[] args) {

        MinStack minStack = new MinStack();

        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);

        System.out.println("Current min: " + minStack.getMin()); // Returns -3

        minStack.pop();
        System.out.println("Top element: " + minStack.top());    // Returns 0
        System.out.println("Current min: " + minStack.getMin()); // Returns -2
    }
}
