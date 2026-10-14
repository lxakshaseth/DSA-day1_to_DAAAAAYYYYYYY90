// Defines that this class belongs to the leetcode package
package leetcode;

// Import utility classes
import java.util.Arrays;
import java.util.Stack;

/*
 * LeetCode 739: Daily Temperatures
 * Given an array of integers temperatures represents the daily temperatures, return an array
 * answer such that answer[i] is the number of days you have to wait after the ith day to get
 * a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0.
 *
 * Monotonic Decreasing Stack Approach:
 * - Stack stores indices of days whose warmer temperature has not yet been found.
 * - When encountering a day with higher temperature than the day at stack.peek(),
 *   pop that index and record the difference in days.
 *
 * Time Complexity: O(n) - each element pushed and popped at most once
 * Space Complexity: O(n)
 */
public class p739 {

    // Method to calculate days until warmer temperature
    public int[] dailyTemperatures(int[] temperatures) {

        int n = temperatures.length;
        int[] result = new int[n];

        // Monotonic stack storing indices
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {

            // While current temperature is greater than temperature at top index
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int prevDay = stack.pop();
                result[prevDay] = i - prevDay;
            }

            // Push current day's index onto stack
            stack.push(i);
        }

        return result;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p739 obj = new p739();

        // Sample temperatures
        int[] temperatures = { 73, 74, 75, 71, 69, 72, 76, 73 };

        // Output: [1, 1, 4, 2, 1, 1, 0, 0]
        int[] result = obj.dailyTemperatures(temperatures);
        System.out.println("Days until warmer temperature: " + Arrays.toString(result));
    }
}
