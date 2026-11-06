// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 110: Balanced Binary Tree
 * Given a binary tree, determine if it is height-balanced.
 * A height-balanced binary tree is defined as a binary tree in which the depth
 * of the two subtrees of every node never differs by more than one.
 *
 * Bottom-Up Post-Order Depth-First Search (DFS) Approach:
 * - A naive top-down approach computes height at every node, taking O(n^2) worst case.
 * - Instead, use bottom-up recursion:
 *   - Helper checkHeight returns the height of the node if balanced.
 *   - If any subtree is found to be unbalanced (Math.abs(left - right) > 1),
 *     it immediately returns -1 as a failure sentinel to short-circuit further calculation.
 *   - If both subtrees are balanced, returns 1 + max(leftHeight, rightHeight).
 * - The tree is balanced if and only if checkHeight(root) != -1.
 *
 * Time Complexity: O(n) where n is the number of nodes (each node visited once)
 * Space Complexity: O(h) recursion call stack depth where h is tree height (O(n) worst-case)
 */
public class p110 {

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

    // Helper method to compute height while checking balance (-1 means unbalanced)
    private int checkHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // Recursively check left subtree
        int leftHeight = checkHeight(node.left);
        if (leftHeight == -1) {
            return -1; // Propagate imbalance upward immediately
        }

        // Recursively check right subtree
        int rightHeight = checkHeight(node.right);
        if (rightHeight == -1) {
            return -1; // Propagate imbalance upward immediately
        }

        // If height difference between subtrees exceeds 1, tree is unbalanced
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        // Return actual height of current node
        return 1 + Math.max(leftHeight, rightHeight);
    }

    // Method to check if binary tree is height-balanced
    public boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p110 obj = new p110();

        // Test case 1:
        //        3
        //       / \
        //      9  20
        //         /  \
        //        15   7
        // Expected: true (balanced)
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20, new TreeNode(15), new TreeNode(7));
        System.out.println("Tree 1 is balanced: " + obj.isBalanced(root1)); // true

        // Test case 2:
        //          1
        //         / \
        //        2   2
        //       / \
        //      3   3
        //     / \
        //    4   4
        // Expected: false (unbalanced)
        TreeNode root2 = new TreeNode(1);
        TreeNode left2 = new TreeNode(2);
        TreeNode right2 = new TreeNode(2);
        TreeNode left3 = new TreeNode(3, new TreeNode(4), new TreeNode(4));
        TreeNode right3 = new TreeNode(3);
        left2.left = left3;
        left2.right = right3;
        root2.left = left2;
        root2.right = right2;
        System.out.println("Tree 2 is balanced: " + obj.isBalanced(root2)); // false

        // Test case 3: empty tree -> true
        System.out.println("Empty tree is balanced: " + obj.isBalanced(null)); // true
    }
}
