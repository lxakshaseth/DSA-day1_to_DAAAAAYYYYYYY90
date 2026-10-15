// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 21: Merge Two Sorted Lists
 * You are given the heads of two sorted linked lists list1 and list2.
 * Merge the two lists into one sorted list by splicing together nodes of the first two lists.
 * Return the head of the merged linked list.
 *
 * Dummy Node Pointer Approach:
 * - Use a dummy node to simplify edge cases.
 * - Compare values from list1 and list2, advancing pointer for the smaller node.
 * - Append any remaining nodes directly.
 *
 * Time Complexity: O(n + m)
 * Space Complexity: O(1)
 */
public class p21 {

    // Definition for singly-linked list node
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // Method to merge two sorted lists
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        // Dummy head to initiate merged list
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;

        // Traverse both lists until one is exhausted
        while (list1 != null && list2 != null) {

            if (list1.val <= list2.val) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }

            tail = tail.next;
        }

        // Attach remaining nodes
        tail.next = (list1 != null) ? list1 : list2;

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

        p21 obj = new p21();

        // List 1: 1 -> 2 -> 4
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(2);
        list1.next.next = new ListNode(4);

        // List 2: 1 -> 3 -> 4
        ListNode list2 = new ListNode(1);
        list2.next = new ListNode(3);
        list2.next.next = new ListNode(4);

        System.out.print("List 1: ");
        printList(list1);

        System.out.print("List 2: ");
        printList(list2);

        ListNode merged = obj.mergeTwoLists(list1, list2);

        System.out.print("Merged list: ");
        printList(merged);
    }
}
