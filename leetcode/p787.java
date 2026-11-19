// Defines that this class belongs to the leetcode package
package leetcode;

import java.util.Arrays;

/*
 * LeetCode 787: Cheapest Flights Within K Stops
 *
 * There are n cities connected by flights where flights[i] = [from, to, price].
 * Given src, dst, and k, return the cheapest price from src to dst with at most
 * k stops. If no route exists, return -1.
 *
 * Optimal Approach: Bellman-Ford Algorithm (Level-by-Level Edge Relaxation)
 * - At most k stops corresponds to at most k + 1 flights (edges).
 * - Maintain dist[i], the cheapest cost to reach city i from src.
 * - Base Case: dist[src] = 0, and all other dist[i] = INF.
 * - Perform exactly k + 1 rounds of edge relaxation:
 *   - In each round, clone dist into tempDist = Arrays.copyOf(dist, n).
 *     Cloning is essential so that updates in round i do not propagate along a chain
 *     within the same round (which would violate the flight limit).
 *   - For each flight [u, v, price]:
 *       if dist[u] != INF and dist[u] + price < tempDist[v]:
 *           tempDist[v] = dist[u] + price
 *   - Update dist = tempDist after checking all flights.
 * - Return dist[dst] if reachable, else -1.
 *
 * Time Complexity: O((k + 1) * E) where E = flights.length <= 5000 and k <= 100.
 * Space Complexity: O(n) auxiliary space for the distance tracking arrays.
 */
public class p787 {

    private static final int INF = Integer.MAX_VALUE;

    // Method to find the cheapest flight cost with at most k stops
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        if (flights == null || n <= 0) {
            return -1;
        }

        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[src] = 0;

        // Perform at most k + 1 flight hops
        for (int i = 0; i <= k; i++) {
            int[] tempDist = Arrays.copyOf(dist, n);

            for (int[] flight : flights) {
                int u = flight[0];
                int v = flight[1];
                int price = flight[2];

                if (dist[u] != INF && dist[u] + price < tempDist[v]) {
                    tempDist[v] = dist[u] + price;
                }
            }

            dist = tempDist;
        }

        return dist[dst] == INF ? -1 : dist[dst];
    }

    public static void main(String[] args) {
        p787 solver = new p787();

        // Test Case 1: 4 cities with 1 stop limit
        int[][] flights1 = {
            {0, 1, 100},
            {1, 2, 100},
            {2, 0, 100},
            {1, 3, 600},
            {2, 3, 200}
        };
        System.out.println("Test Case 1 Price (k = 1): " +
                solver.findCheapestPrice(4, flights1, 0, 3, 1)); // Expected: 700

        // Test Case 2: 3 cities, 1 stop
        int[][] flights2 = {
            {0, 1, 100},
            {1, 2, 100},
            {0, 2, 500}
        };
        System.out.println("Test Case 2 Price (k = 1): " +
                solver.findCheapestPrice(3, flights2, 0, 2, 1)); // Expected: 200

        // Test Case 3: Same flights with 0 stops (must take direct flight)
        System.out.println("Test Case 3 Price (k = 0): " +
                solver.findCheapestPrice(3, flights2, 0, 2, 0)); // Expected: 500

        // Test Case 4: Destination unreachable
        int[][] flights4 = {
            {0, 1, 100}
        };
        System.out.println("Test Case 4 Price (unreachable): " +
                solver.findCheapestPrice(3, flights4, 0, 2, 1)); // Expected: -1
    }
}
