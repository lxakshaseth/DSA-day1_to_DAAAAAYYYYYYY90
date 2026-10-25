// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 191: Number of 1 Bits
 * Given a positive integer n, write a function that returns the number of set bits
 * it has (also known as the Hamming weight).
 *
 * Brian Kernighan's Algorithm Approach:
 * - The expression n & (n - 1) always clears the lowest (rightmost) set bit (1) in n.
 * - By repeatedly applying n = n & (n - 1), the loop runs strictly as many times
 *   as there are 1-bits in n.
 *
 * Time Complexity: O(k) where k is the number of set bits (at most 32 operations) -> O(1)
 * Space Complexity: O(1) constant space
 */
public class p191 {

    // Method to count the number of 1 bits using Brian Kernighan's algorithm
    public int hammingWeight(int n) {

        int count = 0;

        while (n != 0) {
            // Clears the lowest set bit
            n = n & (n - 1);
            count++;
        }

        return count;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p191 obj = new p191();

        // Test case 1: 11 (binary: 0000...1011) -> 3 set bits
        int n1 = 11;
        System.out.println("Hamming weight of " + n1 + ": " + obj.hammingWeight(n1)); // 3

        // Test case 2: 128 (binary: 0000...10000000) -> 1 set bit
        int n2 = 128;
        System.out.println("Hamming weight of " + n2 + ": " + obj.hammingWeight(n2)); // 1

        // Test case 3: 2147483645 -> 30 set bits
        int n3 = 2147483645;
        System.out.println("Hamming weight of " + n3 + ": " + obj.hammingWeight(n3)); // 30
    }
}
