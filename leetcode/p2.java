// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 2: Add Two Numbers
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order, and each of their nodes contains a single digit.
 * Add the two numbers and return the sum as a linked list.
 *
 * Elementary Math + Carry Propagation Approach:
 * - Since digits are ordered from least significant to most significant (reversed),
 *   we simulate grade-school addition directly from head to tail.
 * - Use a dummy head node to simplify result list construction.
 * - Maintain a running carry value.
 * - In each step:
 *   sum = carry + (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0)
 *   carry = sum / 10
 *   curr.next = new ListNode(sum % 10)
 * - Continue while l1 != null || l2 != null || carry != 0.
 *
 * Time Complexity: O(max(m, n)) where m and n are the lengths of l1 and l2
 * Space Complexity: O(max(m, n)) to construct the resulting linked list
 */
public class p2 {

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

    // Method to add two numbers represented as linked lists
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        int carry = 0;

        // Traverse both lists until all digits and carry are processed
        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;

            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            // Update carry for the next position
            carry = sum / 10;

            // Create new node with digit value (sum % 10)
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
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

        p2 obj = new p2();

        // Test case 1: 342 + 465 = 807
        // l1 = [2, 4, 3], l2 = [5, 6, 4] -> [7, 0, 8]
        ListNode l1 = buildList(new int[]{ 2, 4, 3 });
        ListNode l2 = buildList(new int[]{ 5, 6, 4 });
        System.out.println("List 1: " + printList(l1));
        System.out.println("List 2: " + printList(l2));
        ListNode sum1 = obj.addTwoNumbers(l1, l2);
        System.out.println("Sum 1: " + printList(sum1)); // 7 -> 0 -> 8

        // Test case 2: 0 + 0 = 0
        ListNode l3 = buildList(new int[]{ 0 });
        ListNode l4 = buildList(new int[]{ 0 });
        ListNode sum2 = obj.addTwoNumbers(l3, l4);
        System.out.println("Sum 2: " + printList(sum2)); // 0

        // Test case 3: 9999999 + 9999 = 10009998
        ListNode l5 = buildList(new int[]{ 9, 9, 9, 9, 9, 9, 9 });
        ListNode l6 = buildList(new int[]{ 9, 9, 9, 9 });
        ListNode sum3 = obj.addTwoNumbers(l5, l6);
        System.out.println("Sum 3: " + printList(sum3)); // 8 -> 9 -> 9 -> 9 -> 0 -> 0 -> 0 -> 1
    }
}
