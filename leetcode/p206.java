// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 206: Reverse Linked List
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 *
 * Iterative Three-Pointer Approach:
 * - prev: points to previous reversed node (initially null)
 * - curr: points to current node being processed
 * - nextNode: temporarily stores next node before changing link
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class p206 {

    // Definition for singly-linked list node
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // Method to reverse singly linked list
    public ListNode reverseList(ListNode head) {

        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode nextNode = curr.next; // Save next node
            curr.next = prev;              // Reverse pointer
            prev = curr;                   // Advance prev
            curr = nextNode;               // Advance curr
        }

        return prev; // New head of reversed list
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

        p206 obj = new p206();

        // Create linked list: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        System.out.print("Original list: ");
        printList(head);

        ListNode reversedHead = obj.reverseList(head);

        System.out.print("Reversed list: ");
        printList(reversedHead);
    }
}
