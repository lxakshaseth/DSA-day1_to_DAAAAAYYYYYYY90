// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 23: Merge k Sorted Lists
 *
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 * Merge all the linked-lists into one sorted linked-list and return it.
 *
 * Optimal Approach: Divide and Conquer (Bottom-Up Pairwise Merge)
 * - Pair up consecutive lists and merge each pair into one sorted list.
 * - Initially interval = 1: merge (0, 1), (2, 3), (4, 5), ... storing results at even indices.
 * - Double the interval each round (2, 4, 8, ...): merge (0, 2), (4, 6), ...
 * - Continue until interval >= k; the completely merged list resides at lists[0].
 * - Across log2(k) merge levels, each node in all lists is processed once per level.
 *
 * Time Complexity: O(N log k) where N is the total number of nodes and k is the number of lists.
 * Space Complexity: O(1) auxiliary space (iterative merging in-place without recursion stack or heap).
 */
public class p23 {

    // Definition for singly-linked list node
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // Method to merge k sorted linked lists
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        int n = lists.length;
        int interval = 1;

        while (interval < n) {
            for (int i = 0; i + interval < n; i += interval * 2) {
                lists[i] = mergeTwoLists(lists[i], lists[i + interval]);
            }
            interval *= 2;
        }

        return lists[0];
    }

    // Helper method to merge two sorted linked lists in O(1) extra space
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }

        tail.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }

    // Helper method to build a linked list from an array
    private static ListNode buildList(int[] values) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int val : values) {
            curr.next = new ListNode(val);
            curr = curr.next;
        }
        return dummy.next;
    }

    // Helper method to format a linked list as a readable string
    private static String listToString(ListNode head) {
        StringBuilder sb = new StringBuilder("[");
        ListNode curr = head;
        while (curr != null) {
            sb.append(curr.val);
            if (curr.next != null) {
                sb.append(", ");
            }
            curr = curr.next;
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        p23 solver = new p23();

        // Test Case 1: Standard k=3 sorted lists
        ListNode[] lists1 = {
            buildList(new int[]{1, 4, 5}),
            buildList(new int[]{1, 3, 4}),
            buildList(new int[]{2, 6})
        };
        ListNode res1 = solver.mergeKLists(lists1);
        System.out.println("Test Case 1 Merged List: " + listToString(res1)); // Expected: [1, 1, 2, 3, 4, 4, 5, 6]

        // Test Case 2: Empty input array
        ListNode[] lists2 = {};
        ListNode res2 = solver.mergeKLists(lists2);
        System.out.println("Test Case 2 Merged List: " + listToString(res2)); // Expected: []

        // Test Case 3: Array containing a single empty list
        ListNode[] lists3 = {null};
        ListNode res3 = solver.mergeKLists(lists3);
        System.out.println("Test Case 3 Merged List: " + listToString(res3)); // Expected: []

        // Test Case 4: Mix of empty and non-empty lists
        ListNode[] lists4 = {
            null,
            buildList(new int[]{-2, 0, 5}),
            null,
            buildList(new int[]{-3, 1, 4})
        };
        ListNode res4 = solver.mergeKLists(lists4);
        System.out.println("Test Case 4 Merged List: " + listToString(res4)); // Expected: [-3, -2, 0, 1, 4, 5]
    }
}
