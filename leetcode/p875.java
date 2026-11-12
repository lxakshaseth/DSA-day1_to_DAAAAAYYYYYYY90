// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 875: Koko Eating Bananas
 *
 * Koko has n piles of bananas, where piles[i] is the number of bananas in the i-th pile.
 * She can eat up to k bananas per hour from a single pile. Return the minimum integer speed k
 * such that she can eat all the bananas within h hours.
 *
 * Optimal Approach: Binary Search on the Answer Space
 * - The eating speed k lies within [1, max(piles)].
 * - Because total eating hours is monotonically non-increasing as k increases, we can binary
 *   search for the smallest feasible k.
 * - For a candidate speed k, hours needed for pile p is ceil(p / k) = (p - 1) / k + 1.
 * - Accumulate total hours in a 64-bit long and short-circuit early if hours exceed h to
 *   prevent integer overflow on large pile counts/sizes.
 *
 * Time Complexity: O(n * log(maxPile)) where n is piles.length
 * Space Complexity: O(1) constant extra space
 */
public class p875 {

    // Method to find the minimum eating speed k to finish all piles within h hours
    public int minEatingSpeed(int[] piles, int h) {
        if (piles == null || piles.length == 0) {
            return 0;
        }

        int maxPile = 0;
        for (int pile : piles) {
            maxPile = Math.max(maxPile, pile);
        }

        int low = 1;
        int high = maxPile;
        int minSpeed = maxPile;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canFinish(piles, h, mid)) {
                minSpeed = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return minSpeed;
    }

    // Helper to check if all piles can be finished within h hours at eating speed k
    private boolean canFinish(int[] piles, int h, int speed) {
        long totalHours = 0;
        for (int pile : piles) {
            totalHours += (pile - 1L) / speed + 1L;
            if (totalHours > h) {
                return false;
            }
        }
        return totalHours <= h;
    }

    public static void main(String[] args) {
        p875 solver = new p875();

        // Test Case 1: Standard example
        int[] piles1 = {3, 6, 7, 11};
        System.out.println("Test Case 1 Min Speed: " + solver.minEatingSpeed(piles1, 8)); // Expected: 4

        // Test Case 2: Exact number of hours as piles (h == piles.length)
        int[] piles2 = {30, 11, 23, 4, 20};
        System.out.println("Test Case 2 Min Speed: " + solver.minEatingSpeed(piles2, 5)); // Expected: 30

        // Test Case 3: One extra hour allows second-largest pile speed
        int[] piles3 = {30, 11, 23, 4, 20};
        System.out.println("Test Case 3 Min Speed: " + solver.minEatingSpeed(piles3, 6)); // Expected: 23

        // Test Case 4: Large pile sizes testing 64-bit long overflow safety
        int[] piles4 = {805306368, 805306368, 805306368};
        System.out.println("Test Case 4 Min Speed: " + solver.minEatingSpeed(piles4, 1000000000)); // Expected: 3
    }
}
