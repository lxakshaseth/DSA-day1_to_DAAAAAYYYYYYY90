// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 141: Linked List Cycle
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 * Return true if there is a cycle in the linked list. Otherwise, return false.
 *
 * Floyd's Cycle-Finding Algorithm (Tortoise and Hare):
 * - slow pointer moves 1 step at a time.
 * - fast pointer moves 2 steps at a time.
 * - If a cycle exists, the fast pointer will eventually catch up and meet the slow pointer.
 * - If fast reaches null, the list terminates with no cycle.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class p141 {

    // Definition for singly-linked list node
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // Method to detect if a cycle exists in the linked list
    public boolean hasCycle(ListNode head) {

        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;         // Move slow pointer 1 step
            fast = fast.next.next;    // Move fast pointer 2 steps

            // If slow and fast meet, cycle exists
            if (slow == fast) {
                return true;
            }
        }

        // Fast pointer reached the end of list; no cycle
        return false;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p141 obj = new p141();

        // Test Case 1: List with cycle: 3 -> 2 -> 0 -> -4 -> (back to node 2)
        ListNode head1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node0 = new ListNode(0);
        ListNode nodeNeg4 = new ListNode(-4);

        head1.next = node2;
        node2.next = node0;
        node0.next = nodeNeg4;
        nodeNeg4.next = node2; // Creates cycle

        System.out.println("List 1 has cycle: " + obj.hasCycle(head1));

        // Test Case 2: List without cycle: 1 -> 2
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);

        System.out.println("List 2 has cycle: " + obj.hasCycle(head2));
    }
}
