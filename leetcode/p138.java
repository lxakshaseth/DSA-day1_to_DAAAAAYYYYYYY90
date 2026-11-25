package leetcode;

/**
 * LeetCode 138: Copy List with Random Pointer
 * https://leetcode.com/problems/copy-list-with-random-pointer/
 *
 * A linked list of length n is given such that each node contains an additional
 * random pointer, which could point to any node in the list, or null.
 * Construct a deep copy of the list.
 */
public class p138 {

    public static class Node {
        public int val;
        public Node next;
        public Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /**
     * Optimal 3-Pass In-Place Interleaving Algorithm
     * Time Complexity: O(n)
     * Space Complexity: O(1) auxiliary space (excluding returned copied list)
     */
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        // Pass 1: Clone each node and insert it directly after the original node
        // Original: A -> B -> C
        // Becomes:  A -> A' -> B -> B' -> C -> C'
        Node curr = head;
        while (curr != null) {
            Node copy = new Node(curr.val);
            copy.next = curr.next;
            curr.next = copy;
            curr = copy.next;
        }

        // Pass 2: Assign random pointers for all copied nodes
        curr = head;
        while (curr != null) {
            if (curr.random != null) {
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;
        }

        // Pass 3: Separate the interleaved list into original and cloned lists
        Node dummyHead = new Node(0);
        Node copyTail = dummyHead;
        curr = head;

        while (curr != null) {
            Node copy = curr.next;
            Node nextOriginal = copy.next;

            // Append copy to cloned list
            copyTail.next = copy;
            copyTail = copy;

            // Restore original list pointer
            curr.next = nextOriginal;

            curr = nextOriginal;
        }

        return dummyHead.next;
    }

    public static void main(String[] args) {
        System.out.println("Testing LeetCode 138: Copy List with Random Pointer...");
        p138 solver = new p138();

        // Test 1: [[7,null],[13,0],[11,4],[10,2],[1,0]]
        Node n0 = new Node(7);
        Node n1 = new Node(13);
        Node n2 = new Node(11);
        Node n3 = new Node(10);
        Node n4 = new Node(1);

        n0.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        n0.random = null;
        n1.random = n0;
        n2.random = n4;
        n3.random = n2;
        n4.random = n0;

        Node cloned = solver.copyRandomList(n0);

        // Verify deep copy: addresses must differ, values and relative links must match
        Node orig = n0;
        Node clone = cloned;
        while (orig != null) {
            assert orig != clone : "Cloned node cannot share memory address with original node!";
            assert orig.val == clone.val : "Values must match: " + orig.val + " vs " + clone.val;

            if (orig.random == null) {
                assert clone.random == null : "Cloned random pointer should be null";
            } else {
                assert orig.random != clone.random : "Cloned random cannot point to original list node!";
                assert orig.random.val == clone.random.val : "Cloned random value mismatch";
            }

            orig = orig.next;
            clone = clone.next;
        }
        assert clone == null : "Cloned list should end at null";
        System.out.println("Test 1: Complex 5-node list with random pointers passed!");

        // Test 2: [[1,1],[2,1]]
        Node a0 = new Node(1);
        Node a1 = new Node(2);
        a0.next = a1;
        a0.random = a1;
        a1.random = a1;

        Node cloned2 = solver.copyRandomList(a0);
        assert cloned2 != a0 && cloned2.val == 1;
        assert cloned2.next != a1 && cloned2.next.val == 2;
        assert cloned2.random == cloned2.next;
        assert cloned2.next.random == cloned2.next;
        System.out.println("Test 2: Two nodes with self & forward random pointers passed!");

        // Test 3: Null list
        assert solver.copyRandomList(null) == null : "Null input must return null";
        System.out.println("Test 3: Null input passed!");

        // Test 4: Single node pointing to itself
        Node s0 = new Node(42);
        s0.random = s0;
        Node clonedSingle = solver.copyRandomList(s0);
        assert clonedSingle != s0;
        assert clonedSingle.val == 42;
        assert clonedSingle.random == clonedSingle;
        System.out.println("Test 4: Single self-referencing node passed!");

        System.out.println("All test cases passed successfully!");
    }
}
