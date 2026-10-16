// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 572: Subtree of Another Tree
 * Given the roots of two binary trees root and subRoot, return true if there is a subtree
 * of root with the same structure and node values of subRoot and false otherwise.
 *
 * Recursive DFS + Same Tree Helper:
 * - A tree is a subtree of another if:
 *   1. They are identical at current root (isSameTree).
 *   2. Or subRoot is a subtree of the left child.
 *   3. Or subRoot is a subtree of the right child.
 *
 * Time Complexity: O(n * m) where n = nodes in root, m = nodes in subRoot
 * Space Complexity: O(h) recursion stack where h = height of root
 */
public class p572 {

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

    // Helper method to check if two trees are identical
    private boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null || p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    // Method to check if subRoot is a subtree of root
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {

        // An empty tree is always a subtree
        if (subRoot == null) {
            return true;
        }

        // Non-empty subRoot cannot be a subtree of null root
        if (root == null) {
            return false;
        }

        // If trees match starting from current root
        if (isSameTree(root, subRoot)) {
            return true;
        }

        // Check recursively in left and right subtrees
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p572 obj = new p572();

        // Main Tree:
        //       3
        //      / \
        //     4   5
        //    / \
        //   1   2
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(4, new TreeNode(1), new TreeNode(2));
        root.right = new TreeNode(5);

        // Subtree:
        //     4
        //    / \
        //   1   2
        TreeNode subRoot = new TreeNode(4, new TreeNode(1), new TreeNode(2));

        System.out.println("subRoot is subtree of root: " + obj.isSubtree(root, subRoot));
    }
}
