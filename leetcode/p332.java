// Defines that this class belongs to the leetcode package
package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*
 * LeetCode 332: Reconstruct Itinerary
 *
 * You are given a list of airline tickets where tickets[i] = [from_i, to_i].
 * Reconstruct the itinerary in order and return it. The itinerary must begin
 * with "JFK". If there are multiple valid itineraries, return the one with
 * the smallest lexical order. All tickets must be used exactly once.
 *
 * Optimal Approach: Hierholzer's Algorithm for Eulerian Path
 * - This problem translates to finding an Eulerian path in a directed multigraph
 *   starting at "JFK" that traverses every directed edge (ticket) once.
 * - Build an adjacency list mapping each departure airport to a min-heap
 *   (PriorityQueue<String>) of destination airports, ensuring lexical ordering.
 * - Perform post-order DFS (Hierholzer's algorithm):
 *   - From current airport u, greedily poll the smallest destination v from u's min-heap
 *     and recursively traverse it.
 *   - When an airport has no remaining outgoing flights (a dead end or end of cycle),
 *     prepend it to the head of a LinkedList (itinerary.addFirst(u)).
 *   - Prepending on recursion unwind guarantees that dead-end cycles are placed
 *     at the tail of the itinerary while valid loops complete first.
 *
 * Time Complexity: O(E log E) where E is the number of tickets (due to min-heap operations).
 * Space Complexity: O(V + E) for the graph representation and recursion call stack.
 */
public class p332 {

    // Method to reconstruct the itinerary starting from "JFK"
    public List<String> findItinerary(List<List<String>> tickets) {
        LinkedList<String> itinerary = new LinkedList<>();
        if (tickets == null || tickets.isEmpty()) {
            return itinerary;
        }

        // Build directed multigraph with min-heaps for alphabetical neighbor selection
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for (List<String> ticket : tickets) {
            graph.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>()).offer(ticket.get(1));
        }

        // Run Hierholzer's algorithm starting from JFK
        dfs("JFK", graph, itinerary);

        return itinerary;
    }

    private void dfs(String airport, Map<String, PriorityQueue<String>> graph, LinkedList<String> itinerary) {
        PriorityQueue<String> destinations = graph.get(airport);
        while (destinations != null && !destinations.isEmpty()) {
            String nextAirport = destinations.poll();
            dfs(nextAirport, graph, itinerary);
        }
        // Prepend after exhausting all outgoing edges from this node
        itinerary.addFirst(airport);
    }

    public static void main(String[] args) {
        p332 solver = new p332();

        // Test Case 1: Standard single path
        List<List<String>> tickets1 = Arrays.asList(
            Arrays.asList("MUC", "LHR"),
            Arrays.asList("JFK", "MUC"),
            Arrays.asList("SFO", "SJC"),
            Arrays.asList("LHR", "SFO")
        );
        System.out.println("Test Case 1 Itinerary: " + solver.findItinerary(tickets1));
        // Expected: [JFK, MUC, LHR, SFO, SJC]

        // Test Case 2: Lexical ordering choice (ATL vs SFO from JFK)
        List<List<String>> tickets2 = Arrays.asList(
            Arrays.asList("JFK", "SFO"),
            Arrays.asList("JFK", "ATL"),
            Arrays.asList("SFO", "ATL"),
            Arrays.asList("ATL", "JFK"),
            Arrays.asList("ATL", "SFO")
        );
        System.out.println("Test Case 2 Itinerary: " + solver.findItinerary(tickets2));
        // Expected: [JFK, ATL, JFK, SFO, ATL, SFO]

        // Test Case 3: Dead end vs loop (JFK -> KUL is a dead end)
        List<List<String>> tickets3 = Arrays.asList(
            Arrays.asList("JFK", "KUL"),
            Arrays.asList("JFK", "NRT"),
            Arrays.asList("NRT", "JFK")
        );
        System.out.println("Test Case 3 Itinerary: " + solver.findItinerary(tickets3));
        // Expected: [JFK, NRT, JFK, KUL]
    }
}
