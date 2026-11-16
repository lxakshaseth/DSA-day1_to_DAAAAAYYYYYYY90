// Defines that this class belongs to the leetcode package
package leetcode;

import java.util.Arrays;

/*
 * LeetCode 853: Car Fleet
 *
 * There are n cars positioned at position[i] with speed speed[i] driving towards target.
 * Faster cars catch up to slower cars ahead but cannot pass them, merging into a fleet
 * with the leading car's arrival pace. Return the number of fleets that arrive at target.
 *
 * Optimal Approach: Position Sorting + Monotonic Arrival-Time Tracking
 * - Pair each car's initial position with its speed and sort in descending order of position
 *   (from closest to target to farthest).
 * - For each car, calculate its time to target:
 *     time = (double) (target - position) / speed
 * - As we iterate from closest to farthest:
 *   - If the current car's arrival time is strictly greater than maxTime of the fleet ahead,
 *     it can never catch up to that fleet before reaching target. It therefore initiates
 *     a new fleet: increment fleet count and update maxTime.
 *   - If current time <= maxTime, this car catches up to the preceding fleet and merges.
 *
 * Time Complexity: O(n log n) due to sorting cars by initial position.
 * Space Complexity: O(n) to store paired position-speed representations.
 */
public class p853 {

    // Method to calculate the number of car fleets that arrive at target
    public int carFleet(int target, int[] position, int[] speed) {
        if (position == null || position.length == 0) {
            return 0;
        }

        int n = position.length;
        // Store [position, speed] pairs
        int[][] cars = new int[n][2];
        for (int i = 0; i < n; i++) {
            cars[i][0] = position[i];
            cars[i][1] = speed[i];
        }

        // Sort cars descending by position (closest to target first)
        Arrays.sort(cars, (a, b) -> Integer.compare(b[0], a[0]));

        int fleets = 0;
        double maxTime = 0.0;

        for (int i = 0; i < n; i++) {
            double time = (double) (target - cars[i][0]) / cars[i][1];

            // If this car arrives later than the fleet ahead, it begins a new fleet
            if (time > maxTime) {
                fleets++;
                maxTime = time;
            }
        }

        return fleets;
    }

    public static void main(String[] args) {
        p853 solver = new p853();

        // Test Case 1: Standard 5-car scenario
        int target1 = 12;
        int[] pos1 = {10, 8, 0, 5, 3};
        int[] spd1 = {2, 4, 1, 1, 3};
        System.out.println("Test Case 1 Fleets: " + solver.carFleet(target1, pos1, spd1)); // Expected: 3

        // Test Case 2: Single car
        int target2 = 10;
        int[] pos2 = {3};
        int[] spd2 = {3};
        System.out.println("Test Case 2 Fleets: " + solver.carFleet(target2, pos2, spd2)); // Expected: 1

        // Test Case 3: All cars merge into a single leading fleet
        int target3 = 100;
        int[] pos3 = {0, 2, 4};
        int[] spd3 = {4, 2, 1};
        System.out.println("Test Case 3 Fleets: " + solver.carFleet(target3, pos3, spd3)); // Expected: 1

        // Test Case 4: Multiple independent cars that never catch up
        int target4 = 10;
        int[] pos4 = {6, 8};
        int[] spd4 = {3, 2};
        System.out.println("Test Case 4 Fleets: " + solver.carFleet(target4, pos4, spd4)); // Expected: 2
    }
}
