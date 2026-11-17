// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 25: Reverse Nodes in k-Group
 *
 * Given the head of a linked list, reverse the nodes of the list k at a time,
 * and return the modified list. If the number of nodes is not a multiple of k,
 * left-out nodes in the end remain in their original order.
 *
 * Optimal Approach: Iterative In-Place Reversal with Dummy Head
 * - Allocate a dummy node pointing to head to simplify edge cases at the list boundary.
 * - Maintain groupPrev (initially dummy), the node immediately preceding the current group of size k.
 * - In each step:
 *   1. Count k nodes ahead from groupPrev to find kthNode.
 *   2. If fewer than k nodes remain (kthNode == null), terminate the process.
 *   3. Record groupNext = kthNode.next.
 *   4. Reverse the k nodes between groupPrev.next and kthNode in-place by redirecting pointers.
 *      Initializing prev = groupNext cleanly attaches the reversed sublist's tail to groupNext.
 *   5. Connect groupPrev.next = kthNode, and advance groupPrev to the former head of the group
 *      (which is now the tail).
 *
 * Time Complexity: O(n) - Each node is traversed twice (once to locate kthNode, once during reversal).
 * Space Complexity: O(1) auxiliary space - Strictly iterative pointer manipulation without recursion stack.
 */
public class p25 {

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

    // Method to reverse nodes in groups of k
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 1) {
            return head;
        }

        ListNode dummy = new ListNode(0, head);
        ListNode groupPrev = dummy;

        while (true) {
            // Find the k-th node from groupPrev
            ListNode kth = getKth(groupPrev, k);
            if (kth == null) {
                break; // Less than k nodes left; keep as-is
            }

            ListNode groupNext = kth.next;

            // Reverse the current k nodes: [groupPrev.next ... kth]
            ListNode prev = groupNext;
            ListNode curr = groupPrev.next;
            while (curr != groupNext) {
                ListNode nextNode = curr.next;
                curr.next = prev;
                prev = curr;
                curr = nextNode;
            }

            // Re-link groupPrev to the new head of reversed group (kth)
            ListNode groupTail = groupPrev.next;
            groupPrev.next = kth;
            groupPrev = groupTail;
        }

        return dummy.next;
    }

    // Helper to find the k-th node starting from curr
    private ListNode getKth(ListNode curr, int k) {
        while (curr != null && k > 0) {
            curr = curr.next;
            k--;
        }
        return curr;
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

    // Helper method to serialize a linked list to String
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
        p25 solver = new p25();

        // Test Case 1: [1, 2, 3, 4, 5], k = 2
        ListNode list1 = buildList(new int[]{1, 2, 3, 4, 5});
        System.out.println("Test Case 1 (k = 2): " +
                listToString(solver.reverseKGroup(list1, 2))); // Expected: [2, 1, 4, 3, 5]

        // Test Case 2: [1, 2, 3, 4, 5], k = 3
        ListNode list2 = buildList(new int[]{1, 2, 3, 4, 5});
        System.out.println("Test Case 2 (k = 3): " +
                listToString(solver.reverseKGroup(list2, 3))); // Expected: [3, 2, 1, 4, 5]

        // Test Case 3: k equals list length
        ListNode list3 = buildList(new int[]{1, 2, 3, 4});
        System.out.println("Test Case 3 (k = 4): " +
                listToString(solver.reverseKGroup(list3, 4))); // Expected: [4, 3, 2, 1]

        // Test Case 4: k = 1 (no changes)
        ListNode list4 = buildList(new int[]{1, 2, 3});
        System.out.println("Test Case 4 (k = 1): " +
                listToString(solver.reverseKGroup(list4, 1))); // Expected: [1, 2, 3]
    }
}
