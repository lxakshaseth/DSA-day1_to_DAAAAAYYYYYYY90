// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 202: Happy Number
 * A happy number is defined by replacing a number by the sum of the squares of its digits,
 * repeating until the number equals 1, or looping endlessly in a cycle that does not include 1.
 * Return true if n is a happy number, and false otherwise.
 *
 * Floyd's Cycle Detection (Tortoise and Hare) Approach:
 * - The sequence of sum-of-squares either reaches 1 or falls into a repeating cycle.
 * - By moving slow pointer 1 step and fast pointer 2 steps:
 *   If there is a cycle, slow and fast will eventually meet.
 *   If the sequence terminates at 1, fast will reach 1 first.
 *
 * Time Complexity: O(log n)
 * Space Complexity: O(1) constant auxiliary space
 */
public class p202 {

    // Helper method to compute sum of squares of digits
    private int getNext(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }

    // Method to determine if n is a happy number
    public boolean isHappy(int n) {

        int slow = n;
        int fast = getNext(n);

        // Continue until fast reaches 1 (happy) or fast meets slow (cycle detected)
        while (fast != 1 && slow != fast) {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        }

        return fast == 1;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p202 obj = new p202();

        // Test case 1: 19 is happy:
        // 1^2 + 9^2 = 82
        // 8^2 + 2^2 = 68
        // 6^2 + 8^2 = 100
        // 1^2 + 0^2 + 0^2 = 1 -> true
        int n1 = 19;
        System.out.println(n1 + " is happy: " + obj.isHappy(n1)); // true

        // Test case 2: 2 is not happy (falls into 4 -> 16 -> 37 -> ... cycle)
        int n2 = 2;
        System.out.println(n2 + " is happy: " + obj.isHappy(n2)); // false
    }
}
