// Find Itinerary from Tickets
// Given a list of one-way flight tickets from various cities,
// reconstruct the complete travel itinerary in order.
//
// Time Complexity: O(n)
// Space Complexity: O(n)
package day4;

import java.util.HashMap;

public class FindItinerary {

    // Function to find the starting point of the journey
    public static String getStart(HashMap<String, String> tickets) {

        // Reverse map to track all destinations
        HashMap<String, String> revMap = new HashMap<>();

        for (String key : tickets.keySet()) {
            revMap.put(tickets.get(key), key);
        }

        // The starting city will never exist as a destination in revMap
        for (String key : tickets.keySet()) {
            if (!revMap.containsKey(key)) {
                return key; // Starting point found
            }
        }

        return null;
    }

    // Function to print the complete itinerary
    public static void printItinerary(HashMap<String, String> tickets) {

        // Find starting city
        String start = getStart(tickets);
        System.out.print(start);

        // Follow the path using the tickets map
        for (int i = 0; i < tickets.size(); i++) {
            System.out.print(" -> " + tickets.get(start));
            start = tickets.get(start);
        }
        System.out.println();
    }

    // Main method
    public static void main(String[] args) {

        HashMap<String, String> tickets = new HashMap<>();
        tickets.put("Chennai", "Bengaluru");
        tickets.put("Mumbai", "Delhi");
        tickets.put("Goa", "Chennai");
        tickets.put("Delhi", "Goa");

        System.out.print("Complete Itinerary: ");
        printItinerary(tickets);
    }
}
