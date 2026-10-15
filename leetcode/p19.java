// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 19: Remove Nth Node From End of List
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 *
 * One-Pass Two-Pointer Approach (with Dummy Head):
 * - Maintain two pointers: fast and slow, starting at dummy.
 * - Advance fast by (n + 1) steps so that a gap of n nodes exists between slow and fast.
 * - Move both pointers forward until fast reaches null.
 * - slow will now point to the node immediately before the one to be removed.
 * - Delete the node by updating slow.next = slow.next.next.
 *
 * Time Complexity: O(L) where L is the length of the linked list
 * Space Complexity: O(1)
 */
public class p19 {

    // Definition for singly-linked list node
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // Method to remove nth node from end of list
    public ListNode removeNthFromEnd(ListNode head, int n) {

        // Dummy node to gracefully handle removing the head node
        ListNode dummy = new ListNode(0, head);
        ListNode fast = dummy;
        ListNode slow = dummy;

        // Move fast ahead by n + 1 steps
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // Move both fast and slow until fast reaches the end
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // Unlink the target node
        slow.next = slow.next.next;

        return dummy.next;
    }

    // Helper method to print linked list
    public static void printList(ListNode head) {
        ListNode temp = head;
        while (temp != null) {
            System.out.print(temp.val + (temp.next != null ? " -> " : ""));
            temp = temp.next;
        }
        System.out.println();
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p19 obj = new p19();

        // Create linked list: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        System.out.print("Original list: ");
        printList(head);

        // Remove 2nd node from end (node with value 4)
        ListNode updatedHead = obj.removeNthFromEnd(head, 2);

        System.out.print("Updated list: ");
        printList(updatedHead);
    }
}
