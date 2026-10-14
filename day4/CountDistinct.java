// Count Distinct Elements
// Given an integer array, find the count of distinct (unique) elements.
//
// Time Complexity: O(n)
// Space Complexity: O(n)
package day4;

import java.util.HashSet;

public class CountDistinct {

    // Function to count distinct elements in array
    public static int countDistinct(int[] nums) {

        // HashSet only stores unique elements
        HashSet<Integer> set = new HashSet<>();

        // Add all elements from the array into HashSet
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        // The size of the set represents the count of unique elements
        return set.size();
    }

    // Main method to test the function
    public static void main(String[] args) {

        // Sample array with duplicate values
        int[] nums = { 4, 3, 2, 5, 6, 7, 3, 4, 2, 1 };

        // Print count of distinct elements
        System.out.println("Distinct elements count: " + countDistinct(nums));
    }
}
