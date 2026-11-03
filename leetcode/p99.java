// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 99: Recover Binary Search Tree
 * You are given the root of a binary search tree (BST), where the values of exactly
 * two nodes of the tree were swapped by mistake. Recover the tree without changing its structure.
 *
 * Core Concept & In-Order Property:
 * - An in-order traversal of a standard BST visits node values in strictly increasing order.
 * - If two nodes are swapped, the sorted order is broken:
 *   Case 1 (Non-adjacent swap): There will be TWO inversion points (prev.val > curr.val):
 *     - At the first inversion, 'prev' is the first misplaced node.
 *     - At the second inversion, 'curr' is the second misplaced node.
 *   Case 2 (Adjacent swap): There is only ONE inversion point (prev.val > curr.val):
 *     - 'prev' is the first misplaced node, and 'curr' is the second misplaced node.
 *
 * Approach 1: Recursive In-Order Traversal (DFS)
 * - Traverse in-order and track the previous node visited.
 * - Detect the two swapped nodes and swap their values at the end.
 * - Time Complexity: O(n) visiting each node once
 * - Space Complexity: O(h) recursion stack depth (where h is the tree height)
 *
 * Approach 2: Morris In-Order Traversal (Follow-up: O(1) Auxiliary Space)
 * - Uses threaded binary trees to traverse without recursion or an explicit stack.
 * - Achieves true O(1) auxiliary space while restoring tree pointers before completion.
 * - Time Complexity: O(n)
 * - Space Complexity: O(1) auxiliary space
 */
public class p99 {

    // Definition for a binary tree node
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private TreeNode first = null;
    private TreeNode second = null;
    private TreeNode prev = null;

    // Helper method for recursive in-order traversal
    private void inorder(TreeNode root) {
        if (root == null) {
            return;
        }

        // Traverse left subtree
        inorder(root.left);

        // Detect inversion where prev.val > root.val
        if (prev != null && prev.val > root.val) {
            if (first == null) {
                first = prev;
            }
            second = root;
        }
        prev = root;

        // Traverse right subtree
        inorder(root.right);
    }

    // Approach 1: Recover tree using recursive in-order DFS
    public void recoverTree(TreeNode root) {
        first = null;
        second = null;
        prev = null;

        inorder(root);

        // Swap the values of the two identified misplaced nodes
        if (first != null && second != null) {
            int temp = first.val;
            first.val = second.val;
            second.val = temp;
        }
    }

    // Approach 2: Recover tree using Morris In-Order Traversal (O(1) space)
    public void recoverTreeMorris(TreeNode root) {
        TreeNode firstNode = null;
        TreeNode secondNode = null;
        TreeNode previous = null;
        TreeNode curr = root;

        while (curr != null) {
            if (curr.left == null) {
                // Visit current node
                if (previous != null && previous.val > curr.val) {
                    if (firstNode == null) {
                        firstNode = previous;
                    }
                    secondNode = curr;
                }
                previous = curr;
                curr = curr.right;
            } else {
                // Find in-order predecessor
                TreeNode pred = curr.left;
                while (pred.right != null && pred.right != curr) {
                    pred = pred.right;
                }

                if (pred.right == null) {
                    // Establish temporary thread to current node
                    pred.right = curr;
                    curr = curr.left;
                } else {
                    // Remove thread and visit current node
                    pred.right = null;
                    if (previous != null && previous.val > curr.val) {
                        if (firstNode == null) {
                            firstNode = previous;
                        }
                        secondNode = curr;
                    }
                    previous = curr;
                    curr = curr.right;
                }
            }
        }

        // Swap values of the two identified misplaced nodes
        if (firstNode != null && secondNode != null) {
            int temp = firstNode.val;
            firstNode.val = secondNode.val;
            secondNode.val = temp;
        }
    }

    // Helper method to print in-order traversal of the tree
    public static void printInOrder(TreeNode root) {
        if (root == null) return;
        printInOrder(root.left);
        System.out.print(root.val + " ");
        printInOrder(root.right);
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p99 obj = new p99();

        // Test Case 1: [1, 3, null, null, 2] -> Expected in-order after recovery: 1 2 3
        //      1
        //     /
        //    3
        //     \
        //      2
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(3);
        root1.left.right = new TreeNode(2);

        System.out.print("Test Case 1 Before: ");
        printInOrder(root1);
        System.out.println();

        obj.recoverTree(root1);

        System.out.print("Test Case 1 After (DFS): ");
        printInOrder(root1);
        System.out.println("\n");

        // Test Case 2: [3, 1, 4, null, null, 2] -> Expected in-order after recovery: 1 2 3 4
        //      3
        //     / \
        //    1   4
        //       /
        //      2
        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(4);
        root2.right.left = new TreeNode(2);

        System.out.print("Test Case 2 Before: ");
        printInOrder(root2);
        System.out.println();

        obj.recoverTreeMorris(root2);

        System.out.print("Test Case 2 After (Morris O(1)): ");
        printInOrder(root2);
        System.out.println("\n");

        // Test Case 3: Adjacent swap [2, 1, 3] where root = 1, left = 2, right = 3
        // In-order before: 2 1 3 -> After recovery: 1 2 3
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(2);
        root3.right = new TreeNode(3);

        System.out.print("Test Case 3 Before: ");
        printInOrder(root3);
        System.out.println();

        obj.recoverTree(root3);

        System.out.print("Test Case 3 After (DFS): ");
        printInOrder(root3);
        System.out.println();
    }
}
