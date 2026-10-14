// Defines that this class belongs to the leetcode package
package leetcode;

// Import utility classes
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * LeetCode 347: Top K Frequent Elements
 * Given an integer array nums and an integer k, return the k most frequent elements.
 *
 * Bucket Sort approach:
 * 1. Count frequencies using a HashMap.
 * 2. Create buckets where index = frequency.
 * 3. Traverse buckets from highest frequency to lowest to gather top k elements.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class p347 {

    // Method to find the k most frequent elements
    public int[] topKFrequent(int[] nums, int k) {

        // Map to store frequency of each number
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        // Array of lists where index corresponds to frequency count
        @SuppressWarnings("unchecked")
        List<Integer>[] buckets = new List[nums.length + 1];

        // Populate the bucket array
        for (int key : countMap.keySet()) {
            int freq = countMap.get(key);
            if (buckets[freq] == null) {
                buckets[freq] = new ArrayList<>();
            }
            buckets[freq].add(key);
        }

        // Result array of size k
        int[] result = new int[k];
        int index = 0;

        // Traverse buckets backwards from highest possible frequency
        for (int i = buckets.length - 1; i >= 0 && index < k; i--) {
            if (buckets[i] != null) {
                for (int num : buckets[i]) {
                    result[index++] = num;
                    if (index == k) {
                        break;
                    }
                }
            }
        }

        return result;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p347 obj = new p347();

        // Sample input array and k
        int[] nums = { 1, 1, 1, 2, 2, 3 };
        int k = 2;

        // Call method and print result
        int[] topK = obj.topKFrequent(nums, k);
        System.out.println("Top " + k + " frequent elements: " + Arrays.toString(topK));
    }
}
