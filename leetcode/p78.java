// Defines that this class belongs to the leetcode package
package leetcode;

// Import utility classes
import java.util.ArrayList;
import java.util.List;

/*
 * LeetCode 78: Subsets
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 * Backtracking Approach:
 * - At every step, add the current subset to result.
 * - Iterate from start index to nums.length:
 *   1. Include nums[i] in the current subset.
 *   2. Recurse for the next index (i + 1).
 *   3. Backtrack by removing nums[i].
 *
 * Time Complexity: O(n * 2^n) - 2^n total subsets, each takes O(n) to copy
 * Space Complexity: O(n) recursion call stack depth
 */
public class p78 {

    // Helper backtracking method
    private void backtrack(int start, int[] nums, List<Integer> current, List<List<Integer>> result) {

        // Add copy of current subset to result list
        result.add(new ArrayList<>(current));

        for (int i = start; i < nums.length; i++) {

            // Choice: include element
            current.add(nums[i]);

            // Explore: recurse with next index
            backtrack(i + 1, nums, current, result);

            // Undo: backtrack
            current.remove(current.size() - 1);
        }
    }

    // Method to generate all subsets
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(0, nums, new ArrayList<>(), result);
        return result;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p78 obj = new p78();

        // Sample array
        int[] nums = { 1, 2, 3 };

        // Output: [[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]]
        List<List<Integer>> result = obj.subsets(nums);
        System.out.println("All subsets: " + result);
    }
}
