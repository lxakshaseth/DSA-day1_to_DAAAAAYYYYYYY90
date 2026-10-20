// Defines that this class belongs to the leetcode package
package leetcode;

// Import utility classes
import java.util.Arrays;

/*
 * LeetCode 322: Coin Change
 * You are given an integer array coins representing coins of different denominations
 * and an integer amount representing a total amount of money.
 * Return the fewest number of coins needed to make up that amount.
 * If that amount cannot be made up by any combination of coins, return -1.
 * You may assume an infinite supply of each coin.
 *
 * Bottom-Up Dynamic Programming Approach:
 * - dp[i] represents the minimum coins needed to make up amount i.
 * - Base case: dp[0] = 0 (0 coins needed for amount 0).
 * - Transition: dp[i] = min(dp[i], 1 + dp[i - coin]) for each coin <= i.
 *
 * Time Complexity: O(amount * n) where n is the number of coin denominations
 * Space Complexity: O(amount) for the DP table
 */
public class p322 {

    // Method to find fewest coins needed to make amount
    public int coinChange(int[] coins, int amount) {

        // DP array to store min coins for each amount from 0 to amount
        int[] dp = new int[amount + 1];

        // Fill with sentinel value greater than any possible answer
        Arrays.fill(dp, amount + 1);

        // Base case: 0 coins needed for amount 0
        dp[0] = 0;

        // Compute minimum coins for every amount from 1 to target amount
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                }
            }
        }

        // If dp[amount] remains untouched, amount cannot be formed
        return dp[amount] > amount ? -1 : dp[amount];
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p322 obj = new p322();

        // Test case 1
        int[] coins1 = { 1, 2, 5 };
        int amount1 = 11;
        System.out.println("Min coins for " + amount1 + ": " + obj.coinChange(coins1, amount1)); // Output: 3 (5 + 5 + 1)

        // Test case 2
        int[] coins2 = { 2 };
        int amount2 = 3;
        System.out.println("Min coins for " + amount2 + ": " + obj.coinChange(coins2, amount2)); // Output: -1
    }
}
