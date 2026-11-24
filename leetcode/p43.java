// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 43: Multiply Strings
 *
 * Given two non-negative integers num1 and num2 represented as strings,
 * return the product of num1 and num2, also represented as a string.
 * BigInteger library or direct string-to-integer conversion is not permitted.
 *
 * Optimal Approach: Positional Elementary Math Multiplication
 * - The product of an m-digit integer and an n-digit integer has at most m + n digits.
 * - Allocate an integer array pos of length m + n.
 * - Multiply each pair of digits num1[i] and num2[j] from right to left:
 *   - The product num1[i] * num2[j] contributes to positions:
 *       p1 = i + j (carry)
 *       p2 = i + j + 1 (current digit)
 *   - Add current product to pos[p2]:
 *       sum = (num1[i] - '0') * (num2[j] - '0') + pos[p2]
 *       pos[p2] = sum % 10
 *       pos[p1] += sum / 10
 * - Traverse pos to construct the final string, skipping leading zeros.
 * - If the result is empty (e.g. multiplying by 0), return "0".
 *
 * Time Complexity: O(m * n) where m = num1.length(), n = num2.length().
 * Space Complexity: O(m + n) auxiliary space for the product digits array.
 */
public class p43 {

    // Method to multiply two large numbers represented as strings
    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null || "0".equals(num1) || "0".equals(num2)) {
            return "0";
        }

        int m = num1.length();
        int n = num2.length();
        int[] pos = new int[m + n];

        // Multiply each digit right-to-left
        for (int i = m - 1; i >= 0; i--) {
            int d1 = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int d2 = num2.charAt(j) - '0';
                int mul = d1 * d2;

                int p1 = i + j;
                int p2 = i + j + 1;
                int sum = mul + pos[p2];

                pos[p2] = sum % 10;
                pos[p1] += sum / 10;
            }
        }

        // Build string, omitting leading zeros
        StringBuilder sb = new StringBuilder();
        for (int p : pos) {
            if (!(sb.length() == 0 && p == 0)) {
                sb.append(p);
            }
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }

    public static void main(String[] args) {
        p43 solver = new p43();

        // Test Case 1: Single digits
        System.out.println("Test Case 1 (\"2\" * \"3\"): " +
                solver.multiply("2", "3")); // Expected: "6"

        // Test Case 2: Multi-digit multiplication
        System.out.println("Test Case 2 (\"123\" * \"456\"): " +
                solver.multiply("123", "456")); // Expected: "56088"

        // Test Case 3: Multiplying by zero
        System.out.println("Test Case 3 (\"0\" * \"52\"): " +
                solver.multiply("0", "52")); // Expected: "0"

        // Test Case 4: Large 9s causing cascading carries
        System.out.println("Test Case 4 (\"99\" * \"99\"): " +
                solver.multiply("99", "99")); // Expected: "9801"
    }
}
