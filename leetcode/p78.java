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
 * Approach 1: Recursive Backtracking (DFS)
 * - State Definition: A subset is constructed incrementally by making binary decisions
 *   (include or exclude) or by exploring all possible extension candidates starting from index 'start'.
 * - At every step, add a copy of the current subset to result because every prefix path is a valid subset.
 * - Iterate from start index to nums.length:
 *   1. Choose: Include nums[i] in the current subset.
 *   2. Explore: Recurse for the next index (i + 1).
 *   3. Undo: Backtrack by removing nums[i] from current subset.
 *
 * Approach 2: Bit Manipulation (Binary Representation)
 * - An array of length n has 2^n possible subsets.
 * - Each integer mask from 0 to (2^n - 1) corresponds to a unique subset:
 *   if the j-th bit of mask is set (1), include nums[j].
 *
 * Time Complexity: O(n * 2^n) - There are 2^n total subsets, and each subset takes O(n) to copy.
 * Space Complexity: O(n) recursion call stack depth (excluding output list).
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

    // Optimal Method: Backtracking
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(0, nums, new ArrayList<>(), result);
        return result;
    }

    // Alternative Method: Bit Manipulation (Binary Masking)
    public List<List<Integer>> subsetsBitmask(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        int totalSubsets = 1 << n; // 2^n subsets

        for (int mask = 0; mask < totalSubsets; mask++) {
            List<Integer> subset = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ((mask & (1 << j)) != 0) {
                    subset.add(nums[j]);
                }
            }
            result.add(subset);
        }
        return result;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p78 obj = new p78();

        // Test case 1: nums = [1, 2, 3]
        int[] nums1 = { 1, 2, 3 };
        System.out.println("Test Case 1 (Backtracking): " + obj.subsets(nums1));
        System.out.println("Test Case 1 (Bitmask):      " + obj.subsetsBitmask(nums1));

        // Test case 2: nums = [0]
        int[] nums2 = { 0 };
        System.out.println("Test Case 2 (Backtracking): " + obj.subsets(nums2));
        System.out.println("Test Case 2 (Bitmask):      " + obj.subsetsBitmask(nums2));

        // Test case 3: nums = [4, 5]
        int[] nums3 = { 4, 5 };
        System.out.println("Test Case 3 (Backtracking): " + obj.subsets(nums3));
        System.out.println("Test Case 3 (Bitmask):      " + obj.subsetsBitmask(nums3));
    }
}
