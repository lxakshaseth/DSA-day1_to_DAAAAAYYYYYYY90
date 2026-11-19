// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 309: Best Time to Buy and Sell Stock with Cooldown
 *
 * Given an integer array prices where prices[i] is the stock price on day i,
 * find the maximum profit with as many transactions as desired, subject to the rule
 * that after selling stock you must cool down for one day before buying again.
 *
 * Optimal Approach: State Machine Dynamic Programming (O(1) Space)
 * - Model the problem with three distinct states at the close of day i:
 *   1. held: Currently owning 1 share of stock.
 *      Transition: either kept stock from yesterday, or bought today from rest state.
 *      held[i] = max(held[i - 1], rest[i - 1] - prices[i])
 *   2. sold: Sold the stock today (triggers mandatory cooldown tomorrow).
 *      Transition: must have held stock yesterday and sold today at current price.
 *      sold[i] = held[i - 1] + prices[i]
 *   3. rest: Not owning stock and free to buy today (cooldown expired or idling).
 *      Transition: either rested yesterday, or was in cooldown from selling yesterday.
 *      rest[i] = max(rest[i - 1], sold[i - 1])
 * - Base Cases on day 0:
 *     held = -prices[0], sold = 0, rest = 0
 * - Terminal Result: max(sold, rest) since finishing with stock held leaves unrealized cost.
 *
 * Time Complexity: O(n) - Single pass over the prices array.
 * Space Complexity: O(1) - Three scalar state variables.
 */
public class p309 {

    // Method to calculate the maximum profit with cooldown
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int held = -prices[0];
        int sold = 0;
        int rest = 0;

        for (int i = 1; i < prices.length; i++) {
            int prevSold = sold;

            sold = held + prices[i];
            held = Math.max(held, rest - prices[i]);
            rest = Math.max(rest, prevSold);
        }

        return Math.max(sold, rest);
    }

    public static void main(String[] args) {
        p309 solver = new p309();

        // Test Case 1: Standard example with cooldown
        int[] prices1 = {1, 2, 3, 0, 2};
        System.out.println("Test Case 1 Max Profit: " +
                solver.maxProfit(prices1)); // Expected: 3

        // Test Case 2: Single day
        int[] prices2 = {1};
        System.out.println("Test Case 2 Max Profit: " +
                solver.maxProfit(prices2)); // Expected: 0

        // Test Case 3: Strictly decreasing prices
        int[] prices3 = {5, 4, 3, 2, 1};
        System.out.println("Test Case 3 Max Profit: " +
                solver.maxProfit(prices3)); // Expected: 0

        // Test Case 4: Strictly increasing prices
        int[] prices4 = {1, 2, 4};
        System.out.println("Test Case 4 Max Profit: " +
                solver.maxProfit(prices4)); // Expected: 3
    }
}
