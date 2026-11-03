// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 69: Sqrt(x)
 * Given a non-negative integer x, return the square root of x rounded down
 * to the nearest integer. The returned integer should be non-negative as well.
 * You must not use any built-in exponent function or operator.
 *
 * Binary Search Approach:
 * - The square root of x for x >= 1 lies in the range [1, x / 2] (except for x = 1 where sqrt is 1).
 * - Perform binary search in the search space [1, x / 2]:
 *   1. Calculate midpoint mid = left + (right - left) / 2.
 *   2. Compare mid with x / mid to prevent 32-bit integer overflow.
 *   3. If mid <= x / mid, mid is a candidate; record mid and search right (left = mid + 1).
 *   4. Otherwise, mid * mid > x; search left (right = mid - 1).
 * - Return the recorded answer.
 *
 * Time Complexity: O(log x)
 * Space Complexity: O(1) constant extra space
 */
public class p69 {

    // Method to compute integer square root using binary search
    public int mySqrt(int x) {

        // Base cases
        if (x < 2) {
            return x;
        }

        int left = 1;
        int right = x / 2; // Sqrt(x) is <= x / 2 for all x >= 4
        int ans = 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Use division (mid <= x / mid) to prevent 32-bit integer overflow
            if (mid <= x / mid) {
                ans = mid;        // Found valid candidate
                left = mid + 1;   // Try to find a larger integer
            } else {
                right = mid - 1;  // mid is too large, search lower half
            }
        }

        return ans;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p69 obj = new p69();

        // Test case 1: x = 4 -> 2
        int x1 = 4;
        System.out.println("Sqrt(" + x1 + "): " + obj.mySqrt(x1));

        // Test case 2: x = 8 -> 2 (2.828... rounded down to 2)
        int x2 = 8;
        System.out.println("Sqrt(" + x2 + "): " + obj.mySqrt(x2));

        // Test case 3: x = 0 -> 0
        int x3 = 0;
        System.out.println("Sqrt(" + x3 + "): " + obj.mySqrt(x3));

        // Test case 4: x = 1 -> 1
        int x4 = 1;
        System.out.println("Sqrt(" + x4 + "): " + obj.mySqrt(x4));

        // Test case 5: x = 2147395599 (near 2^31 - 1) -> 46339
        int x5 = 2147395599;
        System.out.println("Sqrt(" + x5 + "): " + obj.mySqrt(x5));
    }
}
