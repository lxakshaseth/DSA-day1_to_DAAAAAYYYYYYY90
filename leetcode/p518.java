// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 518: Coin Change II
 *
 * Given an integer array coins representing distinct denominations and an integer amount,
 * return the number of combinations that make up that amount (unbounded coin usage).
 *
 * Optimal Approach: Unbounded Knapsack Dynamic Programming (1-D Space Optimization)
 * - Notice the difference between combinations and permutations:
 *   - Combinations: order does NOT matter ([1, 2] is the same as [2, 1]).
 *   - Permutations: order matters ([1, 2] and [2, 1] are distinct).
 * - To count combinations, iterate over coins in the OUTER loop:
 *   - This forces coins to be picked in a fixed canonical order, preventing duplicate permutations.
 * - In the INNER loop, iterate forward from coin to amount:
 *     dp[a] += dp[a - coin]
 *   - Forward iteration enables unbounded selection of the current coin.
 * - Base Case: dp[0] = 1 (1 way to make amount 0: using zero coins).
 *
 * Time Complexity: O(n * amount) where n = coins.length <= 300 and amount <= 5000.
 * Space Complexity: O(amount) auxiliary space for the 1-D DP array.
 */
public class p518 {

    // Method to calculate the number of combinations making up amount
    public int change(int amount, int[] coins) {
        if (amount < 0 || coins == null) {
            return 0;
        }

        int[] dp = new int[amount + 1];
        dp[0] = 1;

        // Outer loop over coins ensures combination uniqueness
        for (int coin : coins) {
            // Forward inner loop allows unbounded coin reuse
            for (int a = coin; a <= amount; a++) {
                dp[a] += dp[a - coin];
            }
        }

        return dp[amount];
    }

    public static void main(String[] args) {
        p518 solver = new p518();

        // Test Case 1: Standard example
        int[] coins1 = {1, 2, 5};
        System.out.println("Test Case 1 Combinations (amount = 5): " +
                solver.change(5, coins1)); // Expected: 4

        // Test Case 2: Cannot form amount
        int[] coins2 = {2};
        System.out.println("Test Case 2 Combinations (amount = 3): " +
                solver.change(3, coins2)); // Expected: 0

        // Test Case 3: Amount = 0
        int[] coins3 = {7};
        System.out.println("Test Case 3 Combinations (amount = 0): " +
                solver.change(0, coins3)); // Expected: 1

        // Test Case 4: Single denomination exact multiple
        int[] coins4 = {10};
        System.out.println("Test Case 4 Combinations (amount = 10): " +
                solver.change(10, coins4)); // Expected: 1
    }
}
