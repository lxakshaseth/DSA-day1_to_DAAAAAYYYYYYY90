// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 11: Container With Most Water
 * Given an integer array height of length n, find two lines that together with the x-axis
 * form a container, such that the container contains the most water.
 *
 * Two-Pointer Greedy Approach:
 * - Start with maximum width (pointers at extreme left and right).
 * - Area is bounded by the shorter line: min(height[left], height[right]) * (right - left).
 * - Shift the pointer at the shorter line inward to explore potentially taller lines.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class p11 {

    // Method to calculate maximum water area
    public int maxArea(int[] height) {

        int left = 0;
        int right = height.length - 1;
        int maxWater = 0;

        while (left < right) {

            // Width between lines
            int width = right - left;

            // Height is constrained by the shorter line
            int currentHeight = Math.min(height[left], height[right]);

            // Calculate current water area
            int currentWater = width * currentHeight;

            // Update maximum water recorded
            maxWater = Math.max(maxWater, currentWater);

            // Move the pointer at the shorter bar inward
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxWater;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p11 obj = new p11();

        // Sample input heights
        int[] height = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };

        // Output: 49 (between index 1 with height 8 and index 8 with height 7)
        System.out.println("Maximum water container area: " + obj.maxArea(height));
    }
}
