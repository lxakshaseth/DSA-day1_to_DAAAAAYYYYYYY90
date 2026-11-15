// Defines that this class belongs to the leetcode package
package leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/*
 * LeetCode 239: Sliding Window Maximum
 *
 * You are given an array of integers nums, and a sliding window of size k moving
 * from left to right. Return an array containing the maximum value in each window.
 *
 * Optimal Approach: Monotonic Decreasing Deque
 * - Maintain a double-ended queue (Deque) storing indices in strictly decreasing order of their values.
 * - The front of the deque (deque.peekFirst()) always holds the index of the maximum element
 *   within the current k-sized window.
 * - In each step as index i moves from 0 to n - 1:
 *   1. Evict expired elements: Remove indices from the front that fall outside the window (index <= i - k).
 *   2. Maintain monotonicity: Pop indices from the back whose corresponding values are <= nums[i],
 *      as they can never be the maximum in any subsequent window containing nums[i].
 *   3. Enqueue current index i at the back.
 *   4. Record answer: Starting from index i >= k - 1, append nums[deque.peekFirst()] to the result array.
 *
 * Time Complexity: O(n) - Each element's index is pushed and popped from the deque at most once.
 * Space Complexity: O(k) - Auxiliary deque stores at most k indices at any time.
 */
public class p239 {

    // Method to find maximum element in every sliding window of size k
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }

        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            // 1. Remove indices that are out of the current sliding window
            if (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }

            // 2. Remove indices with smaller or equal values from the back
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }

            // 3. Add current index to the back
            deque.offerLast(i);

            // 4. Record the maximum for the window once the first window of size k is complete
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        p239 solver = new p239();

        // Test Case 1: Standard example with alternating values
        int[] nums1 = {1, 3, -1, -3, 5, 3, 6, 7};
        System.out.println("Test Case 1 Max Sliding Window: " +
                Arrays.toString(solver.maxSlidingWindow(nums1, 3)));
        // Expected: [3, 3, 5, 5, 6, 7]

        // Test Case 2: Single element, k = 1
        int[] nums2 = {1};
        System.out.println("Test Case 2 Max Sliding Window: " +
                Arrays.toString(solver.maxSlidingWindow(nums2, 1)));
        // Expected: [1]

        // Test Case 3: Strictly decreasing array
        int[] nums3 = {9, 8, 7, 6, 5, 4};
        System.out.println("Test Case 3 Max Sliding Window: " +
                Arrays.toString(solver.maxSlidingWindow(nums3, 3)));
        // Expected: [9, 8, 7, 6]

        // Test Case 4: Negative numbers and duplicate maximums
        int[] nums4 = {-7, -8, 7, 5, 7, 1, 6, 0};
        System.out.println("Test Case 4 Max Sliding Window: " +
                Arrays.toString(solver.maxSlidingWindow(nums4, 4)));
        // Expected: [7, 7, 7, 7, 7]
    }
}
