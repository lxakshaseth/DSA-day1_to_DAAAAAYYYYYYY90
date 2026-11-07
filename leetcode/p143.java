// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 143: Reorder List
 * You are given the head of a singly linked list. The list can be represented as:
 * L0 -> L1 -> ... -> Ln - 1 -> Ln
 * Reorder the list to be on the following form:
 * L0 -> Ln -> L1 -> Ln - 1 -> L2 -> Ln - 2 -> ...
 * You may not modify the values in the list's nodes. Only nodes themselves may be changed.
 *
 * Three-Step In-Place Pointer Approach:
 * 1. Find Middle: Use fast and slow pointers (slow = head, fast = head.next) to split the list
 *    into two halves of equal (or near-equal) size.
 * 2. Reverse Second Half: Disconnect the first half and reverse the second half in-place.
 * 3. Interleave / Merge: Merge the two halves alternately (one from first, one from second).
 *
 * Time Complexity: O(n) linear time with three sequential O(n/2) passes
 * Space Complexity: O(1) auxiliary space (in-place pointer rearrangement)
 */
public class p143 {

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

    // Method to reorder the linked list in-place
    public void reorderList(ListNode head) {

        if (head == null || head.next == null || head.next.next == null) {
            return;
        }

        // Step 1: Find middle node using slow and fast pointers
        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Split and reverse the second half
        ListNode second = slow.next;
        slow.next = null; // Sever link between first and second halves

        ListNode prev = null;
        ListNode curr = second;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        second = prev; // Head of reversed second half

        // Step 3: Interleave / merge first and second halves
        ListNode first = head;
        while (second != null) {
            ListNode temp1 = first.next;
            ListNode temp2 = second.next;

            first.next = second;
            second.next = temp1;

            first = temp1;
            second = temp2;
        }
    }

    // Helper method to build a linked list from an array
    public static ListNode buildList(int[] values) {
        if (values == null || values.length == 0) return null;
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int val : values) {
            curr.next = new ListNode(val);
            curr = curr.next;
        }
        return dummy.next;
    }

    // Helper method to print linked list as string
    public static String printList(ListNode head) {
        StringBuilder sb = new StringBuilder();
        ListNode temp = head;
        while (temp != null) {
            sb.append(temp.val);
            if (temp.next != null) {
                sb.append(" -> ");
            }
            temp = temp.next;
        }
        return sb.toString();
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p143 obj = new p143();

        // Test case 1: [1, 2, 3, 4] -> [1, 4, 2, 3]
        ListNode head1 = buildList(new int[]{ 1, 2, 3, 4 });
        System.out.println("Original 1: " + printList(head1));
        obj.reorderList(head1);
        System.out.println("Reordered 1: " + printList(head1));

        // Test case 2: [1, 2, 3, 4, 5] -> [1, 5, 2, 4, 3]
        ListNode head2 = buildList(new int[]{ 1, 2, 3, 4, 5 });
        System.out.println("Original 2: " + printList(head2));
        obj.reorderList(head2);
        System.out.println("Reordered 2: " + printList(head2));

        // Test case 3: [1] -> [1]
        ListNode head3 = buildList(new int[]{ 1 });
        obj.reorderList(head3);
        System.out.println("Reordered 3: " + printList(head3));
    }
}
