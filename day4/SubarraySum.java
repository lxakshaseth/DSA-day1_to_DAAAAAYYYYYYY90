// Subarray Sum Equal to K
// Find the total number of continuous subarrays whose sum equals to k.
//
// Time Complexity: O(n)
// Space Complexity: O(n)
package day4;

import java.util.HashMap;

public class SubarraySum {

    // Function to count subarrays with sum equal to k
    public static int subarraySum(int[] arr, int k) {

        // Create a HashMap to store (prefixSum -> frequency)
        HashMap<Integer, Integer> map = new HashMap<>();

        // Base case: prefix sum of 0 has frequency 1
        map.put(0, 1);

        int sum = 0;
        int count = 0;

        // Traverse the array
        for (int i = 0; i < arr.length; i++) {

            // Add current element to cumulative sum
            sum += arr[i];

            // If (sum - k) exists in map, add its frequency to count
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }

            // Put current cumulative sum into map
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        // Return total count of subarrays
        return count;
    }

    // Main method
    public static void main(String[] args) {

        // Sample input array
        int[] arr = { 10, 2, -2, -20, 10 };
        int k = -10;

        // Call method and print result
        System.out.println("Subarrays with sum " + k + ": " + subarraySum(arr, k));
    }
}
