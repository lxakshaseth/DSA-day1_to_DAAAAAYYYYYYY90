package leetcode;

/**
 * LeetCode 45: Jump Game II
 * https://leetcode.com/problems/jump-game-ii/
 *
 * You are given a 0-indexed array of integers nums of length n.
 * Return the minimum number of jumps to reach nums[n - 1].
 */
public class p45 {

    /**
     * Greedy Range Expansion (Implicit BFS).
     *
     * At each jump level [start, currentEnd], find the maximum reachable index
     * for the next jump. When i reaches currentEnd, commit a jump and expand the boundary.
     *
     * Time Complexity: O(n) single pass
     * Space Complexity: O(1) auxiliary space
     */
    public int jump(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }

        int jumps = 0;
        int currentEnd = 0;
        int farthest = 0;

        // Iterate up to n - 2 because reaching n - 1 doesn't require jumping further
        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);

            // When we finish exploring the reach of the current jump level
            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;

                // Early exit if the target is already reachable
                if (currentEnd >= nums.length - 1) {
                    break;
                }
            }
        }

        return jumps;
    }

    public static void main(String[] args) {
        System.out.println("Testing LeetCode 45: Jump Game II...");
        p45 solver = new p45();

        // Test 1: [2,3,1,1,4] -> 2 (0 -> 1 -> 4)
        int[] nums1 = {2, 3, 1, 1, 4};
        int res1 = solver.jump(nums1);
        assert res1 == 2 : "Expected 2, got " + res1;
        System.out.println("Test 1: [2,3,1,1,4] -> " + res1 + " (Passed)");

        // Test 2: [2,3,0,1,4] -> 2
        int[] nums2 = {2, 3, 0, 1, 4};
        int res2 = solver.jump(nums2);
        assert res2 == 2 : "Expected 2, got " + res2;
        System.out.println("Test 2: [2,3,0,1,4] -> " + res2 + " (Passed)");

        // Test 3: [1] -> 0 (already at destination)
        int[] nums3 = {1};
        int res3 = solver.jump(nums3);
        assert res3 == 0 : "Expected 0, got " + res3;
        System.out.println("Test 3: [1] -> " + res3 + " (Passed)");

        // Test 4: [1,2,3] -> 2
        int[] nums4 = {1, 2, 3};
        int res4 = solver.jump(nums4);
        assert res4 == 2 : "Expected 2, got " + res4;
        System.out.println("Test 4: [1,2,3] -> " + res4 + " (Passed)");

        // Test 5: [10,9,8,7,6,5,4,3,2,1,1,0] -> 2 (0 -> 10 -> 11)
        int[] nums5 = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 1, 0};
        int res5 = solver.jump(nums5);
        assert res5 == 2 : "Expected 2, got " + res5;
        System.out.println("Test 5: [10,...] -> " + res5 + " (Passed)");

        System.out.println("All test cases passed successfully!");
    }
}
