// Defines that this class belongs to the leetcode package
package leetcode;

// Imports HashSet and Set from Java's utility package
import java.util.HashSet;
import java.util.Set;

/*
 * LeetCode 128: Longest Consecutive Sequence
 * Given an unsorted array of integers nums, return the length of the longest
 * consecutive elements sequence in O(n) runtime.
 *
 * Approach:
 * - Insert all elements into a HashSet.
 * - Only start counting a sequence if (num - 1) is not in the set (i.e. num is the start of a streak).
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class p128 {

    // Method to find length of longest consecutive sequence
    public int longestConsecutive(int[] nums) {

        // Store all elements in a HashSet for O(1) lookup
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int longestStreak = 0;

        // Traverse all elements in the set
        for (int num : set) {

            // Check if num is the start of a sequence (i.e. num - 1 doesn't exist)
            if (!set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                // Expand sequence forward
                while (set.contains(currentNum + 1)) {
                    currentNum++;
                    currentStreak++;
                }

                // Update longest streak found so far
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p128 obj = new p128();

        // Sample input array
        int[] nums = { 100, 4, 200, 1, 3, 2 };

        // Output length of longest consecutive sequence: [1, 2, 3, 4] -> length 4
        System.out.println("Longest consecutive sequence length: " + obj.longestConsecutive(nums));
    }
}
