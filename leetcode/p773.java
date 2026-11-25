package leetcode;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * LeetCode 773: Sliding Puzzle
 * https://leetcode.com/problems/sliding-puzzle/
 *
 * On an 2 x 3 board, there are five tiles labeled from 1 to 5, and an empty square represented by 0.
 * A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.
 * The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].
 * Given the puzzle board, return the least number of moves to reach the target, or -1 if impossible.
 */
public class p773 {

    private static final String TARGET = "123450";

    // Adjacency list for 1D indices corresponding to the 2x3 board:
    // 0 1 2
    // 3 4 5
    private static final int[][] DIRS = {
        {1, 3},       // 0 can move to 1 (right) or 3 (down)
        {0, 2, 4},    // 1 can move to 0 (left), 2 (right), or 4 (down)
        {1, 5},       // 2 can move to 1 (left) or 5 (down)
        {0, 4},       // 3 can move to 0 (up) or 4 (right)
        {1, 3, 5},    // 4 can move to 1 (up), 3 (left), or 5 (right)
        {2, 4}        // 5 can move to 2 (up) or 4 (left)
    };

    public int slidingPuzzle(int[][] board) {
        StringBuilder startBuilder = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                startBuilder.append(board[i][j]);
            }
        }
        String start = startBuilder.toString();

        if (start.equals(TARGET)) {
            return 0;
        }

        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);

        int moves = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            moves++;

            for (int k = 0; k < levelSize; k++) {
                String current = queue.poll();
                int zeroIdx = current.indexOf('0');

                for (int nextIdx : DIRS[zeroIdx]) {
                    String nextState = swap(current, zeroIdx, nextIdx);

                    if (nextState.equals(TARGET)) {
                        return moves;
                    }

                    if (visited.add(nextState)) {
                        queue.offer(nextState);
                    }
                }
            }
        }

        return -1;
    }

    private String swap(String s, int i, int j) {
        char[] chars = s.toCharArray();
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        return new String(chars);
    }

    public static void main(String[] args) {
        System.out.println("Testing LeetCode 773: Sliding Puzzle...");
        p773 solver = new p773();

        // Test 1: [[1,2,3],[4,0,5]] -> 1 move
        int[][] board1 = {{1, 2, 3}, {4, 0, 5}};
        int res1 = solver.slidingPuzzle(board1);
        assert res1 == 1 : "Expected 1, got " + res1;
        System.out.println("Test 1: [[1,2,3],[4,0,5]] -> " + res1 + " (Passed)");

        // Test 2: [[1,2,3],[5,4,0]] -> -1 (unsolvable parity)
        int[][] board2 = {{1, 2, 3}, {5, 4, 0}};
        int res2 = solver.slidingPuzzle(board2);
        assert res2 == -1 : "Expected -1, got " + res2;
        System.out.println("Test 2: [[1,2,3],[5,4,0]] -> " + res2 + " (Passed)");

        // Test 3: [[4,1,2],[5,0,3]] -> 5 moves
        int[][] board3 = {{4, 1, 2}, {5, 0, 3}};
        int res3 = solver.slidingPuzzle(board3);
        assert res3 == 5 : "Expected 5, got " + res3;
        System.out.println("Test 3: [[4,1,2],[5,0,3]] -> " + res3 + " (Passed)");

        // Test 4: Already solved [[1,2,3],[4,5,0]] -> 0 moves
        int[][] board4 = {{1, 2, 3}, {4, 5, 0}};
        int res4 = solver.slidingPuzzle(board4);
        assert res4 == 0 : "Expected 0, got " + res4;
        System.out.println("Test 4: [[1,2,3],[4,5,0]] -> " + res4 + " (Passed)");

        System.out.println("All test cases passed successfully!");
    }
}
