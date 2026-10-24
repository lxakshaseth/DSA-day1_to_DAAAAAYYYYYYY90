// Defines that this class belongs to the leetcode package
package leetcode;

// Import utility classes
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * LeetCode 57: Insert Interval
 * You are given an array of non-overlapping intervals sorted in ascending order by start time,
 * and a newInterval. Insert newInterval into intervals such that intervals is still sorted
 * and has no overlapping intervals (merge overlapping intervals if necessary).
 *
 * Linear Three-Phase Approach:
 * 1. Add all intervals that come strictly before newInterval (interval.end < newInterval.start).
 * 2. Merge all overlapping intervals with newInterval (interval.start <= newInterval.end).
 * 3. Add all remaining intervals that come strictly after newInterval.
 *
 * Time Complexity: O(n) single pass
 * Space Complexity: O(n) for the output array
 */
public class p57 {

    // Method to insert and merge newInterval
    public int[][] insert(int[][] intervals, int[] newInterval) {

        List<int[]> result = new ArrayList<>();
        int i = 0;
        int n = intervals.length;

        // Phase 1: Add all intervals ending before newInterval starts
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        // Phase 2: Merge overlapping intervals with newInterval
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval); // Insert the merged newInterval

        // Phase 3: Add all remaining intervals that start after newInterval ends
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][]);
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

        p57 obj = new p57();

        // Test case 1: [[1,3],[6,9]], newInterval = [2,5] -> [[1,5],[6,9]]
        int[][] intervals1 = { { 1, 3 }, { 6, 9 } };
        int[] newInterval1 = { 2, 5 };
        System.out.print("Result 1: ");
        printIntervals(obj.insert(intervals1, newInterval1));

        // Test case 2: [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
        // -> [[1,2],[3,10],[12,16]]
        int[][] intervals2 = { { 1, 2 }, { 3, 5 }, { 6, 7 }, { 8, 10 }, { 12, 16 } };
        int[] newInterval2 = { 4, 8 };
        System.out.print("Result 2: ");
        printIntervals(obj.insert(intervals2, newInterval2));
    }
}
