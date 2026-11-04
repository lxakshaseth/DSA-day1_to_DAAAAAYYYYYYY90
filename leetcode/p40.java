// Defines that this class belongs to the leetcode package
package leetcode;

// Import utility classes
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * LeetCode 40: Combination Sum II
 * Given a collection of candidate numbers (candidates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sum to target.
 * Each number in candidates may only be used ONCE in the combination.
 * Note: The solution set must not contain duplicate combinations.
 *
 * Sorting + Backtracking with Duplicate and Exceeded-Sum Pruning:
 * - Sort candidates initially:
 *   1. Allows immediate pruning (break) when candidates[i] > remaining sum.
 *   2. Groups duplicates together so they can be skipped at the same decision level
 *      (i > start && candidates[i] == candidates[i - 1]).
 * - Recursive Backtracking:
 *   - Base case: remaining == 0 -> valid combination found, add copy to result.
 *   - Loop from start to candidates.length:
 *     - If candidates[i] > remaining: break (since array is sorted, subsequent elements are also too large).
 *     - If i > start && candidates[i] == candidates[i - 1]: continue (skip duplicate branch).
 *     - Add candidates[i] to current combination.
 *     - Recurse with index i + 1 (each number used at most once).
 *     - Remove candidates[i] (backtrack).
 *
 * Time Complexity: O(2^n) worst case (bounded tighter by early break pruning)
 * Space Complexity: O(n) recursion call stack depth and combination buffer
 */
public class p40 {

    // Helper backtracking method with duplicate skip and early break pruning
    private void backtrack(int start, int[] candidates, int remaining,
                           List<Integer> current, List<List<Integer>> result) {

        // Base case: target matched
        if (remaining == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < candidates.length; i++) {

            // Early pruning: candidates are sorted, so any element greater than remaining
            // means all subsequent elements will also exceed the target
            if (candidates[i] > remaining) {
                break;
            }

            // Duplicate pruning: skip identical candidates at the same decision level
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }

            // Choice: include candidate
            current.add(candidates[i]);

            // Explore: recurse with next index (i + 1) since each element can be used once
            backtrack(i + 1, candidates, remaining - candidates[i], current, result);

            // Undo: backtrack
            current.remove(current.size() - 1);
        }
    }

    // Method to find all unique combinations summing to target
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // Sort to enable early pruning and duplicate skipping
        Arrays.sort(candidates);
        backtrack(0, candidates, target, new ArrayList<>(), result);
        return result;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p40 obj = new p40();

        // Test case 1: candidates = [10, 1, 2, 7, 6, 1, 5], target = 8
        // Expected: [[1, 1, 6], [1, 2, 5], [1, 7], [2, 6]]
        int[] candidates1 = { 10, 1, 2, 7, 6, 1, 5 };
        int target1 = 8;
        System.out.println("Combinations for target 8: " + obj.combinationSum2(candidates1, target1));

        // Test case 2: candidates = [2, 5, 2, 1, 2], target = 5
        // Expected: [[1, 2, 2], [5]]
        int[] candidates2 = { 2, 5, 2, 1, 2 };
        int target2 = 5;
        System.out.println("Combinations for target 5: " + obj.combinationSum2(candidates2, target2));
    }
}
