// Defines that this class belongs to the leetcode package
package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/*
 * LeetCode 84: Largest Rectangle in Histogram
 * 
 * Given an array of integers heights representing the histogram's bar height 
 * where the width of each bar is 1, return the area of the largest rectangle 
 * in the histogram.
 *
 * Optimal Approach: Monotonic Increasing Stack
 * - Maintain a stack of indices representing bars of non-decreasing heights.
 * - Iterate through the array up to index n (where an artificial bar of height 0
 *   is used as a sentinel to flush out all remaining bars in the stack).
 * - For each index i with current height:
 *   - While the current bar is shorter than the bar at stack.peek(), pop the top:
 *     - The popped bar determines the height of the rectangle: h = heights[popped].
 *     - The right boundary is the current index i (the first shorter bar to the right).
 *     - The left boundary is stack.peek() (the first shorter bar to the left),
 *       or 0 if the stack is now empty.
 *     - Width = stack.isEmpty() ? i : (i - stack.peek() - 1).
 *     - Area = h * width.
 *   - Push current index i onto the stack.
 *
 * Time Complexity: O(n) - Each index is pushed and popped at most once.
 * Space Complexity: O(n) - Stack stores at most n indices.
 */
public class p84 {

    // Method to calculate the largest rectangle area in the histogram
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }

        int n = heights.length;
        int maxArea = 0;
        Deque<Integer> stack = new ArrayDeque<>();

        // Iterate through n + 1 positions; index n acts as a sentinel with height 0
        for (int i = 0; i <= n; i++) {
            int currentHeight = (i == n) ? 0 : heights[i];

            while (!stack.isEmpty() && currentHeight < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : (i - stack.peek() - 1);
                maxArea = Math.max(maxArea, height * width);
            }

            stack.push(i);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        p84 solver = new p84();

        // Test Case 1: Standard histogram
        int[] heights1 = {2, 1, 5, 6, 2, 3};
        int result1 = solver.largestRectangleArea(heights1);
        System.out.println("Test Case 1 Largest Rectangle Area: " + result1); // Expected: 10

        // Test Case 2: Two ascending bars
        int[] heights2 = {2, 4};
        int result2 = solver.largestRectangleArea(heights2);
        System.out.println("Test Case 2 Largest Rectangle Area: " + result2); // Expected: 4

        // Test Case 3: Single bar
        int[] heights3 = {7};
        int result3 = solver.largestRectangleArea(heights3);
        System.out.println("Test Case 3 Largest Rectangle Area: " + result3); // Expected: 7

        // Test Case 4: Identical height bars
        int[] heights4 = {2, 2, 2, 2, 2};
        int result4 = solver.largestRectangleArea(heights4);
        System.out.println("Test Case 4 Largest Rectangle Area: " + result4); // Expected: 10

        // Test Case 5: Strictly decreasing bars
        int[] heights5 = {5, 4, 3, 2, 1};
        int result5 = solver.largestRectangleArea(heights5);
        System.out.println("Test Case 5 Largest Rectangle Area: " + result5); // Expected: 9 (height 3 * width 3)
    }
}
