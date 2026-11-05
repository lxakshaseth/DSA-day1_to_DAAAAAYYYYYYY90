// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 92: Reverse Linked List II
 * Given the head of a singly linked list and two integers left and right where left <= right,
 * reverse the nodes of the list from position left to position right, and return the reversed list.
 *
 * Single-Pass In-Place Pointer Manipulation Approach:
 * - Use a dummy node pointing to head to cleanly handle reversals starting from the very first node (left == 1).
 * - Traverse prev pointer to the node immediately preceding position left (i.e., left - 1 steps).
 * - Set curr = prev.next (the starting node of the segment, which will become the tail of the reversed sublist).
 * - For right - left iterations, iteratively move the node after curr (nextNode) to the position right after prev:
 *   1. nextNode = curr.next
 *   2. curr.next = nextNode.next
 *   3. nextNode.next = prev.next
 *   4. prev.next = nextNode
 * - This reverses the sublist strictly in-place in a single pass without extra allocations.
 *
 * Time Complexity: O(n) single pass (at most right steps)
 * Space Complexity: O(1) in-place auxiliary space
 */
public class p92 {

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

    // Method to reverse nodes from position left to position right
    public ListNode reverseBetween(ListNode head, int left, int right) {

        if (head == null || left == right) {
            return head;
        }

        // Dummy head to simplify edge cases where left == 1
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // Step 1: Reach node right before position left
        ListNode prev = dummy;
        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }

        // curr will become the tail of the reversed segment
        ListNode curr = prev.next;

        // Step 2: Reverse the segment between left and right in-place
        for (int i = 0; i < right - left; i++) {
            ListNode nextNode = curr.next;
            curr.next = nextNode.next;
            nextNode.next = prev.next;
            prev.next = nextNode;
        }

        return dummy.next;
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

        p92 obj = new p92();

        // Test case 1: [1, 2, 3, 4, 5], left = 2, right = 4 -> [1, 4, 3, 2, 5]
        ListNode head1 = buildList(new int[]{ 1, 2, 3, 4, 5 });
        System.out.println("Original 1: " + printList(head1));
        ListNode res1 = obj.reverseBetween(head1, 2, 4);
        System.out.println("Reversed (left=2, right=4): " + printList(res1));

        // Test case 2: [5], left = 1, right = 1 -> [5]
        ListNode head2 = buildList(new int[]{ 5 });
        System.out.println("Original 2: " + printList(head2));
        ListNode res2 = obj.reverseBetween(head2, 1, 1);
        System.out.println("Reversed (left=1, right=1): " + printList(res2));

        // Test case 3: [3, 5], left = 1, right = 2 -> [5, 3]
        ListNode head3 = buildList(new int[]{ 3, 5 });
        System.out.println("Original 3: " + printList(head3));
        ListNode res3 = obj.reverseBetween(head3, 1, 2);
        System.out.println("Reversed (left=1, right=2): " + printList(res3));
    }
}
