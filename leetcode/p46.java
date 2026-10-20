// Defines that this class belongs to the leetcode package
package leetcode;

// Import utility classes
import java.util.ArrayList;
import java.util.List;

/*
 * LeetCode 46: Permutations
 * Given an array nums of distinct integers, return all the possible permutations.
 * You can return the answer in any order.
 *
 * Backtracking Approach:
 * - Build permutations step-by-step.
 * - When current permutation length equals nums.length, a complete permutation is formed.
 * - Otherwise, try adding each unused number, recurse, and then backtrack.
 *
 * Time Complexity: O(n * n!) where n is array length (n! permutations, each taking O(n) to copy)
 * Space Complexity: O(n) recursion call stack depth
 */
public class p46 {

    // Helper backtracking method
    private void backtrack(int[] nums, boolean[] used, List<Integer> current, List<List<Integer>> result) {

        // Base case: current permutation contains all numbers
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {

            // Skip numbers that are already part of current permutation
            if (used[i]) {
                continue;
            }

            // Choice: include nums[i]
            used[i] = true;
            current.add(nums[i]);

            // Explore: recurse to next position
            backtrack(nums, used, current, result);

            // Undo: backtrack
            current.remove(current.size() - 1);
            used[i] = false;
        }
    }

    // Method to generate all permutations
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrack(nums, used, new ArrayList<>(), result);
        return result;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p46 obj = new p46();

        // Sample input
        int[] nums = { 1, 2, 3 };

        // Output: [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
        List<List<Integer>> result = obj.permute(nums);
        System.out.println("All permutations: " + result);
    }
}
