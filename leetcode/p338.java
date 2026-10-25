// Defines that this class belongs to the leetcode package
package leetcode;

// Import utility classes
import java.util.Arrays;

/*
 * LeetCode 338: Counting Bits
 * Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n),
 * ans[i] is the number of 1's in the binary representation of i.
 *
 * Dynamic Programming with Bit Manipulation Approach:
 * - Notice that for any integer i:
 *   i >> 1 (which is i / 2) shifts all bits to the right by 1, dropping the least significant bit.
 *   The count of 1s in i is therefore the count of 1s in (i >> 1) plus 1 if the dropped bit was 1.
 * - Recurrence:
 *   ans[i] = ans[i >> 1] + (i & 1)
 *
 * Time Complexity: O(n) single pass from 0 to n
 * Space Complexity: O(1) auxiliary space (excluding returned array)
 */
public class p338 {

    // Method to count set bits for all numbers from 0 to n
    public int[] countBits(int n) {

        int[] ans = new int[n + 1];

        // Base case: ans[0] = 0 (default in Java int array)

        // Compute set bit counts using subproblem (i >> 1)
        for (int i = 1; i <= n; i++) {
            ans[i] = ans[i >> 1] + (i & 1);
        }

        return ans;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p338 obj = new p338();

        // Test case 1: n = 2 -> [0, 1, 1]
        int n1 = 2;
        System.out.println("Counting bits for " + n1 + ": " + Arrays.toString(obj.countBits(n1)));

        // Test case 2: n = 5 -> [0, 1, 1, 2, 1, 2]
        int n2 = 5;
        System.out.println("Counting bits for " + n2 + ": " + Arrays.toString(obj.countBits(n2)));
    }
}
