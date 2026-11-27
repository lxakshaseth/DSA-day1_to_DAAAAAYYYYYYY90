package leetcode;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * LeetCode 1046: Last Stone Weight
 * https://leetcode.com/problems/last-stone-weight/
 *
 * You are given an array of integers stones where stones[i] is the weight of the ith stone.
 * On each turn, we choose the heaviest two stones with weights x and y with x <= y.
 * If x == y, both are destroyed; if x != y, the new weight is y - x.
 * Return the weight of the last remaining stone, or 0 if no stones remain.
 */
public class p1046 {

    /**
     * Max-Heap (PriorityQueue) Simulation
     *
     * Time Complexity: O(n log n)
     * Space Complexity: O(n)
     */
    public int lastStoneWeight(int[] stones) {
        if (stones == null || stones.length == 0) {
            return 0;
        }

        // Max-Heap of stone weights
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int stone : stones) {
            maxHeap.offer(stone);
        }

        while (maxHeap.size() > 1) {
            int y = maxHeap.poll(); // heaviest
            int x = maxHeap.poll(); // second heaviest

            if (y > x) {
                maxHeap.offer(y - x);
            }
        }

        return maxHeap.isEmpty() ? 0 : maxHeap.poll();
    }

    public static void main(String[] args) {
        System.out.println("Testing LeetCode 1046: Last Stone Weight...");
        p1046 solver = new p1046();

        // Test 1: [2,7,4,1,8,1] -> 1
        int[] stones1 = {2, 7, 4, 1, 8, 1};
        int res1 = solver.lastStoneWeight(stones1);
        assert res1 == 1 : "Expected 1, got " + res1;
        System.out.println("Test 1: [2,7,4,1,8,1] -> " + res1 + " (Passed)");

        // Test 2: [1] -> 1
        int[] stones2 = {1};
        int res2 = solver.lastStoneWeight(stones2);
        assert res2 == 1 : "Expected 1, got " + res2;
        System.out.println("Test 2: [1] -> " + res2 + " (Passed)");

        // Test 3: [2, 2] -> 0 (all stones crushed)
        int[] stones3 = {2, 2};
        int res3 = solver.lastStoneWeight(stones3);
        assert res3 == 0 : "Expected 0, got " + res3;
        System.out.println("Test 3: [2,2] -> " + res3 + " (Passed)");

        // Test 4: All identical weights [3, 3, 3, 3] -> 0
        int[] stones4 = {3, 3, 3, 3};
        int res4 = solver.lastStoneWeight(stones4);
        assert res4 == 0 : "Expected 0, got " + res4;
        System.out.println("Test 4: [3,3,3,3] -> " + res4 + " (Passed)");

        // Test 5: Odd number of identical weights [5, 5, 5] -> 5
        int[] stones5 = {5, 5, 5};
        int res5 = solver.lastStoneWeight(stones5);
        assert res5 == 5 : "Expected 5, got " + res5;
        System.out.println("Test 5: [5,5,5] -> " + res5 + " (Passed)");

        System.out.println("All test cases passed successfully!");
    }
}
