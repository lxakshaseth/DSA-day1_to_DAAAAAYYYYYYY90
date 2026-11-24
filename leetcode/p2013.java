package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 2013: Detect Squares
 * https://leetcode.com/problems/detect-squares/
 *
 * Design a data structure that receives a stream of points on an axis-aligned 2D plane
 * and can count the number of ways to choose three points such that the three points
 * together with a query point form an axis-aligned square with positive area.
 */
public class p2013 {

    public static class DetectSquares {
        // Since coordinates are 0 <= x, y <= 1000, we can use a direct 2D frequency array.
        private final int[][] count;
        private final List<int[]> points;

        public DetectSquares() {
            count = new int[1001][1001];
            points = new ArrayList<>();
        }

        public void add(int[] point) {
            points.add(point);
            count[point[0]][point[1]]++;
        }

        public int count(int[] point) {
            int qx = point[0];
            int qy = point[1];
            int totalSquares = 0;

            // Iterate through all added points as potential diagonal points
            for (int[] p : points) {
                int px = p[0];
                int py = p[1];

                // Must have positive area (not the same point) and form a square diagonal
                if (px == qx || py == qy || Math.abs(px - qx) != Math.abs(py - qy)) {
                    continue;
                }

                // If (px, py) is the diagonal opposite to (qx, qy), the other two corners
                // of the axis-aligned square must be (qx, py) and (px, qy).
                int ways = count[qx][py] * count[px][qy];
                totalSquares += ways;
            }

            return totalSquares;
        }
    }

    public static void main(String[] args) {
        System.out.println("Testing LeetCode 2013: Detect Squares...");

        // Example 1
        DetectSquares detectSquares = new DetectSquares();
        detectSquares.add(new int[]{3, 10});
        detectSquares.add(new int[]{11, 2});
        detectSquares.add(new int[]{3, 2});

        // Query [11, 10]: should return 1
        int res1 = detectSquares.count(new int[]{11, 10});
        assert res1 == 1 : "Expected 1, got " + res1;
        System.out.println("Query [11, 10] -> " + res1 + " (Passed)");

        // Query [14, 8]: should return 0
        int res2 = detectSquares.count(new int[]{14, 8});
        assert res2 == 0 : "Expected 0, got " + res2;
        System.out.println("Query [14, 8] -> " + res2 + " (Passed)");

        // Add duplicate point [11, 2]
        detectSquares.add(new int[]{11, 2});

        // Query [11, 10] again: count of corner [11, 2] is 2, so should return 2
        int res3 = detectSquares.count(new int[]{11, 10});
        assert res3 == 2 : "Expected 2, got " + res3;
        System.out.println("Query [11, 10] after duplicate add -> " + res3 + " (Passed)");

        // Additional edge case: single point queries with no squares
        DetectSquares ds2 = new DetectSquares();
        ds2.add(new int[]{0, 0});
        ds2.add(new int[]{0, 1});
        ds2.add(new int[]{1, 0});
        assert ds2.count(new int[]{1, 1}) == 1 : "Expected 1";
        System.out.println("Unit square test passed!");

        // Multiple overlapping squares test
        ds2.add(new int[]{0, 0});
        assert ds2.count(new int[]{1, 1}) == 2 : "Expected 2 with duplicate (0,0)";
        System.out.println("Duplicate corner test passed!");

        System.out.println("All test cases passed successfully!");
    }
}
