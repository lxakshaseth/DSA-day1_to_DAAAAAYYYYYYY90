// Defines that this class belongs to the leetcode package
package leetcode;

// Import utility classes
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * LeetCode 90: Subsets II
 * Given an integer array nums that may contain duplicates, return all possible
 * subsets (the power set). The solution set must not contain duplicate subsets.
 * Return the solution in any order.
 *
 * Sorting + Backtracking with Duplicate Pruning Approach:
 * - Sort the array first so identical elements are adjacent.
 * - At each recursive step, add a copy of current subset to the result.
 * - Iterate from start index to nums.length:
 *   - If current element is identical to the previous element at the same decision level
 *     (i.e., i > start && nums[i] == nums[i - 1]), skip it to prevent generating duplicate subsets.
 *   - Otherwise:
 *     1. Choose: current.add(nums[i]).
 *     2. Explore: backtrack(i + 1, nums, current, result).
 *     3. Undo: current.remove(current.size() - 1).
 *
 * Time Complexity: O(n * 2^n) - at most 2^n subsets, each taking O(n) to copy; sorting takes O(n log n)
 * Space Complexity: O(n) recursion call stack depth and current subset buffer (excluding result list)
 */
public class p90 {

    // Helper backtracking method with duplicate skip
    private void backtrack(int start, int[] nums, List<Integer> current, List<List<Integer>> result) {

        // Add copy of current subset to result list
        result.add(new ArrayList<>(current));

        for (int i = start; i < nums.length; i++) {

            // Skip duplicates at the same tree depth/recursion level
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }

            // Choice: include element
            current.add(nums[i]);

            // Explore: recurse with next index
            backtrack(i + 1, nums, current, result);

            // Undo: backtrack
            current.remove(current.size() - 1);
        }
    }

    // Method to generate all unique subsets from array with duplicates
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // Sort to group duplicate elements together
        Arrays.sort(nums);
        backtrack(0, nums, new ArrayList<>(), result);
        return result;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p90 obj = new p90();

        // Test case 1: nums = [1, 2, 2]
        // Expected: [[], [1], [1, 2], [1, 2, 2], [2], [2, 2]]
        int[] nums1 = { 1, 2, 2 };
        System.out.println("Subsets for [1, 2, 2]: " + obj.subsetsWithDup(nums1));

        // Test case 2: nums = [0]
        // Expected: [[], [0]]
        int[] nums2 = { 0 };
        System.out.println("Subsets for [0]: " + obj.subsetsWithDup(nums2));

        // Test case 3: nums = [4, 4, 4, 1, 4]
        // Expected: unique subsets without duplicate combinations
        int[] nums3 = { 4, 4, 4, 1, 4 };
        System.out.println("Subsets count for [4, 4, 4, 1, 4]: " + obj.subsetsWithDup(nums3).size());
        System.out.println("Subsets for [4, 4, 4, 1, 4]: " + obj.subsetsWithDup(nums3));
    }
}
