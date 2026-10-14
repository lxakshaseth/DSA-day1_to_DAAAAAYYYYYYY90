// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 125: Valid Palindrome
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters
 * and removing all non-alphanumeric characters, it reads the same forward and backward.
 *
 * Two-Pointer Approach:
 * - Place one pointer at the start (left) and one at the end (right).
 * - Skip non-alphanumeric characters.
 * - Compare characters case-insensitively.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class p125 {

    // Method to check if string is a valid palindrome
    public boolean isPalindrome(String s) {

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {

            // Skip non-alphanumeric characters from left
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }

            // Skip non-alphanumeric characters from right
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            // Compare corresponding alphanumeric characters
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p125 obj = new p125();

        // Test case 1
        String s1 = "A man, a plan, a canal: Panama";
        System.out.println("\"" + s1 + "\" is palindrome: " + obj.isPalindrome(s1));

        // Test case 2
        String s2 = "race a car";
        System.out.println("\"" + s2 + "\" is palindrome: " + obj.isPalindrome(s2));
    }
}
