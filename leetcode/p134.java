// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 134: Gas Station
 * There are n gas stations along a circular route, where the amount of gas at the ith station
 * is gas[i]. It costs cost[i] of gas to travel from station i to station (i + 1).
 * Return the starting gas station index if you can travel around the circuit once clockwise;
 * otherwise return -1.
 *
 * Greedy Single-Pass Approach:
 * - If total gas available across all stations is less than total cost, completing the circuit
 *   is strictly impossible (totalTank < 0 -> return -1).
 * - Maintain currTank starting from startIndex = 0.
 * - If currTank drops below 0 at station i, no station between startIndex and i can serve
 *   as a valid starting point. Reset currTank to 0 and set startIndex = i + 1.
 *
 * Time Complexity: O(n) single linear pass
 * Space Complexity: O(1) constant auxiliary space
 */
public class p134 {

    // Method to find the unique starting gas station index
    public int canCompleteCircuit(int[] gas, int[] cost) {

        int totalTank = 0;
        int currTank = 0;
        int startIndex = 0;

        for (int i = 0; i < gas.length; i++) {

            int netGain = gas[i] - cost[i];
            totalTank += netGain;
            currTank += netGain;

            // If we cannot reach station i + 1 from current start
            if (currTank < 0) {
                startIndex = i + 1; // Start fresh from next station
                currTank = 0;       // Reset current tank
            }
        }

        // Circuit can be completed only if total gas >= total cost
        return totalTank >= 0 ? startIndex : -1;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p134 obj = new p134();

        // Test case 1: gas = [1,2,3,4,5], cost = [3,4,5,1,2] -> start at index 3 (station 4)
        int[] gas1 = { 1, 2, 3, 4, 5 };
        int[] cost1 = { 3, 4, 5, 1, 2 };
        System.out.println("Starting station 1: " + obj.canCompleteCircuit(gas1, cost1)); // 3

        // Test case 2: gas = [2,3,4], cost = [3,4,3] -> impossible -> -1
        int[] gas2 = { 2, 3, 4 };
        int[] cost2 = { 3, 4, 3 };
        System.out.println("Starting station 2: " + obj.canCompleteCircuit(gas2, cost2)); // -1
    }
}
