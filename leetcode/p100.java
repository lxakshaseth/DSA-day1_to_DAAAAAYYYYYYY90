// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 100: Same Tree
 * Given the roots of two binary trees p and q, write a function to check if they are the same or not.
 * Two binary trees are considered the same if they are structurally identical,
 * and the nodes have the same value.
 *
 * Recursive Depth-First Search (DFS) Approach:
 * - If both nodes are null, both trees are identical at this branch -> return true.
 * - If one is null and the other is not, or values differ -> return false.
 * - Recursively check that both left and right subtrees are identical.
 *
 * Time Complexity: O(n) where n is the number of nodes
 * Space Complexity: O(h) where h is the tree height (recursion stack)
 */
public class p100 {

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

    // Method to check if two binary trees are identical
    public boolean isSameTree(TreeNode p, TreeNode q) {

        // Both nodes are null: identical at this leaf
        if (p == null && q == null) {
            return true;
        }

        // One is null while other is not: structural mismatch
        if (p == null || q == null) {
            return false;
        }

        // Values do not match: content mismatch
        if (p.val != q.val) {
            return false;
        }

        // Recursively check left and right subtrees
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p100 obj = new p100();

        // Tree 1: 1 -> left: 2, right: 3
        TreeNode p1 = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        // Tree 2: 1 -> left: 2, right: 3
        TreeNode q1 = new TreeNode(1, new TreeNode(2), new TreeNode(3));

        System.out.println("Tree 1 and Tree 2 are same: " + obj.isSameTree(p1, q1));

        // Tree 3: 1 -> left: 2
        TreeNode p2 = new TreeNode(1, new TreeNode(2), null);
        // Tree 4: 1 -> right: 2
        TreeNode q2 = new TreeNode(1, null, new TreeNode(2));

        System.out.println("Tree 3 and Tree 4 are same: " + obj.isSameTree(p2, q2));
    }
}
