// Defines that this class belongs to the leetcode package
package leetcode;

// Import PriorityQueue utility class
import java.util.PriorityQueue;

/*
 * LeetCode 215: Kth Largest Element in an Array
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 * Note that it is the kth largest element in sorted order, not the kth distinct element.
 *
 * Min-Heap (PriorityQueue) Approach:
 * - Maintain a Min-Heap of size k.
 * - Iterate through all elements in nums, offering each element to the heap.
 * - When heap size exceeds k, remove the minimum element (poll).
 * - After processing all elements, the root of the min-heap is the kth largest element.
 *
 * Time Complexity: O(n log k) where n is array length, k is heap capacity
 * Space Complexity: O(k) for the priority queue
 */
public class p215 {

    // Method to find kth largest element using min-heap
    public int findKthLargest(int[] nums, int k) {

        // Min-heap storing at most k largest elements
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int num : nums) {
            minHeap.offer(num);

            // Keep heap size bounded by k
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // Top element is the kth largest
        return minHeap.peek();
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p215 obj = new p215();

        // Sample array and k
        int[] nums1 = { 3, 2, 1, 5, 6, 4 };
        int k1 = 2;
        System.out.println(k1 + "nd largest: " + obj.findKthLargest(nums1, k1)); // Output: 5

        int[] nums2 = { 3, 2, 3, 1, 2, 4, 5, 5, 6 };
        int k2 = 4;
        System.out.println(k2 + "th largest: " + obj.findKthLargest(nums2, k2)); // Output: 4
    }
}
