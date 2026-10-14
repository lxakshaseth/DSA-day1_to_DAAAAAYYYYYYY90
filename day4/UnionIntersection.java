// Union and Intersection of Two Arrays
// Find the union and intersection counts and elements using HashSet.
//
// Time Complexity: O(n + m)
// Space Complexity: O(n + m)
package day4;

import java.util.ArrayList;
import java.util.HashSet;

public class UnionIntersection {

    // Function to calculate Union of two arrays
    public static HashSet<Integer> getUnion(int[] arr1, int[] arr2) {
        HashSet<Integer> set = new HashSet<>();

        // Add all elements of first array
        for (int num : arr1) {
            set.add(num);
        }

        // Add all elements of second array
        for (int num : arr2) {
            set.add(num);
        }

        // Set contains unique union elements
        return set;
    }

    // Function to calculate Intersection of two arrays
    public static ArrayList<Integer> getIntersection(int[] arr1, int[] arr2) {
        HashSet<Integer> set = new HashSet<>();
        ArrayList<Integer> result = new ArrayList<>();

        // Add all elements of first array into HashSet
        for (int num : arr1) {
            set.add(num);
        }

        // Check if elements of second array exist in the set
        for (int num : arr2) {
            if (set.contains(num)) {
                result.add(num);
                // Remove from set to avoid duplicate intersection counting
                set.remove(num);
            }
        }

        return result;
    }

    // Main method
    public static void main(String[] args) {
        int[] arr1 = { 7, 3, 9 };
        int[] arr2 = { 6, 3, 9, 2, 9, 4 };

        HashSet<Integer> union = getUnion(arr1, arr2);
        ArrayList<Integer> intersection = getIntersection(arr1, arr2);

        System.out.println("Union size: " + union.size() + ", elements: " + union);
        System.out.println("Intersection size: " + intersection.size() + ", elements: " + intersection);
    }
}
