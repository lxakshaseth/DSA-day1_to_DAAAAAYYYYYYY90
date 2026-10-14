// Largest Subarray with 0 Sum
// Given an array of integers, find the length of the longest subarray with sum equal to 0.
//
// Time Complexity: O(n)
// Space Complexity: O(n)
package day4;

import java.util.HashMap;

public class LargestSubarrayZeroSum {

    // Function to find length of largest subarray with 0 sum
    public static int maxLen(int[] arr) {

        // HashMap to store (cumulativeSum -> firstOccurrenceIndex)
        HashMap<Integer, Integer> map = new HashMap<>();

        int sum = 0;
        int maxLength = 0;

        // Traverse the array
        for (int i = 0; i < arr.length; i++) {

            // Add current element to cumulative sum
            sum += arr[i];

            // If cumulative sum is 0, subarray from index 0 to i has sum 0
            if (sum == 0) {
                maxLength = i + 1;
            }

            // If this sum has been seen before, subarray between previous index and i has sum 0
            if (map.containsKey(sum)) {
                maxLength = Math.max(maxLength, i - map.get(sum));
            } else {
                // Store first occurrence of this prefix sum
                map.put(sum, i);
            }
        }

        return maxLength;
    }

    // Main method to test
    public static void main(String[] args) {

        // Sample array
        int[] arr = { 15, -2, 2, -8, 1, 7, 10, 23 };

        // Print length of the largest subarray with sum 0
        System.out.println("Length of largest subarray with 0 sum: " + maxLen(arr));
    }
}
