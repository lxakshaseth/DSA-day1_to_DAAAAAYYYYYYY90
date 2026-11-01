// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 58: Length of Last Word
 * Given a string s consisting of words and spaces, return the length of the last word in the string.
 * A word is a maximal substring consisting of non-space characters only.
 *
 * Reverse Traversal Approach:
 * - Traverse backwards from the end of the string to avoid scanning unnecessary earlier words:
 *   1. Skip any trailing spaces by decrementing the index pointer while characters are spaces.
 *   2. Count the characters of the last word until another space is encountered or the beginning is reached.
 * - This avoids allocating extra arrays or substrings via String.split() or trim().
 *
 * Time Complexity: O(n) where n is s.length() (in worst case scans whole string, but terminates as soon as last word ends)
 * Space Complexity: O(1) auxiliary space
 */
public class p58 {

    // Method to calculate the length of the last word
    public int lengthOfLastWord(String s) {

        int i = s.length() - 1;
        int length = 0;

        // Step 1: Skip trailing spaces
        while (i >= 0 && s.charAt(i) == ' ') {
            i--;
        }

        // Step 2: Count characters of the last word
        while (i >= 0 && s.charAt(i) != ' ') {
            length++;
            i--;
        }

        return length;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p58 obj = new p58();

        // Test case 1: "Hello World" -> 5 ("World")
        String s1 = "Hello World";
        System.out.println("Length of last word in \"" + s1 + "\": " + obj.lengthOfLastWord(s1));

        // Test case 2: "   fly me   to   the moon  " -> 4 ("moon")
        String s2 = "   fly me   to   the moon  ";
        System.out.println("Length of last word in \"" + s2 + "\": " + obj.lengthOfLastWord(s2));

        // Test case 3: "luffy is still joyboy" -> 6 ("joyboy")
        String s3 = "luffy is still joyboy";
        System.out.println("Length of last word in \"" + s3 + "\": " + obj.lengthOfLastWord(s3));

        // Test case 4: "a" -> 1 ("a")
        String s4 = "a";
        System.out.println("Length of last word in \"" + s4 + "\": " + obj.lengthOfLastWord(s4));

        // Test case 5: "   day   " -> 3 ("day")
        String s5 = "   day   ";
        System.out.println("Length of last word in \"" + s5 + "\": " + obj.lengthOfLastWord(s5));
    }
}
