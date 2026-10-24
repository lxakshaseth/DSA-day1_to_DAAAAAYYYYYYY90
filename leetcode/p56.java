// Defines that this class belongs to the leetcode package
package leetcode;

// Import utility classes
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * LeetCode 56: Merge Intervals
 * Given an array of intervals where intervals[i] = [start, end], merge all overlapping intervals,
 * and return an array of the non-overlapping intervals that cover all the intervals in the input.
 *
 * Sorting + Greedy Merging Approach:
 * - Sort intervals by their start time: intervals[i][0].
 * - Maintain a list of merged intervals.
 * - For each interval:
 *   1. If merged list is empty or current interval starts after the last merged interval ends,
 *      add current interval directly.
 *   2. Otherwise, overlapping detected: merge by updating the last merged interval's end
 *      to max(last.end, current.end).
 *
 * Time Complexity: O(n log n) due to sorting intervals
 * Space Complexity: O(n) for storing the merged intervals output
 */
public class p56 {

    // Method to merge all overlapping intervals
    public int[][] merge(int[][] intervals) {

        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }

        // Sort intervals by their starting times
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();

        for (int[] interval : intervals) {

            // If merged is empty or no overlap with the last merged interval
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]) {
                merged.add(interval);
            } else {
                // Overlap exists: update the end time of previous interval
                int[] last = merged.get(merged.size() - 1);
                last[1] = Math.max(last[1], interval[1]);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }

    // Helper method to print 2D intervals array
    public static void printIntervals(int[][] intervals) {
        System.out.print("[");
        for (int i = 0; i < intervals.length; i++) {
            System.out.print(Arrays.toString(intervals[i]) + (i < intervals.length - 1 ? ", " : ""));
        }
        System.out.println("]");
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p56 obj = new p56();

        // Test case 1: [[1,3],[2,6],[8,10],[15,18]] -> [[1,6],[8,10],[15,18]]
        int[][] intervals1 = { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } };
        System.out.print("Merged intervals 1: ");
        printIntervals(obj.merge(intervals1));

        // Test case 2: [[1,4],[4,5]] -> [[1,5]]
        int[][] intervals2 = { { 1, 4 }, { 4, 5 } };
        System.out.print("Merged intervals 2: ");
        printIntervals(obj.merge(intervals2));
    }
}
