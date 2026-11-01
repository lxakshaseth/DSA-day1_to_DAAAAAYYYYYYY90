// Defines that this class belongs to the leetcode package
package leetcode;

// Import utility classes
import java.util.ArrayList;
import java.util.List;

/*
 * LeetCode 77: Combinations
 * Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].
 * You may return the answer in any order.
 *
 * Backtracking with Pruning Approach:
 * - Use recursive backtracking to construct combinations incrementally.
 * - Base Case: When the current combination reaches size k, add a copy to result.
 * - Pruning Optimization:
 *   If the remaining elements available in [i, n] are fewer than the elements still needed
 *   (i.e., n - i + 1 < k - current.size()), we can safely break or bound the loop to:
 *   i <= n - (k - current.size()) + 1.
 * - For each candidate i:
 *   1. Choose: add i to current.
 *   2. Explore: recurse with i + 1.
 *   3. Undo: remove the last added element (backtrack).
 *
 * Time Complexity: O(k * C(n, k)) where C(n, k) is n choose k
 * Space Complexity: O(k) recursion call stack depth (excluding output list)
 */
public class p77 {

    // Helper backtracking method with search pruning
    private void backtrack(int start, int n, int k, List<Integer> current, List<List<Integer>> result) {

        // Base case: combination of size k reached
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Pruning: only iterate while enough candidates remain to form size k
        int needed = k - current.size();
        for (int i = start; i <= n - needed + 1; i++) {

            // Choice: include number i
            current.add(i);

            // Explore: recurse with next candidate
            backtrack(i + 1, n, k, current, result);

            // Undo: backtrack
            current.remove(current.size() - 1);
        }
    }

    // Method to generate all combinations of k numbers from 1 to n
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(1, n, k, new ArrayList<>(), result);
        return result;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p77 obj = new p77();

        // Test case 1: n = 4, k = 2
        // Expected: [[1, 2], [1, 3], [1, 4], [2, 3], [2, 4], [3, 4]]
        System.out.println("Combinations (n = 4, k = 2): " + obj.combine(4, 2));

        // Test case 2: n = 1, k = 1
        // Expected: [[1]]
        System.out.println("Combinations (n = 1, k = 1): " + obj.combine(1, 1));

        // Test case 3: n = 5, k = 3
        System.out.println("Combinations (n = 5, k = 3): " + obj.combine(5, 3));
    }
}
