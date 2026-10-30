// Defines that this class belongs to the leetcode package
package leetcode;

// Import utility classes
import java.util.Arrays;

/*
 * LeetCode 435: Non-overlapping Intervals
 * Given an array of intervals where intervals[i] = [start, end], return the minimum number
 * of intervals you need to remove to make the rest of the intervals non-overlapping.
 *
 * Greedy Interval Scheduling Approach:
 * - Sort intervals by their end times in ascending order.
 * - Always greedily keep the interval that finishes earliest (smallest end time),
 *   leaving the maximum possible room for subsequent intervals.
 * - If current interval's start < previous interval's end, an overlap occurs -> increment removal count.
 * - Otherwise, update previous end time to current interval's end.
 *
 * Time Complexity: O(n log n) due to sorting intervals
 * Space Complexity: O(1) auxiliary space (or O(log n) for sorting recursion stack)
 */
public class p435 {

    // Method to find minimum number of interval removals to eliminate overlaps
    public int eraseOverlapIntervals(int[][] intervals) {

        if (intervals == null || intervals.length <= 1) {
            return 0;
        }

        // Sort intervals by end time in ascending order
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));

        int count = 0;
        int prevEnd = intervals[0][1];

        // Traverse remaining intervals
        for (int i = 1; i < intervals.length; i++) {

            // If current interval starts before previous interval ends -> Overlap!
            if (intervals[i][0] < prevEnd) {
                count++; // Remove current interval
            } else {
                // Non-overlapping interval: update end time
                prevEnd = intervals[i][1];
            }
        }

        return count;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p435 obj = new p435();

        // Test case 1: [[1,2],[2,3],[3,4],[1,3]] -> Remove [1,3] -> 1 removal
        int[][] intervals1 = { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 1, 3 } };
        System.out.println("Min removals 1: " + obj.eraseOverlapIntervals(intervals1)); // 1

        // Test case 2: [[1,2],[1,2],[1,2]] -> Remove 2 intervals -> 2 removals
        int[][] intervals2 = { { 1, 2 }, { 1, 2 }, { 1, 2 } };
        System.out.println("Min removals 2: " + obj.eraseOverlapIntervals(intervals2)); // 2

        // Test case 3: [[1,2],[2,3]] -> 0 removals
        int[][] intervals3 = { { 1, 2 }, { 2, 3 } };
        System.out.println("Min removals 3: " + obj.eraseOverlapIntervals(intervals3)); // 0
    }
}
