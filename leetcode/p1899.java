package leetcode;

/**
 * LeetCode 1899: Merge Triplets to Form Target Triplet
 * https://leetcode.com/problems/merge-triplets-to-form-target-triplet/
 *
 * A triplet is an array of three integers. You are given a 2D integer array triplets,
 * where triplets[i] = [ai, bi, ci] describes the i-th triplet. You are also given
 * an integer array target = [x, y, z] that describes the target triplet.
 *
 * Return true if it is possible to obtain the target triplet [x, y, z] as an element
 * of triplets, or false otherwise.
 */
public class p1899 {

    /**
     * Greedy Component-Wise Filtering and Merging.
     *
     * Any triplet with a component strictly greater than target's component can never be used,
     * as the max operation is monotonic and would permanently exceed the target value.
     * Among all valid triplets (t[0] <= target[0] && t[1] <= target[1] && t[2] <= target[2]),
     * we check if we can match each target component at least once.
     *
     * Time Complexity: O(n) where n = triplets.length
     * Space Complexity: O(1) auxiliary space
     */
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        boolean match0 = false;
        boolean match1 = false;
        boolean match2 = false;

        for (int[] t : triplets) {
            // Discard triplets that exceed target in any dimension
            if (t[0] > target[0] || t[1] > target[1] || t[2] > target[2]) {
                continue;
            }

            // Check if this valid triplet can supply the required target values
            if (t[0] == target[0]) match0 = true;
            if (t[1] == target[1]) match1 = true;
            if (t[2] == target[2]) match2 = true;

            // Early exit if all target components have been achieved
            if (match0 && match1 && match2) {
                return true;
            }
        }

        return match0 && match1 && match2;
    }

    public static void main(String[] args) {
        System.out.println("Testing LeetCode 1899: Merge Triplets to Form Target Triplet...");
        p1899 solver = new p1899();

        // Test 1: [[2,5,3],[1,8,4],[1,7,5]], target = [2,7,5] -> true
        int[][] triplets1 = {{2, 5, 3}, {1, 8, 4}, {1, 7, 5}};
        int[] target1 = {2, 7, 5};
        boolean res1 = solver.mergeTriplets(triplets1, target1);
        assert res1 : "Expected true for Test 1";
        System.out.println("Test 1: " + res1 + " (Passed)");

        // Test 2: [[3,4,5],[4,5,6]], target = [3,2,5] -> false
        int[][] triplets2 = {{3, 4, 5}, {4, 5, 6}};
        int[] target2 = {3, 2, 5};
        boolean res2 = solver.mergeTriplets(triplets2, target2);
        assert !res2 : "Expected false for Test 2";
        System.out.println("Test 2: " + res2 + " (Passed)");

        // Test 3: [[2,5,3],[2,3,4],[1,2,5],[5,2,3]], target = [5,5,5] -> true
        int[][] triplets3 = {{2, 5, 3}, {2, 3, 4}, {1, 2, 5}, {5, 2, 3}};
        int[] target3 = {5, 5, 5};
        boolean res3 = solver.mergeTriplets(triplets3, target3);
        assert res3 : "Expected true for Test 3";
        System.out.println("Test 3: " + res3 + " (Passed)");

        // Test 4: Single triplet exactly matching target -> true
        int[][] triplets4 = {{1, 2, 3}};
        int[] target4 = {1, 2, 3};
        boolean res4 = solver.mergeTriplets(triplets4, target4);
        assert res4 : "Expected true for Test 4";
        System.out.println("Test 4: " + res4 + " (Passed)");

        System.out.println("All test cases passed successfully!");
    }
}
