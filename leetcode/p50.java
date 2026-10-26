// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 50: Pow(x, n)
 * Implement pow(x, n), which calculates x raised to the power n (i.e., x^n).
 *
 * Binary Exponentiation (Fast Power) Approach:
 * - Handle negative powers by taking x = 1 / x and n = -n.
 * - Cast n to long to prevent integer overflow with Integer.MIN_VALUE (-2^31).
 * - Repeatedly divide power by 2 while squaring the base:
 *   - If power is odd, multiply result by current base.
 *   - Square base: x = x * x.
 *   - Halve power: n = n / 2.
 *
 * Time Complexity: O(log n)
 * Space Complexity: O(1) constant auxiliary space
 */
public class p50 {

    // Method to compute x^n in O(log n) time
    public double myPow(double x, int n) {

        // Use long to avoid overflow when n = Integer.MIN_VALUE
        long N = n;

        // If exponent is negative, invert base and negate exponent
        if (N < 0) {
            x = 1.0 / x;
            N = -N;
        }

        double result = 1.0;
        double currentProduct = x;

        while (N > 0) {

            // If least significant bit of N is 1 (N is odd)
            if ((N & 1) == 1) {
                result *= currentProduct;
            }

            // Square the base for the next bit
            currentProduct *= currentProduct;

            // Shift exponent right by 1 bit (N /= 2)
            N >>= 1;
        }

        return result;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p50 obj = new p50();

        // Test case 1: 2.0 ^ 10 = 1024.0
        double x1 = 2.0;
        int n1 = 10;
        System.out.println(x1 + "^" + n1 + " = " + obj.myPow(x1, n1));

        // Test case 2: 2.1 ^ 3 = 9.261
        double x2 = 2.1;
        int n2 = 3;
        System.out.println(x2 + "^" + n2 + " = " + obj.myPow(x2, n2));

        // Test case 3: 2.0 ^ -2 = 0.25
        double x3 = 2.0;
        int n3 = -2;
        System.out.println(x3 + "^" + n3 + " = " + obj.myPow(x3, n3));
    }
}
