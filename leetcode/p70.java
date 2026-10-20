// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 70: Climbing Stairs
 * You are climbing a staircase. It takes n steps to reach the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 *
 * Dynamic Programming (Fibonacci) Approach:
 * - To reach step i, you can come from step (i - 1) with 1 step, or from (i - 2) with 2 steps.
 * - Recurrence relation: dp[i] = dp[i - 1] + dp[i - 2].
 * - Space optimized to O(1) by maintaining only the previous two steps.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class p70 {

    // Method to calculate distinct ways to climb n stairs
    public int climbStairs(int n) {

        // Base cases
        if (n <= 2) {
            return n;
        }

        // Two variables to track previous two steps
        int prev2 = 1; // 1 way to reach step 1
        int prev1 = 2; // 2 ways to reach step 2

        for (int i = 3; i <= n; i++) {
            int current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p70 obj = new p70();

        // Test cases
        int n1 = 2;
        System.out.println("Ways to climb " + n1 + " stairs: " + obj.climbStairs(n1)); // 2

        int n2 = 3;
        System.out.println("Ways to climb " + n2 + " stairs: " + obj.climbStairs(n2)); // 3

        int n3 = 5;
        System.out.println("Ways to climb " + n3 + " stairs: " + obj.climbStairs(n3)); // 8
    }
}
