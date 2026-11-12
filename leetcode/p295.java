// Defines that this class belongs to the leetcode package
package leetcode;

import java.util.Collections;
import java.util.PriorityQueue;

/*
 * LeetCode 295: Find Median from Data Stream
 *
 * Design a data structure that supports adding integers from a data stream
 * and finding the median of all elements added so far in logarithmic / constant time.
 *
 * Optimal Approach: Two Heaps (Max-Heap + Min-Heap)
 * - Maintain two balanced priority queues:
 *   1. maxHeap (low): Stores the smaller half of the stream (max at top).
 *   2. minHeap (high): Stores the larger half of the stream (min at top).
 * - Invariants maintained after every addNum(num):
 *   1. Order: Every element in maxHeap <= every element in minHeap.
 *   2. Balance: maxHeap.size() == minHeap.size() OR maxHeap.size() == minHeap.size() + 1.
 * - Insertion steps in addNum(num):
 *   - Offer num to maxHeap, then poll maxHeap's top and offer it to minHeap to enforce ordering.
 *   - If minHeap grows larger than maxHeap, poll minHeap's top back into maxHeap to rebalance sizes.
 * - Finding the median in findMedian():
 *   - If maxHeap has one extra element (odd count), return maxHeap.peek().
 *   - Otherwise (even count), return the average of maxHeap.peek() and minHeap.peek().
 *
 * Time Complexity:
 * - addNum(): O(log n) due to heap insertions/removals
 * - findMedian(): O(1)
 * Space Complexity: O(n) to store all elements across both heaps
 */
public class p295 {

    public static class MedianFinder {

        private final PriorityQueue<Integer> maxHeap; // Smaller half
        private final PriorityQueue<Integer> minHeap; // Larger half

        public MedianFinder() {
            this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            this.minHeap = new PriorityQueue<>();
        }

        // Inserts a number from the stream while preserving heap ordering and size balance
        public void addNum(int num) {
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());

            if (minHeap.size() > maxHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
        }

        // Returns the median in O(1) time
        public double findMedian() {
            if (maxHeap.size() > minHeap.size()) {
                return maxHeap.peek();
            }
            return ((double) maxHeap.peek() + minHeap.peek()) / 2.0;
        }
    }

    public static void main(String[] args) {
        // Test Case 1: LeetCode standard example
        MedianFinder mf1 = new MedianFinder();
        mf1.addNum(1);
        mf1.addNum(2);
        System.out.println("Test Case 1 Median after [1, 2]: " + mf1.findMedian()); // Expected: 1.5
        mf1.addNum(3);
        System.out.println("Test Case 1 Median after [1, 2, 3]: " + mf1.findMedian()); // Expected: 2.0

        // Test Case 2: Descending negative values
        MedianFinder mf2 = new MedianFinder();
        mf2.addNum(-1);
        System.out.println("Test Case 2 Median [-1]: " + mf2.findMedian()); // Expected: -1.0
        mf2.addNum(-2);
        System.out.println("Test Case 2 Median [-1, -2]: " + mf2.findMedian()); // Expected: -1.5
        mf2.addNum(-3);
        System.out.println("Test Case 2 Median [-1, -2, -3]: " + mf2.findMedian()); // Expected: -2.0
        mf2.addNum(-4);
        System.out.println("Test Case 2 Median [-1, -2, -3, -4]: " + mf2.findMedian()); // Expected: -2.5
        mf2.addNum(-5);
        System.out.println("Test Case 2 Median [-1, -2, -3, -4, -5]: " + mf2.findMedian()); // Expected: -3.0

        // Test Case 3: Duplicate elements and large values
        MedianFinder mf3 = new MedianFinder();
        mf3.addNum(Integer.MAX_VALUE);
        mf3.addNum(Integer.MAX_VALUE);
        System.out.println("Test Case 3 Median [MAX, MAX]: " + mf3.findMedian()); // Expected: 2.147483647E9
    }
}
