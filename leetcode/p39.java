// Defines that this class belongs to the leetcode package
package leetcode;

// Import utility classes
import java.util.ArrayList;
import java.util.List;

/*
 * LeetCode 39: Combination Sum
 * Given an array of distinct integers candidates and a target integer target, return a list
 * of all unique combinations of candidates where the chosen numbers sum to target.
 * The same number may be chosen from candidates an unlimited number of times.
 *
 * Backtracking Approach:
 * - At each recursive step, we can either:
 *   1. Pick candidates[i] and recurse with same index i (allowing reuse).
 *   2. Or skip candidates[i] and move to i + 1.
 * - Base cases:
 *   - remaining == 0: valid combination found, add copy to result.
 *   - remaining < 0: exceeded target, prune branch.
 *
 * Time Complexity: O(2^(target / min)) where min is smallest candidate
 * Space Complexity: O(target / min) recursion stack depth
 */
public class p39 {

    // Helper backtracking method
    private void backtrack(int start, int[] candidates, int remaining,
                           List<Integer> current, List<List<Integer>> result) {

        // Base case: exact target matched
        if (remaining == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Base case: exceeded target, prune search path
        if (remaining < 0) {
            return;
        }

        for (int i = start; i < candidates.length; i++) {

            // Choice: include candidate
            current.add(candidates[i]);

            // Explore: recurse staying at index i (unlimited reuse permitted)
            backtrack(i, candidates, remaining - candidates[i], current, result);

            // Undo: backtrack
            current.remove(current.size() - 1);
        }
    }

    // Method to find all combination sums
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(0, candidates, target, new ArrayList<>(), result);
        return result;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p39 obj = new p39();

        // Sample input
        int[] candidates = { 2, 3, 6, 7 };
        int target = 7;

        // Output: [[2, 2, 3], [7]]
        List<List<Integer>> result = obj.combinationSum(candidates, target);
        System.out.println("Combinations summing to " + target + ": " + result);
    }
}
