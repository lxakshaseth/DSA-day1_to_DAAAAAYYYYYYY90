// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 1448: Count Good Nodes in Binary Tree
 * Given a binary tree root, a node X in the tree is named good if in the path
 * from the root to X there are no nodes with a value greater than X.
 * Return the number of good nodes in the binary tree.
 *
 * Depth-First Search (DFS) with Running Maximum Approach:
 * - Traverse the tree from top to bottom (pre-order DFS).
 * - Keep track of maxSoFar, the maximum node value encountered along the current path from the root.
 * - At each node:
 *   1. If node.val >= maxSoFar, this node is good (count = 1), and update maxSoFar = node.val.
 *   2. Otherwise, count = 0, and maxSoFar remains unchanged.
 *   3. Recursively add the good node counts of the left and right subtrees passing the updated maxSoFar.
 *
 * Time Complexity: O(n) where n is the number of nodes (each node visited once)
 * Space Complexity: O(h) recursion call stack depth where h is the tree height (O(n) worst-case)
 */
public class p1448 {

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

    // Helper DFS method tracking the maximum value seen along the current path
    private int dfs(TreeNode node, int maxSoFar) {
        if (node == null) {
            return 0;
        }

        int count = 0;
        if (node.val >= maxSoFar) {
            count = 1;
            maxSoFar = node.val;
        }

        // Recurse left and right with updated maximum value
        count += dfs(node.left, maxSoFar);
        count += dfs(node.right, maxSoFar);

        return count;
    }

    // Method to count the number of good nodes in the tree
    public int goodNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return dfs(root, root.val);
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p1448 obj = new p1448();

        // Test case 1:
        //        3
        //       / \
        //      1   4
        //     /   / \
        //    3   1   5
        // Good nodes: 3 (root), 4, 5, 3 (left child of 1) -> Total = 4
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(1, new TreeNode(3), null);
        root1.right = new TreeNode(4, new TreeNode(1), new TreeNode(5));
        System.out.println("Good nodes in tree 1: " + obj.goodNodes(root1)); // 4

        // Test case 2:
        //        3
        //       /
        //      3
        //     / \
        //    4   2
        // Good nodes: 3 (root), 3 (child), 4 -> Total = 3
        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(3, new TreeNode(4), new TreeNode(2));
        System.out.println("Good nodes in tree 2: " + obj.goodNodes(root2)); // 3

        // Test case 3: Single node tree
        TreeNode root3 = new TreeNode(1);
        System.out.println("Good nodes in tree 3 (single node): " + obj.goodNodes(root3)); // 1
    }
}
