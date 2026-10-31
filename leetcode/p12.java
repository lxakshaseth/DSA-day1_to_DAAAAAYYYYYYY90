// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 12: Integer to Roman
 * Seven different symbols represent Roman numerals with the following values:
 * Symbol   Value
 * I        1
 * V        5
 * X        10
 * L        50
 * C        100
 * D        500
 * M        1000
 *
 * Subtractive instances include:
 * - 4 is represented as IV, 9 as IX
 * - 40 is represented as XL, 90 as XC
 * - 400 is represented as CD, 900 as CM
 *
 * Greedy Value-to-Symbol Mapping Approach:
 * - Maintain descending arrays of standard decimal values and their Roman symbol representations.
 * - For each value, greedily append its symbol to a StringBuilder as many times as it fits into num,
 *   subtracting the value from num each time.
 * - Continue until num is reduced to 0.
 *
 * Time Complexity: O(1) (since the maximum input value is 3999, the number of operations is strictly bounded)
 * Space Complexity: O(1) auxiliary space
 */
public class p12 {

    // Method to convert an integer to a Roman numeral string
    public String intToRoman(int num) {

        // Values mapped in descending order including subtractive combinations
        final int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        final String[] symbols = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

        StringBuilder roman = new StringBuilder();

        // Greedily match the largest possible Roman numeral values
        for (int i = 0; i < values.length && num > 0; i++) {
            while (num >= values[i]) {
                num -= values[i];
                roman.append(symbols[i]);
            }
        }

        return roman.toString();
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p12 obj = new p12();

        // Test case 1: 3749 -> "MMMDCCXLIX"
        int num1 = 3749;
        System.out.println("Integer: " + num1 + " -> Roman: " + obj.intToRoman(num1));

        // Test case 2: 58 -> "LVIII"
        int num2 = 58;
        System.out.println("Integer: " + num2 + " -> Roman: " + obj.intToRoman(num2));

        // Test case 3: 1994 -> "MCMXCIV"
        int num3 = 1994;
        System.out.println("Integer: " + num3 + " -> Roman: " + obj.intToRoman(num3));

        // Test case 4: 4 -> "IV"
        int num4 = 4;
        System.out.println("Integer: " + num4 + " -> Roman: " + obj.intToRoman(num4));

        // Test case 5: 9 -> "IX"
        int num5 = 9;
        System.out.println("Integer: " + num5 + " -> Roman: " + obj.intToRoman(num5));
    }
}
