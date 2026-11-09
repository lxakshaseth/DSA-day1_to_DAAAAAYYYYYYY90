// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 42: Trapping Rain Water
 * 
 * Given n non-negative integers representing an elevation map where the width
 * of each bar is 1, compute how much water it can trap after raining.
 *
 * Optimal Approach: Two Pointers
 * - Maintain two pointers: left at 0 and right at n - 1.
 * - Track the maximum height seen so far from left (leftMax) and right (rightMax).
 * - At each step, compare height[left] and height[right]:
 *   - If height[left] <= height[right]:
 *       - If height[left] >= leftMax, update leftMax.
 *       - Otherwise, the water trapped above the current bar is leftMax - height[left].
 *       - Increment left.
 *   - Else:
 *       - If height[right] >= rightMax, update rightMax.
 *       - Otherwise, the water trapped above the current bar is rightMax - height[right].
 *       - Decrement right.
 * - Since the current bar's trapped water is determined by min(max_left, max_right) - height[i],
 *   processing the smaller side guarantees the opposite side has a boundary at least as tall.
 *
 * Time Complexity: O(n) - Single pass through the array.
 * Space Complexity: O(1) - Only constant extra variables used.
 */
public class p42 {

    // Method to calculate the total trapped rain water
    public int trap(int[] height) {
        if (height == null || height.length <= 2) {
            return 0;
        }

        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int totalWater = 0;

        while (left < right) {
            if (height[left] <= height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    totalWater += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    totalWater += rightMax - height[right];
                }
                right--;
            }
        }

        return totalWater;
    }

    public static void main(String[] args) {
        p42 solver = new p42();

        // Test Case 1: Standard elevation map
        int[] height1 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int result1 = solver.trap(height1);
        System.out.println("Test Case 1 Trapped Water: " + result1); // Expected: 6

        // Test Case 2: Elevation map with tall boundaries
        int[] height2 = {4, 2, 0, 3, 2, 5};
        int result2 = solver.trap(height2);
        System.out.println("Test Case 2 Trapped Water: " + result2); // Expected: 9

        // Test Case 3: Strictly increasing or decreasing (no water can be trapped)
        int[] height3 = {1, 2, 3, 4, 5};
        int result3 = solver.trap(height3);
        System.out.println("Test Case 3 Trapped Water: " + result3); // Expected: 0

        // Test Case 4: Flat elevation map
        int[] height4 = {3, 3, 3, 3};
        int result4 = solver.trap(height4);
        System.out.println("Test Case 4 Trapped Water: " + result4); // Expected: 0
    }
}
