// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 121: Best Time to Buy and Sell Stock
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * You want to maximize your profit by choosing a single day to buy one stock and choosing
 * a different day in the future to sell that stock. Return the maximum profit.
 *
 * One-Pass Greedy / Sliding Window Approach:
 * - Keep track of minimum buying price observed so far.
 * - Calculate profit if sold today: prices[i] - minPrice.
 * - Maintain maximum profit found.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class p121 {

    // Method to calculate maximum profit
    public int maxProfit(int[] prices) {

        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int i = 0; i < prices.length; i++) {

            // Update minimum price if current price is lower
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else {
                // Calculate profit if sold on current day
                int currentProfit = prices[i] - minPrice;
                maxProfit = Math.max(maxProfit, currentProfit);
            }
        }

        return maxProfit;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p121 obj = new p121();

        // Sample prices: buy at 1, sell at 6 -> profit = 5
        int[] prices = { 7, 1, 5, 3, 6, 4 };

        System.out.println("Maximum Profit: " + obj.maxProfit(prices));
    }
}
