// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 98: Validate Binary Search Tree
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 *
 * Valid BST definition:
 * - The left subtree of a node contains only nodes with keys strictly less than node's key.
 * - The right subtree of a node contains only nodes with keys strictly greater than node's key.
 * - Both left and right subtrees must also be binary search trees.
 *
 * Range Validation Approach (using Long bounds):
 * - Pass valid allowable (min, max) range down recursively.
 * - For left child, the max allowed is current node's value.
 * - For right child, the min allowed is current node's value.
 *
 * Time Complexity: O(n) visiting each node once
 * Space Complexity: O(h) recursion stack where h is height of tree
 */
public class p98 {

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

    // Helper method to validate node within (min, max) open interval
    private boolean isValid(TreeNode node, long min, long max) {

        // Base case: null node is a valid BST
        if (node == null) {
            return true;
        }

        // Violates BST property if value is outside strictly allowed bounds
        if (node.val <= min || node.val >= max) {
            return false;
        }

        // Recursively validate left and right subtrees with updated bounds
        return isValid(node.left, min, node.val) && isValid(node.right, node.val, max);
    }

    // Main method to validate BST
    public boolean isValidBST(TreeNode root) {
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p98 obj = new p98();

        // Valid BST:
        //    2
        //   / \
        //  1   3
        TreeNode root1 = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        System.out.println("Tree 1 is valid BST: " + obj.isValidBST(root1));

        // Invalid BST:
        //    5
        //   / \
        //  1   4
        //     / \
        //    3   6
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(4, new TreeNode(3), new TreeNode(6));
        System.out.println("Tree 2 is valid BST: " + obj.isValidBST(root2));
    }
}
